package me.peace.thread;

//实现线程的两种基本方式，继承Thread类和实现Runnable接口
public class ThreadBasicUse {
    private static final String TAG = ThreadBasicUse.class.getSimpleName();

    public static void main(String[] args) {
        useClassThread();
        useInterfaceRunnable();
        runMainThread();
    }

    private static void useClassThread(){
        //构造thread实例，调用start方法运行
        new CarRunThread().start();
    }


    private static void useInterfaceRunnable(){
        //构造thread实例，传入实现的runnable接口，调用start方法运行
        new Thread(new DriveRunnable()).start();
    }

    private static void runMainThread(){
        //分配给主线程实现的runnable
        TaskRunnable task = new TaskRunnable();
        task.run();
    }
}

//继承Thread类,重写run方法
class CarRunThread extends Thread{
    private static final String TAG = CarRunThread.class.getSimpleName();

    @Override
    public void run() {
        super.run();
        LogUtils.i(TAG,"car is running(extends thread) ");
    }
}

//实现Runnable接口，重写run方法
class DriveRunnable implements Runnable{
    private static final String TAG = DriveRunnable.class.getSimpleName();

    @Override
    public void run() {
        LogUtils.i(TAG,"now is driving(implements runnable)");
    }
}


