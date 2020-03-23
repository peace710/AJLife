package me.peace.thread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPriority {
    private static final String TAG = ThreadPriority.class.getSimpleName();

    public static void main(String[] args) {
        priority();
    }

    private static void priority(){
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < 5 ;i++){
            service.execute(new PriorityRunnable(Thread.MIN_PRIORITY));
        }
        service.execute(new PriorityRunnable(Thread.MAX_PRIORITY));
        service.shutdown();
    }
}

class PriorityRunnable implements Runnable{
    private static final String TAG = PriorityRunnable.class.getSimpleName();

    private int count = 5;
    /***
     * volatile是一个类型修饰符。
     * 作用是确保本条指令不会因编译器的优化而省略。
     * 保证了不同线程这个变量进行操作时的可见性
     * 即一个线程修改某个变量值，这新值对于其他线程是立即可见的（可见性）
     * 禁止进行指令重排序（有序性）
     * volatile只能保证对单次读/写的原子性
     */
    private volatile double d;
    private int priority;

    public PriorityRunnable(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        //用于打印线程的名称、优先级和线程所属的线程组
        return "PriorityRunnable{" +
            Thread.currentThread() +
            "," +
            "count=" + count +
            '}';
    }


    @Override
    public void run() {
        //用于设置线程的优先级
        Thread.currentThread().setPriority(priority);
        while (true){
            //只有线程运行开销比较大的时候，才能体现的优先级的效果
            for (int i = 1 ;i < 1000000000 ;i++){
                d += (Math.PI + Math.E) / (double)i;
                if (i %  1000 == 0){
                    Thread.yield();
                }
            }
            LogUtils.i(TAG,this.toString());
            if (--count == 0) return;
        }
    }
}
