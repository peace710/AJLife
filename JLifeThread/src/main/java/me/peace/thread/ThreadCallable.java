package me.peace.thread;

import java.util.ArrayList;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class ThreadCallable {
    private static final String TAG = ThreadCallable.class.getSimpleName();

    public static void main(String[] args) {
        callableTask();
    }

    private static void callableTask(){
        ExecutorService service = Executors.newCachedThreadPool();
        ArrayList<Future<String>> list = new ArrayList<>();
        for (int i = 0 ;i < 10 ; i++){
            //通过Executor submit方法提交这个异步任务，会产生Future对象
            list.add(service.submit(new TaskCallable(i)));
        }
        for (Future<String> future : list){
            try {
                //isDone方法查询Future是否完成
                LogUtils.i(TAG,"future is done ==> " + future.isDone());
                //isCancelled方法查询Future是否退出
                LogUtils.i(TAG,"future is cancelled ==> " + future.isCancelled());
                //通过调用get方法,获取异步线程的值,调用这个方法会等待返回结果,会产生阻塞现象
                //get方法里还可以传入等待超时时间,get(long timeout, TimeUnit unit)
                //这样最多只会等待设定的超时时长,避免了阻塞
                LogUtils.i(TAG,"future value ==> " + future.get());
            } catch (InterruptedException e) {
                e.printStackTrace();
            } catch (ExecutionException e) {
                e.printStackTrace();
            } finally {
                service.shutdown();
            }
        }
    }
}


//带返回值的异步线程,实现Callable接口,重写call方法
class TaskCallable implements Callable<String>{
    private int id;

    public TaskCallable(int id) {
        this.id = id;
    }


    //call方法实现在异步线程中,然后return结果出来
    //返回值类型为Callable定义的泛型
    @Override
    public String call() throws Exception {
        return "Callable task id is " + id;
    }


}
