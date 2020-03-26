package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ThreadSyncLock {
    private static final String TAG = ThreadSyncLock.class.getSimpleName();

    public static void main(String[] args) {
        handleData(new DataLockHandler(),10);
    }

    private static void handleData(DataLockHandler handler,int nums){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ;i < nums ;i++) {
            service.execute(new DataLockHandleRunnable(handler));
        }
        service.shutdown();
    }
}

class DataLockHandleRunnable implements Runnable{
    private static final String TAG = DataLockHandleRunnable.class.getSimpleName();

    private DataLockHandler handler;

    public DataLockHandleRunnable(DataLockHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int data;
        while ((data = handler.handle()) < 1000){
            LogUtils.i(TAG,this + ",data = " + data);
            if (data % 3  != 0){
                break;
            }
        }
    }
}

class DataLockHandler{
    private int data = 0;

    //使用Lock上锁与解锁，配置try catch finally使用，避免出现死锁
    private Lock lock = new ReentrantLock();

    public int handle(){
        lock.lock();
        try {
            data++;
            sleep();
            data++;
            sleep();
            data++;
        } finally {
            lock.unlock();
        }
        return data;
    }
    private void sleep(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
