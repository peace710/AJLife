package me.peace.thread.semaphore;


import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import me.peace.thread.LogUtils;

public class SemaphoreDemo {
    private static final String TAG = "SemaphoreDemo";
    final static int SIZE = 25;

    public static void main(String[] args) throws Exception{
        final Pool<Fat> pool = new Pool<>(Fat.class,SIZE);
        ExecutorService service = Executors.newCachedThreadPool();
        for (int i = 0 ; i < SIZE ;i++) {
            service.execute(new CheckOutTask<Fat>(pool));
        }
        LogUtils.i(TAG,"all CheckOutTasks created");
        List<Fat> list = new ArrayList<>();
        for (int i = 0 ; i < SIZE ;i++){
            Fat f = pool.checkOut();
            LogUtils.i(TAG,i + " : main() thread checked out");
            f.operation();
            list.add(f);
        }
        Future<?> blocked = service.submit(new Runnable() {
            @Override
            public void run() {
                try {
                    pool.checkOut();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                    LogUtils.i(TAG,"checkOut() interrupted");
                }
            }
        });
        TimeUnit.SECONDS.sleep(2);
        blocked.cancel(true);
        LogUtils.i(TAG,"Checking in objects in " + list);
        for (Fat f :list){
            pool.checkIn(f);
        }
        for (Fat f :list){
            pool.checkIn(f);
        }
        service.shutdown();
    }
}
