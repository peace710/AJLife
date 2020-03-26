package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadSyncObj {
    private static final String TAG = ThreadSyncObj.class.getSimpleName();

    public static void main(String[] args) {
//        syncObjectA();
        syncObjectB();
    }

    private static void syncObjectA(){
        SyncObjHandler handler = new SyncObjHandler();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new SyncObjRunnable(handler));
        handler.methodB();
        service.shutdown();
    }

    private static void syncObjectB(){
        SyncObjHandler handler = new SyncObjHandler();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new SyncObjRunnable(handler));
        handler.methodC();
        service.shutdown();
    }
}

class SyncObjRunnable implements Runnable{
    private SyncObjHandler handler;

    public SyncObjRunnable(SyncObjHandler handler) {
        this.handler = handler;
    }

    @Override
    public void run() {
        handler.methodA();
    }
}

class SyncObjHandler{
    private static final String TAG = SyncObjHandler.class.getSimpleName();
    private Object syncObject = new Object();

    public synchronized void methodA(){
        for (int i = 0 ; i < 5 ;i++){
            LogUtils.i(TAG,"methodA[" + i +"]");
            Thread.yield();
        }
    }

    public  void methodB(){
        for (int i = 0 ; i < 5 ;i++){
            LogUtils.i(TAG,"methodB[" + i +"]");
            Thread.yield();
        }
    }

    public void methodC(){
        //使用对象加上同步锁
        synchronized (syncObject) {
            for (int i = 0; i < 5; i++) {
                LogUtils.i(TAG, "methodC[" + i + "]");
                Thread.yield();
            }
        }
    }
}
