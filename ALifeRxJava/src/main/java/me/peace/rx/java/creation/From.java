package me.peace.rx.java.creation;

import org.reactivestreams.Publisher;
import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.function.BiFunction;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableObserver;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class From {
    private static final String TAG = "From";
    public static void main(String[] args) {
        From from = new From();
        from.fromIterable();
        from.fromArray();
        from.fromCallable();
        from.fromAction();
        from.fromRunnable();
        from.fromFuture();
        from.fromReactiveType();
    }

    public void print(String content){
        LogUtils.d(TAG,content);
    }
    
    public void fromIterable(){
        print("fromIterable");
        ArrayList<String> array = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g"));
        Observable.fromIterable(array).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromIterable: s = [" + s + "]");
            }
        });

        Flowable.fromIterable(array).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Flowable Operator fromIterable: s = [" + s + "]");
            }
        });
    }
    
    public void fromArray(){
        print("fromArray");
        //只支持引用数组
        Integer[] array = new Integer[8];
        for (int i = 0 ;i < array.length ;i++){
            array[i] = i;
        }
        Observable.fromArray(array).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                print("RxJava Observable Operator fromArray: s = [" + integer + "]");
            }
        });

        Flowable.fromArray(array).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                print("RxJava Flowable Operator fromArray: s = [" + integer + "]");
            }
        });
    }

    public void fromCallable(){
        print("fromCallable");
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        };

        Observable.fromCallable(callable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromArray: s = [" + s + "]");
            }
        });

        Flowable.fromCallable(callable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Flowable Operator fromArray: s = [" + s + "]");
            }
        });

        Maybe.fromCallable(callable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Maybe Operator fromArray: s = [" + s + "]");
            }
        });

        Single.fromCallable(callable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Single Operator fromArray: s = [" + s + "]");
            }
        });

        Completable.fromCallable(callable).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                print("RxJava Completable Operator fromCallable is done");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void fromAction(){
        print("fromAction");
        Action action = new Action() {
            @Override
            public void run() throws Throwable {
                print("RxJava fromAction");
            }
        };
        
        Maybe.fromAction(action).subscribe();

        Completable.fromAction(action).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                print("RxJava Completable Operator fromAction");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void fromRunnable(){
        print("fromRunnable");
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                print("RxJava fromRunnable");
            }
        };

        Maybe.fromRunnable(runnable).subscribe();

        Completable.fromRunnable(runnable).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                print("RxJava Completable Operator fromRunnable");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void fromFuture(){
        print("fromFuture");
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Future<String> future = executor.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        },1, TimeUnit.SECONDS);
        Observable.fromFuture(future).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromFuture: s = [" + s + "]");
            }
        });

        Flowable.fromFuture(future).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Flowable Operator fromFuture: s = [" + s + "]");
            }
        });

        Maybe.fromFuture(future).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Maybe Operator fromFuture: s = [" + s + "]");
            }
        });

        Single.fromFuture(future).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Single Operator fromFuture: s = [" + s + "]");
            }
        });

        Completable.fromFuture(future).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                print("RxJava Single Completable fromFuture");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
    }

    public void fromReactiveType(){
        CompletableFuture<Integer> future =  CompletableFuture.<Integer>completedFuture(10086);

        future =
            CompletableFuture.<Integer>completedFuture(10086).handleAsync(new BiFunction<Integer,
            Throwable, Integer>() {
            @Override
            public Integer apply(Integer integer, Throwable throwable) {
                return integer * 10 ;
            }
        });

        Observable.fromPublisher(new CompletableFuturePublisher(future)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                print("RxJava Observable Operator fromReactiveType: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                print("RxJava Observable Operator fromReactiveType: throwable = [" + throwable + "]");
            }
        });
    }

    class CompletableFuturePublisher implements Publisher<Integer>{
        CompletableFuture<Integer> future;

        public CompletableFuturePublisher(CompletableFuture<Integer> future) {
            this.future = future;
        }

        @Override
        public void subscribe(Subscriber<? super Integer> subscriber) {
//            future = null;
            subscriber.onSubscribe(new CompletableFutureSubscription(subscriber,future));
        }
    }

    class CompletableFutureSubscription implements Subscription{
        Subscriber<? super Integer> publisher;
        CompletableFuture<Integer> future;

        public CompletableFutureSubscription(Subscriber<? super Integer> publisher, CompletableFuture<Integer> future) {
            this.publisher = publisher;
            this.future = future;
        }

        @Override
        public void request(long n) {
            try {
                print("CompletableFutureSubscription request() onNext");
                publisher.onNext(future.get(5,TimeUnit.SECONDS));
                print("CompletableFutureSubscription request() onComplete");
                publisher.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                print("CompletableFutureSubscription request() onError");
                publisher.onError(e);
            }
        }

        @Override
        public void cancel() {

        }
    }
}
