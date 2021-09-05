package me.peace.thread.exchanger;


import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class Consumer implements Runnable {
    private static final String TAG = "Consumer";
    private Exchanger<Integer> exchanger;
    private static int data = 0;
    Consumer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        while (true) {
            data = 0;
            LogUtils.i(TAG,"Consumer 交换前:" + data);
            try {
                TimeUnit.SECONDS.sleep(1);
                data = exchanger.exchange(data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            LogUtils.i(TAG,"Consumer 交换后:" + data);
        }
    }
}
