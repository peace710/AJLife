package me.peace.thread.exchanger;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class Producer implements Runnable {
    private static final String TAG = "Producer";

    private Exchanger<Integer> exchanger;
    private static int data = 0;
    Producer(Exchanger<Integer> exchanger) {
        this.exchanger = exchanger;
    }

    @Override
    public void run() {
        for (int i = 1; i < 5; i++) {
            try {
                TimeUnit.SECONDS.sleep(1);
                data = i;
                LogUtils.i(TAG, "Producer 交换前:" + data);
                data = exchanger.exchange(data);
                LogUtils.i(TAG, "Producer 交换后:" + data);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
