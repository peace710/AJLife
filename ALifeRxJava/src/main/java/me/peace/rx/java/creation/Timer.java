package me.peace.rx.java.creation;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Timer {
    private static final String TAG = "Timer";

    public static void main(String[] args) {
        Timer timer = new Timer();
        timer.timer();
    }

    public void timer(){
        Observable<Long> observable = Observable.timer(5, TimeUnit.SECONDS);
        observable.blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator timer: time = [" + time + "]");
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
