package me.peace.thread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

//生产者与消费者(使用wait,notify,notifyAll)
public class ThreadWaitAndNotify {
    private static final String TAG = ThreadWaitAndNotify.class.getSimpleName();

    public static void main(String[] args) {
        Shop shop = new Shop();
        shop.work();
        shop.stopWork();
    }
}

class Producer implements Runnable{
    private static final String TAG = Producer.class.getSimpleName();
    private Shop shop;

    public Producer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                LinkedList<Goods> list = shop.getGoods();
                synchronized (this) {
                    while (list.size() != 0) {
                        //当生产的物品有库存时，生产者等待
                        wait();
                    }
                }
                synchronized (shop.getConsumer()){
                    //无库存时，生产物品
                    produce(10);
                    //唤醒消费者
                    shop.getConsumer().notifyAll();
                }
                LogUtils.d(TAG,"producer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }

    private void produce(int count){
        LinkedList<Goods> list = shop.getGoods();
        for (int i = 0 ; i < count ;i++){
            list.add(new Goods(i));
        }
        LogUtils.i(TAG,"Producer produce");
    }
}

class Consumer implements Runnable{
    private static final String TAG = Consumer.class.getSimpleName();
    private Shop shop;

    public Consumer(Shop shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            try {
                Thread.sleep(100);
                LinkedList<Goods> list = shop.getGoods();
                synchronized (this) {
                    //无库存时，消费者等待
                    while (list.size() == 0) {
                       wait();
                    }
                }
                synchronized (shop.getProducer()){
                    LogUtils.i(TAG,"Consumer consume,id is " + list.remove().getId());
                    //唤醒生产者
                    if (list.size() == 0) {
                        shop.getProducer().notifyAll();
                    }
                }
                LogUtils.d(TAG,"consumer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }
        }
    }
}

class Shop{
    private static final String TAG = Shop.class.getSimpleName();
    private LinkedList<Goods> goods = new LinkedList<>();
    private Producer producer = new Producer(this);
    private Consumer consumer = new Consumer(this);

    private ExecutorService service;

    public LinkedList<Goods> getGoods() {
        return goods;
    }

    public Producer getProducer() {
        return producer;
    }

    public Consumer getConsumer() {
        return consumer;
    }

    public Shop() {
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

class Goods{
    private int id;

    public Goods(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}