package me.peace.thread;

import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadLocalValue {
    private static final String TAG = ThreadLocalValue.class.getSimpleName();

    public static void main(String[] args) {
        threadLocal(5);
    }

    private static void threadLocal(int nums){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < nums ;i++) {
            service.execute(new ThreadLocalRunnable(i));
        }
        service.shutdown();
    }
}

class ThreadLocalRunnable implements Runnable{
    private static final String TAG = ThreadLocalRunnable.class.getSimpleName();
    private int id;
    int count = 0;

    public ThreadLocalRunnable(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        while (count < 10){
            ThreadLocalValueTool.increment();
            LogUtils.i(TAG,
                "ThreadLocalRunnable[" + id + "]["+count+"]=" + ThreadLocalValueTool.get());
            count++;
        }
    }
}

class ThreadLocalValueTool{
    //线程本地存储，每个线程本地都会有个副本，修改不会相互影响
    //使用ThreadLocal类，重写initialValue方法，提供初始值
    private static ThreadLocal<Integer> value = new ThreadLocal<Integer>(){
        @Override
        protected Integer initialValue() {
            return new Random().nextInt(10000);
        }
    };

    public static void increment(){
        value.set(value.get() + 1);
    }

    public static int get(){
        return value.get();
    }
}
