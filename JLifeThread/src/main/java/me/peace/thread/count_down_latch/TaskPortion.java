package me.peace.thread.count_down_latch;


import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class TaskPortion implements Runnable{
    private static final String TAG = "TaskPortion";
    private static int counter = 0;

    private final int id = counter++;

    private static Random random = new Random(48);

    private final CountDownLatch latch;

    public TaskPortion(CountDownLatch latch) {
        this.latch = latch;
    }

    @Override
    public void run() {
        try {
            doWork();
            latch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

    private void doWork() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(random.nextInt(2000));
        LogUtils.i(TAG,this + " is completed");
    }

    @Override
    public String toString() {
        return "TaskPortion{" +
                "id=" + id +
                '}';
    }
}
