package me.peace.rx.java.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class Skip {
    private static final String TAG = "Skip";

    public static void main(String[] args) {
        Skip skip = new Skip();
        skip.skip();
        skip.skipUntil();
        skip.skipWhile();
        skip.skipLast();
    }

    public void skip(){
        Observable.just(1,2,3,4,5,6).skip(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skip: integer = [" + integer + "]");
            }
        });
    }

    public void skipUntil(){
        Observable.interval(1,TimeUnit.SECONDS).skipUntil(Observable.just("3").delay(3,
            TimeUnit.SECONDS)).subscribe(new Consumer<Object>() {
                @Override
                public void accept(Object integer) throws Throwable {
                    LogUtils.d(TAG, "RxJava Observable Operator skipUntil: integer = [" + integer + "]");
                }
            });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void skipWhile(){
        Observable.interval(1,TimeUnit.SECONDS).skipWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 6;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skipWhile: aLong = [" + aLong + "]");
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void skipLast(){
        Observable.just(1,2,3).skipLast(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skipLast: integer = [" + integer + "]");
            }
        });
    }
}
