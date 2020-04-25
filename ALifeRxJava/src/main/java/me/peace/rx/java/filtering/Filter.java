package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class Filter {
    private static final String TAG = "Filter";

    public static void main(String[] args) {
        Filter filter = new Filter();
        filter.filter();
    }

    public void filter(){
        Observable.just(1,2,3,4,5,6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer % 3 == 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator filter: integer = [" + integer + "]");
            }
        });
    }
}
