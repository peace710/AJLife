package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Distinct {
    private static final String TAG = "Distinct";

    public static void main(String[] args) {
        Distinct distinct = new Distinct();
        distinct.distinct();
    }

    public void distinct(){
        Observable.just(2,3,4,4,2,1).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator distinct: integer = [" + integer + "]");
            }
        });
    }
}
