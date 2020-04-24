package me.peace.rx.java.error;

import java.io.IOException;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class OnErrorComplete {
    private static final String TAG = "OnErrorComplete";
    public static void main(String[] args) {
        OnErrorComplete onErrorComplete = new OnErrorComplete();
        onErrorComplete.onErrorComplete();
    }

    public void onErrorComplete(){
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                throw new IOException();
            }
        }).onErrorComplete(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Throwable {
                return throwable instanceof IOException;
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator onErrorComplete complete");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorComplete: throwable = [" + throwable.getMessage() + "]");
            }
        });
    }
}
