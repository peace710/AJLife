package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class ThreadFactoryMode {
    private static final String TAG = ThreadFactoryMode.class.getSimpleName();

    public static void main(String[] args) {
        factoryThread();
    }


    /**
     * 一旦主线程完成其工作结束了，那么整个程序就结束
     * 为了观察后台线程的工作，所以需要确认主线程不停止
     * 这里设置成短暂sleep供查看后台线程的工作
     */
    private static void factoryThread(){
        ExecutorService service = Executors.newCachedThreadPool(new DaemonThreadFactory());
        for (int i = 0 ;i < 5 ;i++){
            service.execute(new DaemonRunnable());
        }
        LogUtils.i(TAG,"All daemons started");
        try {
            TimeUnit.MILLISECONDS.sleep(800);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class DaemonThreadFactory implements ThreadFactory {
    private static final String TAG = DaemonThreadFactory.class.getSimpleName();

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        //设置线程为后台线程
        thread.setDaemon(true);
        return thread;
    }
}

class DaemonRunnable implements Runnable{
    private static final String TAG = DaemonRunnable.class.getSimpleName();

    @Override
    public void run() {
        try {
            while (true){
                TimeUnit.MILLISECONDS.sleep(100);
                LogUtils.i(TAG,Thread.currentThread() + "" + this);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
