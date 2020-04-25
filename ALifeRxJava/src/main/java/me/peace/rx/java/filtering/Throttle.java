package me.peace.rx.java.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.schedulers.Schedulers;
import me.peace.rx.LogUtils;

public class Throttle {
    private static final String TAG = "Throttle";

    public static void main(String[] args) {
        Throttle throttle = new Throttle();
        throttle.throttleFirst();
        throttle.throttleLast();
        throttle.throttleLatest();
        throttle.throttleWithTimeout();
    }

    public void throttleFirst(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleFirst(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst complete");
                }
            });
    }

    public void throttleLast(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleLast(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast complete");
                }
            });
    }

    public void throttleLatest(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleLatest(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest complete");
                }
            });
    }

    public void throttleWithTimeout(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(1500);
                emitter.onNext("B");
                sleep(500);
                emitter.onNext("C");
                sleep(250);
                emitter.onNext("D");
                sleep(2000);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleWithTimeout(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout complete");
                }
            });
    }

    private void sleep(long time){
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
