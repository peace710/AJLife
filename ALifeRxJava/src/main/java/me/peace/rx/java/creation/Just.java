package me.peace.rx.java.creation;

import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Just {
    private static final String TAG = "Just";
    public static void main(String[] args) {
        Just just = new Just();
        just.just();
        just.justArgs();
        just.justFlowable();
        just.justMaybe();
        just.justSingle();
    }

    public void just(){
        String greeting = "Hello world!";
        Observable<String> observable = Observable.just(greeting);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator just: s = [" + s + "]");
            }
        });
    }

    public void justArgs(){
        Observable<String> observable = Observable.just("android","iphone","xiaomi");
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator justArgs: s = [" + s + "]");
            }
        });
    }

    public void justFlowable(){
        Flowable<String> flowable = Flowable.just("Flowable");
        flowable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Flowable Operator just: s = [" + s + "]");
            }
        });
    }

    public void justMaybe(){
        Maybe<String> maybe = Maybe.just("Maybe");
        maybe.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Maybe Operator just: s = [" + s + "]");
            }
        });
    }

    public void justSingle(){
        Single<String> single = Single.just("Single");
        single.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Single Operator just: s = [" + s + "]");
            }
        });
    }
}


