package me.peace.rx.java.aggregate;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Reduce {
    private static final String TAG = "Reduce";

    public static void main(String[] args) {
        Reduce reduce = new Reduce();
        reduce.reduce();
    }

    public void reduce(){
        Observable.range(1,5)
            .reduce(new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b) throws Throwable {
                    return a * b;
                }
            }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator reduce: integer = [" + integer + "]");
            }
        });
    }
}
