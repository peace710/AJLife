package me.peace.rx.java.error;

import java.io.IOException;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class DoOnError {
    private static final String TAG = "DoOnError";

    public static void main(String[] args) {
        DoOnError doOnError = new DoOnError();
        doOnError.doOnError();
    }

    public void doOnError(){
        Observable.error(new NullPointerException("something is wrong")).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator doOnError: throwable = [" + throwable.getMessage() + "]");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator doOnError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator doOnError(subscribe): throwable = [" + throwable.getMessage() + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator doOnError: complete");
            }
        });

    }
}
