package me.peace.rx.java.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Scan {
    private static final String TAG = "Scan";

    public static void main(String[] args) {
        Scan scan = new Scan();
        scan.scan();
    }

    public void scan(){
        Observable.just(5,3,8,1,7)
            .scan(0,new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b) throws Throwable {
                    return a + b;
                }
            }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator scan: integer = [" +  integer + "]");
            }
        });
    }
}
