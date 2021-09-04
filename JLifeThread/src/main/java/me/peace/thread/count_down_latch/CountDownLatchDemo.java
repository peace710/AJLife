package me.peace.thread.count_down_latch;


import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.peace.thread.LogUtils;

public class CountDownLatchDemo {
    private static final String TAG = "CountDownLatchDemo";

    public static final int SIZE = 100;

    public static void main(String[] args) {
        ExecutorService service = Executors.newCachedThreadPool();
        CountDownLatch latch = new CountDownLatch(SIZE);
        for (int i = 0 ; i < 10 ;i++){
            service.execute(new WaitingTask(latch));
        }
        for (int i = 0 ; i < SIZE ;i++){
            service.execute(new TaskPortion(latch));
        }
        LogUtils.i(TAG,"Launched all tasks");
        service.shutdown();
    }
}
