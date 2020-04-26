package me.peace.rx.java.transforming;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class SwitchMap {
    private static final String TAG = "SwitchMap";

    public static void main(String[] args) {
        SwitchMap switchMap = new SwitchMap();
        switchMap.switchMap();
    }

    public void switchMap(){
        Observable.interval(0,1, TimeUnit.SECONDS)
            .switchMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long a) throws Throwable {
                    return Observable.interval(0,750,TimeUnit.MILLISECONDS)
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long b) throws Throwable {
                                return a;
                            }
                        });
                }
            }).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long value) throws Throwable {
                return value < 3;
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long value) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator switchMap: value = [" +  value + "]");
            }
        });
    }
}
