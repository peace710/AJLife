package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

public class ThreadInterrupted {
    private static final String TAG = ThreadInterrupted.class.getSimpleName();

    public static void main(String[] args) {
//        threadCancel(true);
        threadInterrupted();
    }

    private static void threadInterrupted(){
        Runnable runnable = new CheckThreadInterrupted();
        Thread thread = new Thread(runnable);
        thread.start();
        try {
            TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //使线程进入中断异常
        thread.interrupt();
    }

    private static void threadCancel(boolean mayInterruptIfRunning){
        ExecutorService service = Executors.newCachedThreadPool();
        Runnable runnable = new CheckThreadInterrupted();
        // Executors submit提交Runnable对象
        // 获取Future对象控制线程中断
        Future<?> future = service.submit(runnable);
        try {
            TimeUnit.SECONDS.sleep(5);
            //调用cancel方法中断线程
            future.cancel(mayInterruptIfRunning);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}

class CheckThreadInterrupted implements Runnable{
    private static final String TAG = CheckThreadInterrupted.class.getSimpleName();

    @Override
    public void run() {
        //Thread.interrupted获取线程中断标志的值
        while (!Thread.interrupted()){
            try {
                LogUtils.i(TAG,"current timestamp --> " + System.currentTimeMillis());
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                LogUtils.i(TAG,"Thread into InterruptedException");
                e.printStackTrace();
                //重设当前线程的中断的标志为true来结束循环
                Thread.currentThread().interrupt();
            }
        }
        LogUtils.i(TAG,"Thread is interrupted");
    }
}
