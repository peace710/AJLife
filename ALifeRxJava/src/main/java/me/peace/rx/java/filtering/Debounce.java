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

public class Debounce {
    private static final String TAG = "Debounce";

    public static void main(String[] args) {
        Debounce debounce = new Debounce();
        debounce.debounce();
    }

    public void debounce(){
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
        }).subscribeOn(Schedulers.io()).debounce(1, TimeUnit.SECONDS)
        .blockingSubscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce complete");
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
