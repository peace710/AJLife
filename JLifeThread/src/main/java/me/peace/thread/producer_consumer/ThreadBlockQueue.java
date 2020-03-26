package me.peace.thread.producer_consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

//生产者与消费者(使用BlockQueue,线程安全阻塞的队列)
public class ThreadBlockQueue {
    private static final String TAG = ThreadBlockQueue.class.getSimpleName();

    public static void main(String[] args) {
        ShopBlockQueue shop = new ShopBlockQueue();
        shop.work();
        shop.stopWork();
    }
}

class ProducerBlockQueue implements Runnable{
    private static final String TAG = ProducerBlockQueue.class.getSimpleName();
    private ShopBlockQueue shop;

    public ProducerBlockQueue(ShopBlockQueue shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                LinkedBlockingQueue<GoodsBlockQueue> queue = shop.getQueue();
                if (queue.size() == 0) {
                    produce(10);
                }
                LogUtils.d(TAG,"producer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void produce(int count){
        LinkedBlockingQueue<GoodsBlockQueue> queue = shop.getQueue();
        for (int i = 0 ; i < count ;i++){
            queue.add(new GoodsBlockQueue(i));
        }
        LogUtils.i(TAG,"Producer produce");
    }
}

class ConsumerBlockQueue implements Runnable{
    private static final String TAG = ConsumerBlockQueue.class.getSimpleName();
    private ShopBlockQueue shop;

    public ConsumerBlockQueue(ShopBlockQueue shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                LinkedBlockingQueue<GoodsBlockQueue> queue = shop.getQueue();
                if (queue.size() > 0) {
                    LogUtils.i(TAG,"Consumer consume,id is " + queue.take().getId());
                }
                LogUtils.d(TAG,"consumer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

class ShopBlockQueue{
    private static final String TAG = ShopBlockQueue.class.getSimpleName();
    private LinkedBlockingQueue<GoodsBlockQueue> queue = new LinkedBlockingQueue(10);
    private ProducerBlockQueue producer = new ProducerBlockQueue(this);
    private ConsumerBlockQueue consumer = new ConsumerBlockQueue(this);

    private ExecutorService service;

    public LinkedBlockingQueue<GoodsBlockQueue> getQueue() {
        return queue;
    }



    public ShopBlockQueue() {
        service = Executors.newCachedThreadPool();
    }

    public void work(){
        service.execute(producer);
        service.execute(consumer);
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

class GoodsBlockQueue{
    private int id;

    public GoodsBlockQueue(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
