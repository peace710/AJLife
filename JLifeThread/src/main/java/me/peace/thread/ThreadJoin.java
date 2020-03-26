package me.peace.thread;

public class ThreadJoin {
    private static final String TAG = ThreadJoin.class.getSimpleName();

    public static void main(String[] args) {
        netDataFillUi();
//        netDataFillUiLimit(1000);
    }

    private static void netDataFillUi(){
        Thread thread = new Thread(new NetRunnable());
        thread.start();
        LogUtils.i(TAG,"init ui views");
        try {
            //thread加入，等待加入线程执行完成，再执行接下来的代码
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i(TAG,"update ui views with data");
    }

    private static void netDataFillUiLimit(long time){
        Thread thread = new Thread(new NetRunnable());
        thread.start();
        LogUtils.i(TAG,"init ui views");
        try {
            //thread加入，等待time ms，再执行接下来的代码
            //如果在time ms内thread未执行完成，thread内容不会停止，仍会继续执行
            thread.join(time);
//            thread.interrupt();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LogUtils.i(TAG,"update ui views with data");
    }
}

class NetRunnable implements Runnable{
    private static final String TAG = NetRunnable.class.getSimpleName();

    @Override
    public void run() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
            return;
        }
        LogUtils.i(TAG,"net runnable is over");
    }
}

