package me.peace.thread;

public class TaskRunnable implements Runnable{
    private static final String TAG = TaskRunnable.class.getSimpleName();

    protected int taskNums = 0;
    private static int taskCount = 0;
    private final int id = taskCount++;

    public TaskRunnable() {
    }

    public TaskRunnable(int taskNums) {
        this.taskNums = taskNums;
    }

    public String taskStatus(){
        return "#" + id + ",task" + taskNums + " is running";
    }

    @Override
    public void run() {
        while (++taskNums <= 10){
            LogUtils.i(TAG,taskStatus());
            //线程调度器将CPU从一种线程转移到另一个线程
            Thread.yield();
        }
    }
}