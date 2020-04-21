package me.peace.rx.java.combining;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class SwitchOnNext {
    private static final String TAG = "SwitchOnNext";

    public static void main(String[] args) {
        SwitchOnNext switchOnNext = new SwitchOnNext();
        switchOnNext.switchOnNext();
    }

    public void switchOnNext(){
        Observable<Observable<String>> time = Observable.interval(1, TimeUnit.SECONDS)
            .map(new Function<Long, Observable<String>>() {
                @Override
                public Observable<String> apply(Long aLong) throws Throwable {
                    return Observable.just(String.valueOf(aLong)).map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Throwable {
                            return s;
                        }
                    });
                }
            });

        Observable.switchOnNext(time).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator switchOnNext: s = [" + s + "]");
            }
        });

        try {
            Thread.sleep(5 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
