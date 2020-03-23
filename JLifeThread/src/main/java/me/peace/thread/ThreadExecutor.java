package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadExecutor {
    private static final String TAG = ThreadExecutor.class.getSimpleName();

    public static void main(String[] args) {
        moreThread();
        cachedThread();
        fixedThread();
        singleThread();
    }

    private static void moreThread(){
        for (int i = 0 ; i < 5 ; i++){
            new Thread(new TaskRunnable()).start();
        }
        LogUtils.i(TAG,"waiting moreThread");
    }


    /**
     * 使用Executor管理Thread对象，简化并发编程
     */
    private static void cachedThread(){
        //使用Executors构建ExecutorService对象
        // newCacheThreadPool会为每个runnable创建一个线程
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 5 ; i++){
            service.execute(new TaskRunnable());
        }
        LogUtils.i(TAG,"waiting cachedThread");
        //关闭线程执行器，防止后续新任务提交给Executor执行
        service.shutdown();
    }

    private static void fixedThread(){
        // newFixedThreadPool控制线程池里的线程数量，减少创建线程的开销
        // 回收停止的旧线程给新的任务使用
        ExecutorService service = Executors.newFixedThreadPool(3);
        for (int i = 0 ; i < 5 ; i++){
            service.execute(new TaskRunnable());
        }
        LogUtils.i(TAG,"waiting fixedThread");
        service.shutdown();
    }

    private static void singleThread(){
        // 线程数量为1的newFixedThreadPool
        ExecutorService service = Executors.newSingleThreadExecutor();
        for (int i = 0 ; i < 5 ; i++){
            service.execute(new TaskRunnable());
        }
        LogUtils.i(TAG,"waiting singleThread");
        service.shutdown();
    }
}
