package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Last {
    private static final String TAG = "Last";

    public static void main(String[] args) {
        Last last = new Last();
        last.last();
        last.lastElement();
        last.lastOrError();
    }

    public void last(){
        Observable.just(1,2,3).last(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator last: integer = [" + integer + "]");
            }
        });
    }

    public void lastElement(){
        Observable.just(1,2,3).lastElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastElement: integer = [" + integer + "]");
            }
        });
    }

    public void lastOrError(){
        Observable.empty().lastOrError().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastOrError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastOrError: throwable = [" + throwable + "]");
            }
        });
    }
}
