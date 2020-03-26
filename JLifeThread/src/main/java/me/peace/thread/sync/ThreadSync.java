package me.peace.thread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import me.peace.thread.LogUtils;

public class ThreadSync {
    private static final String TAG = ThreadSync.class.getSimpleName();

    public static void main(String[] args) {
        handleData(new DataHandler(),10);
    }

    private static void handleData(DataHandler handler,int nums){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ;i < nums ;i++) {
//            service.execute(new DataHandleRunnable(handler));
            service.execute(new SyncDataHandleRunnable(handler));
        }
        service.shutdown();
    }
}

class DataHandleRunnable implements Runnable{
    private static final String TAG = DataHandleRunnable.class.getSimpleName();

    private DataHandler handler;

    public DataHandleRunnable(DataHandler handler) {
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

class SyncDataHandleRunnable implements Runnable{
    private static final String TAG = DataHandleRunnable.class.getSimpleName();

    private DataHandler handler;

    public SyncDataHandleRunnable(DataHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        int data;
        while ((data = handler.syncHandle()) < 100){
            LogUtils.i(TAG,this + ",data = " + data);
            if (data % 3  != 0){
                break;
            }
        }
    }
}

class DataHandler{
    private static final String TAG = DataHandler.class.getSimpleName();

    private int data = 0;
    private int syncData = 0;

    //synchronized方法实现同步效果，对比加与不加程序运行效果
    public synchronized int handle(){
        data++;
        sleep();
        data++;
        sleep();
        data++;
        return data;
    }

    public int syncHandle(){
        LogUtils.i(TAG,"before enter synchronized block");
        try {
            //synchronized方法实现同步块
            synchronized (this) {
                syncData++;
                sleep();
                syncData++;
                sleep();
                syncData++;
                return syncData;
            }
        } finally {
            LogUtils.i(TAG,"after enter synchronized block");
        }
    }

    private void sleep(){
        try {
            Thread.sleep(50);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
