package me.peace.rx.java.creation;

import java.util.concurrent.Callable;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.BackpressureStrategy;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.FlowableEmitter;
import io.reactivex.rxjava3.core.FlowableOnSubscribe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Cancellable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Create {
    private static final String TAG = "Create";
    public static void main(String[] args) {
        Create create = new Create();
        create.create();
    }

    public void create(){
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ObservableOnSubscribe<String> handler = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Future<String> future = service.schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        emitter.onNext("hello");
                        emitter.onNext("world");
                        emitter.onComplete();
                        return null;
                    }
                },1, TimeUnit.SECONDS);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {
                        future.cancel(false);
                    }
                });
            }
        };
        Observable.create(handler).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator create: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator create complete");
            }
        });

        FlowableOnSubscribe<String> flowable = new FlowableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull FlowableEmitter<String> emitter) throws Throwable {
                Future<String> future = service.schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        emitter.onNext("hello");
                        emitter.onNext("world");
                        emitter.onComplete();
                        return null;
                    }
                },1, TimeUnit.SECONDS);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {
                        future.cancel(false);
                    }
                });
            }
        };

        Flowable.create(flowable,BackpressureStrategy.BUFFER).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Flowable Operator create: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Flowable Operator create complete");
            }
        });

        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        service.shutdown();
    }
}
