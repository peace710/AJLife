package me.peace.rx.java.error;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class RetryWhen {
    private static final String TAG = "RetryWhen";

    public static void main(String[] args) {
        RetryWhen retryWhen = new RetryWhen();
        retryWhen.retryWhen();
    }

    public void retryWhen(){
        Observable<Long> source = Observable.interval(0,1, TimeUnit.SECONDS)
            .flatMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long aLong) throws Throwable {
                    if (aLong >= 2) return Observable.error(new NullPointerException("Something is wrong!"));
                    return Observable.just(aLong);
                }
            });

        source.retryWhen(new Function<Observable<Throwable>, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Observable<Throwable> throwableObservable) throws Throwable {
                return throwableObservable.map(new Function<Throwable, Long>() {
                    @Override
                    public Long apply(Throwable throwable) throws Throwable {
                        return Long.valueOf(1);
                    }
                });
            }
        }).scan(new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Throwable {
                return Math.addExact(aLong,aLong2);
            }
        }).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen doOnNext: aLong = [" + aLong + "]");
            }
        }).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 3;
            }
        }).flatMapSingle(new Function<Long, SingleSource<Long>>() {
            @Override
            public SingleSource<Long> apply(Long aLong) throws Throwable {
                return Single.timer(aLong,TimeUnit.SECONDS);
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen: aLong = [" + aLong + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen complete");
            }
        });
    }
}
