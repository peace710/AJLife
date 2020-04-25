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

public class Sample {
    private static final String TAG = "Sample";

    public static void main(String[] args) {
        Sample sample = new Sample();
        sample.sample();
    }

    public void sample(){
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
        }).subscribeOn(Schedulers.io()).sample(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample complete");
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
