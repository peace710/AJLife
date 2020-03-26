package me.peace.thread.producer_consumer;

import java.util.LinkedList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import me.peace.thread.LogUtils;

//生产者与消费者(使用Lock,Condition)
public class ThreadLockCondition {
    private static final String TAG = ThreadLockCondition.class.getSimpleName();

    public static void main(String[] args) {
        ShopLockCondition shop = new ShopLockCondition();
        shop.work();
        shop.stopWork();
    }
}

class ProducerLockCondition implements Runnable{
    private static final String TAG = ProducerLockCondition.class.getSimpleName();
    private ShopLockCondition shop;

    public ProducerLockCondition(ShopLockCondition shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            shop.lock.lock();
            try {
                Thread.sleep(100);
                LinkedList<GoodsLockCondition> list = shop.getGoods();
                while (list.size() != 0) {
                    //当生产的物品有库存时，生产者等待
                    shop.producer.await();
                }


                //无库存时，生产物品，唤醒消费者
                produce(10);
                shop.consumer.signalAll();
                LogUtils.d(TAG,"producer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            } finally {
                shop.lock.unlock();
            }
        }
    }

    private void produce(int count){
        LinkedList<GoodsLockCondition> list = shop.getGoods();
        for (int i = 0 ; i < count ;i++){
            list.add(new GoodsLockCondition(i));
        }
        LogUtils.i(TAG,"Producer produce");
    }
}

class ConsumerLockCondition implements Runnable{
    private static final String TAG = ConsumerLockCondition.class.getSimpleName();
    private ShopLockCondition shop;

    public ConsumerLockCondition(ShopLockCondition shop) {
        this.shop = shop;
    }

    @Override
    public void run() {
        while (!Thread.interrupted()) {
            shop.lock.lock();
            try {
                Thread.sleep(100);
                LinkedList<GoodsLockCondition> list = shop.getGoods();
                //无库存时，消费者等待
                while (list.size() == 0) {
                    shop.consumer.await();
                }

                LogUtils.i(TAG,"Consumer consume,id is " + list.remove().getId());
                //唤醒生产者
                if (list.size() == 0) {
                    shop.producer.signalAll();
                }
                LogUtils.d(TAG,"consumer thread");
            } catch (InterruptedException e) {
                e.printStackTrace();
                Thread.currentThread().interrupt();
            }finally {
                shop.lock.unlock();
            }
        }
    }
}

class ShopLockCondition{
    private static final String TAG = ShopLockCondition.class.getSimpleName();
    private LinkedList<GoodsLockCondition> goods = new LinkedList<>();

    Lock lock = new ReentrantLock();
    Condition producer = lock.newCondition();
    Condition consumer = lock.newCondition();

    private ExecutorService service;

    public LinkedList<GoodsLockCondition> getGoods() {
        return goods;
    }


    public ShopLockCondition() {
        service = Executors.newCachedThreadPool();
    }

    public void work(){
        service.execute(new ProducerLockCondition(this));
        service.execute(new ConsumerLockCondition(this));
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

class GoodsLockCondition{
    private int id;

    public GoodsLockCondition(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }
}
