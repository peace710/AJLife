package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadUncaughtException {
    private static final String TAG = ThreadUncaughtException.class.getSimpleName();

    public static void main(String[] args) {
        exceptionHandler();
        defaultExceptionHandler();
    }

    private static void exceptionHandler(){
        ExecutorService service = Executors.newCachedThreadPool(new ExceptionHandlerFactory());
        service.execute(new ExceptionThread());
        service.shutdown();
    }

    private static void defaultExceptionHandler(){
        // 设置默认的异常处理器
        // 如果线程有专用的异常处理器，就用专用的异常处理器处理，没有则用默认的异常处理器
        Thread.setDefaultUncaughtExceptionHandler(new DefaultUncaughtExceptionHandler());
        // 获取线程默认的异常处理器
        Thread.getDefaultUncaughtExceptionHandler();
        ExecutorService service = Executors.newCachedThreadPool();
        service.execute(new ExceptionThread());
        service.shutdown();
        ExecutorService customService =
            Executors.newCachedThreadPool(new ExceptionHandlerFactory());
        customService.execute(new ExceptionThread());
        customService.shutdown();
    }
}

class ExceptionThread implements Runnable{
    @Override
    public void run() {
       String a = "abc";
       String b = a.substring(5);
    }
}

class ExceptionHandlerFactory implements ThreadFactory{
    private static final String TAG = ExceptionHandlerFactory.class.getSimpleName();
    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        //将自定义的异常处理器加入线程对象中
        thread.setUncaughtExceptionHandler(new UncaughtExceptionHandler());
        //获取异常处理器
        LogUtils.i(TAG,"UncaughtExceptionHandler is " + thread.getUncaughtExceptionHandler());
        return thread;
    }
}

//异常处理器用于捕获异常，实现Thread.UncaughtExceptionHandler接口可以捕获线程run方法里产生的错误
class UncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    private static final String TAG = UncaughtExceptionHandler.class.getSimpleName();

    //重写uncaughtException，对捕获到的线程和异常做处理
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogUtils.i(TAG,
            "UncaughtExceptionHandler thread = " + t + ",throwable  = " + e.getMessage());
    }
}

class DefaultUncaughtExceptionHandler implements Thread.UncaughtExceptionHandler{
    private static final String TAG = DefaultUncaughtExceptionHandler.class.getSimpleName();

    //重写uncaughtException，对捕获到的线程和异常做处理
    @Override
    public void uncaughtException(Thread t, Throwable e) {
        LogUtils.i(TAG,
            "DefaultUncaughtExceptionHandler thread = " + t + ",throwable  = " + e.getMessage());
    }
}
