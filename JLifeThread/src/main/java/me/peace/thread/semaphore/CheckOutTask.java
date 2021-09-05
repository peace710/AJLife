package me.peace.thread.semaphore;


import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class CheckOutTask<T> implements Runnable {
    private static final String TAG = "CheckOutTask";
    private static int counter = 0;
    private final int id = counter++;
    private Pool<T> pool;

    public CheckOutTask(Pool<T> pool) {
        this.pool = pool;
    }

    @Override
    public void run() {
        try {
            T item = pool.checkOut();
            LogUtils.i(TAG,this + " checked out " + item);
            TimeUnit.SECONDS.sleep(1);
            LogUtils.i(TAG,this + " checking in " + item);
            pool.checkIn(item);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String toString() {
        return "CheckOutTask{" +
                "id=" + id +
                '}';
    }
}
