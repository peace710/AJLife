package me.peace.thread.count_down_latch;


import java.util.concurrent.CountDownLatch;

import me.peace.thread.LogUtils;

public class WaitingTask implements Runnable {
    private static final String TAG = "WaitingTask";
    public static int counter = 0;

    private final int id = counter++;

    private final CountDownLatch latch;

    public WaitingTask(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            latch.await();
            LogUtils.i(TAG,"Latch barrier passed for " + this);
        } catch (InterruptedException e) {
            e.printStackTrace();
            LogUtils.i(TAG,this + " interrupted");
        }
    }

    @Override
    public String toString() {
        return "WaitingTask{" +
                "id=" + id +
                '}';
    }
}
