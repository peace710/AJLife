package me.peace.rx.java.conditional;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class Take {
    private static final String TAG = "Take";

    public static void main(String[] args) {
        Take take = new Take();
        take.take();
        take.takeUntil();
        take.takeWhile();
        take.takeLast();
    }

    public void take(){
        Observable.just(1,2,3,4,5,6,7).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator take: integer = [" + integer + "]");
            }
        });
    }

    public void takeUntil(){
        Observable.interval(1, TimeUnit.SECONDS).takeUntil(Observable.timer(3,TimeUnit.SECONDS)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeUntil: aLong = [" + aLong + "]");
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeWhile(){
        Observable.interval(1, TimeUnit.SECONDS).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 3;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeWhile: aLong = [" + aLong + "]");
            }
        });

        try {
            Thread.sleep(10 * 1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void takeLast(){
        Observable.just(1,2,3,4,5,6,7).takeLast(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeLast: integer = [" + integer + "]");
            }
        });
    }
}
