package me.peace.rx.java.creation;

import java.io.IOException;
import java.util.concurrent.Callable;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Error {
    private static final String TAG = "Error";

    public static void main(String[] args) {
        Error error = new Error();
        error.error();
        error.onErrorResumeNext();
    }

    public void error(){
        Observable.<String>error(new NullPointerException()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error:" + throwable);
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error complete");
            }
        });
    }

    public void onErrorResumeNext(){
        Observable.range(1,10).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Observable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        if (Math.random() < 0.5){
                            throw new IOException();
                        }
                        throw new IllegalArgumentException();
                    }
                }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
                    @Override
                    public ObservableSource<? extends String> apply(Throwable throwable) throws Throwable {
                        if (throwable instanceof IllegalArgumentException){
                            return Observable.empty();
                        }
                        return Observable.error(throwable);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator OnErrorResumeNext: s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator OnErrorResumeNext error:" + throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator OnErrorResumeNext complete");
                    }
                });
            }
        });
    }
}
