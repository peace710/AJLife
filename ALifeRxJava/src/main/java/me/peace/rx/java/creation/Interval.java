package me.peace.rx.java.creation;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Interval {
    private static final String TAG = "Interval";
    public static void main(String[] args) {
        Interval interval = new Interval();
        interval.interval();
    }

    public void interval(){
        Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator interval: time = [" + System.currentTimeMillis() + "," + time +"]");
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
