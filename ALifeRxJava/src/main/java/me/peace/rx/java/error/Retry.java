package me.peace.rx.java.error;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiPredicate;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Retry {
    private static final String TAG = "Retry";

    public static void main(String[] args) {
        Retry retry = new Retry();
        retry.retry();
    }

    public void retry(){
        Observable.interval(0,1, TimeUnit.SECONDS).flatMap(new Function<Long, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Long aLong) throws Throwable {
                if (aLong >= 2){
                    return Observable.error(new NullPointerException("Something is wrong"));
                }
                return Observable.just(aLong);
            }
        }).retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Throwable {
                return integer < 3;
            }
        }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retry: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retry: throwable = [" + throwable + "]");
            }
        });
    }
}
