package me.peace.thread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

/**
 * Semaphore（信号量）是用来控制同时访问特定资源的线程数量，它通过协调各个线程，
 * 以保证合理的使用公共资源，在操作系统中是一个非常重要的问题，可以用来解决哲学家就餐问题。
 * Java中的Semaphore维护了一个许可集，一开始先设定这个许可集的数量，可以使用acquire()方法获得一个许可，
 * 当许可不足时会被阻塞，release()添加一个许可。
 */
public class ThreadSemaphore {
    private static final String TAG = ThreadSemaphore.class.getSimpleName();

    public static void main(String[] args) {
        ShopSemaphore shop = new ShopSemaphore();
        shop.work();
        shop.stopWork();
    }
}

class ProducerSemaphore implements Runnable{
    private static final String TAG = ProducerSemaphore.class.getSimpleName();
    private ShopSemaphore shop;

    public ProducerSemaphore(ShopSemaphore shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                shop.producer.acquire();
                LinkedList<GoodsSemaphore> list = shop.getGoods();
                if (list.size() == 0) {
                    produce(10);
                }
                LogUtils.d(TAG,"producer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                shop.consumer.release();
            }
        }
    }

    private void produce(int count){
        LinkedList<GoodsSemaphore> list = shop.getGoods();
        for (int i = 0 ; i < count ;i++){
            list.add(new GoodsSemaphore(i));
        }
        LogUtils.i(TAG,"Producer produce");
    }
}

class ConsumerSemaphore implements Runnable{
    private static final String TAG = ConsumerSemaphore.class.getSimpleName();
    private ShopSemaphore shop;

    public ConsumerSemaphore(ShopSemaphore shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                shop.consumer.acquire();
                LinkedList<GoodsSemaphore> list = shop.getGoods();
                if (list.size() > 0) {
                    LogUtils.i(TAG, "Consumer consume,id is " + list.remove().getId());
                }
                LogUtils.d(TAG,"consumer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }finally {
                shop.producer.release();
            }
        }
    }
}

class ShopSemaphore{
    private static final String TAG = ShopSemaphore.class.getSimpleName();
    private LinkedList<GoodsSemaphore> goods = new LinkedList<>();
    Semaphore producer = new Semaphore(10);
    Semaphore consumer = new Semaphore(0);

    private ExecutorService service;

    public LinkedList<GoodsSemaphore> getGoods() {
        return goods;
    }

    public ShopSemaphore() {
        service = Executors.newCachedThreadPool();
    }

    public void work(){
        service.execute(new ProducerSemaphore(this));
        service.execute(new ConsumerSemaphore(this));
    }

    public void stopWork(){
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdownNow();
    }
}

class GoodsSemaphore{
    private int id;

    public GoodsSemaphore(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
