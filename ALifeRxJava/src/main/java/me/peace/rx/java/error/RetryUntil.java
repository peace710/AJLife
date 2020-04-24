package me.peace.rx.java.error;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.LongAdder;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BooleanSupplier;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class RetryUntil {
    private static final String TAG = "RetryUntil";

    public static void main(String[] args) {
        RetryUntil retryUntil = new RetryUntil();
        retryUntil.retryUntil();
    }

    public void retryUntil(){
        LongAdder error = new LongAdder();
        Observable<Long> source = Observable.interval(0,1, TimeUnit.SECONDS)
            .flatMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long aLong) throws Throwable {
                    if (aLong >= 2) return Observable.error(new NullPointerException("Something is wrong!"));
                    return Observable.just(aLong);
                }
            }).doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    error.increment();
                }
            });

        source.retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Throwable {
                return error.intValue() >= 3;
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryUntil: aLong = [" + aLong + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryUntil: throwable = [" + throwable + "]");
            }
        });
    }
}
