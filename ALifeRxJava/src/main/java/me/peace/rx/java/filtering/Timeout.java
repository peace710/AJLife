package me.peace.rx.java.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Timeout {
    private static final String TAG = "Timeout";

    public static void main(String[] args) {
        Timeout timeOut = new Timeout();
        timeOut.timeout();
    }

    public void timeout(){
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(800);
                emitter.onNext("B");
                sleep(400);
                emitter.onNext("C");
                sleep(1200);
                emitter.onNext("D");
                emitter.onComplete();
            }
        }).timeout(1, TimeUnit.SECONDS)
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout complete");
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
