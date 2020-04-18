package me.peace.rx.java.creation;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import me.peace.rx.LogUtils;

public class Defer {
    private static final String TAG = "Defer";
    public static void main(String[] args) {
        Defer defer = new Defer();
        defer.defer();
    }

    public void defer(){
        Observable<Long> observable = Observable.defer(new Supplier<ObservableSource<? extends Long>>() {
            @Override
            public ObservableSource<? extends Long> get() throws Throwable {
                long time = System.currentTimeMillis();
                return Observable.just(time);
            }
        });

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defer: time = [" + time + "]");
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defer: time = [" + time + "]");
            }
        });
    }
}
