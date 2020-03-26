package me.peace.thread.sync;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicInteger;

import me.peace.thread.LogUtils;

public class ThreadSyncAtomic {
    private static final String TAG = ThreadSyncAtomic.class.getSimpleName();

    public static void main(String[] args) {
        handleData(new DataAtomicHandler(),10);
    }

    private static void handleData(DataAtomicHandler handler,int nums){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ;i < nums ;i++) {
            service.execute(new DataAtomicHandleRunnable(handler));
        }
        try {
            service.awaitTermination(5, TimeUnit.SECONDS);
            LogUtils.i(TAG,"handleData --> " + handler.getData().get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class DataAtomicHandleRunnable implements Runnable{
    private static final String TAG = DataAtomicHandleRunnable.class.getSimpleName();

    private DataAtomicHandler handler;

    public DataAtomicHandleRunnable(DataAtomicHandler handler) {
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

class DataAtomicHandler{
    //原子类变量AtomicInteger、AtomicLong和AtomicReference
    private AtomicInteger data = new AtomicInteger(0);

    public int handle(){
        data.addAndGet(3);
        return data.get();
    }

    public AtomicInteger getData() {
        return data;
    }
}

