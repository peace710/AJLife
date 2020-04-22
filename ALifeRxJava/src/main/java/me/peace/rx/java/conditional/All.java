package me.peace.rx.java.conditional;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class All {
    private static final String TAG = "All";

    public static void main(String[] args) {
        All all = new All();
        all.all();
    }

    public void all(){
        Observable.just(1,2,3,4,5,6).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer > 3;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator all: b = [" + b + "]");
            }
        });

        Observable.just(1,2,3,4,5,6).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer > 0;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator all: b = [" + b + "]");
            }
        });
    }
}
