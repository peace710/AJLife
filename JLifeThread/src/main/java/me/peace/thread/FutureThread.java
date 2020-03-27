package me.peace.thread;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.FutureTask;
import java.util.concurrent.TimeUnit;


//FutureTask实现线程
public abstract class FutureThread<V> {
    private static final String TAG = FutureThread.class.getSimpleName();
    private FutureTask<V> task;

    public FutureThread() {
        task = new FutureTask<V>(new FutureCallable<V>(this));
    }

    public void start() {
        ExecutorService service = Executors.newSingleThreadExecutor();
        service.submit(task);
        service.shutdown();
    }

    public V getResult(V v) {
        if (task == null) {
            return v;
        }
        try {
            V ret = task.get();
            return ret == null ? v : ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
        return v;
    }

    public V getResult(V v, int i) {
        if (task == null) {
            return v;
        }
        try {
            V ret = task.get(i, TimeUnit.MILLISECONDS);
            return ret == null ? v : ret;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            task.cancel(true);
        }
        return v;
    }

    public abstract V doAction();

    private static class FutureCallable<V> implements Callable<V> {
        private FutureThread<V> worker;

        public FutureCallable(FutureThread<V> worker) {
            this.worker = worker;
        }

        @Override
        public V call() throws Exception {
            if (worker != null) {
                return worker.doAction();
            }
            return null;
        }

    }

    public static void main(String[] args) {
        FutureThread<String> worker = new FutureThread<String>() {
            @Override
            public String doAction() {
                LogUtils.i(TAG,"doAction running");
                return "Hello";
            }
        };
        worker.start();

        LogUtils.i(TAG,worker.getResult(""));
    }
}
