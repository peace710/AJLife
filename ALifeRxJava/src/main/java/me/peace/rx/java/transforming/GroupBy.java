package me.peace.rx.java.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.observables.GroupedObservable;
import me.peace.rx.LogUtils;

public class GroupBy {
    private static final String TAG = "GroupBy";

    public static void main(String[] args) {
        GroupBy groupBy = new GroupBy();
        groupBy.groupBy();
    }

    public void groupBy(){
        Observable<String> animals = Observable.just(
            "Android", "Xiaomi", "Apple", "Huawei", "Sony");

        animals.groupBy(new Function<String, String>() {
            @Override
            public String apply(String s) throws Throwable {
                return String.valueOf(s.charAt(0));
            }
        }, new Function<String, String>() {
            @Override
            public String apply(String s) throws Throwable {
                return s.toUpperCase();
            }
        }).concatMapSingle(new Function<GroupedObservable<String, String>, SingleSource<?>>() {
            @Override
            public SingleSource<?> apply(GroupedObservable<String, String> observable) throws Throwable {
                return observable.toList();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator groupBy: o = [" +  o + "]");
            }
        });
    }
}
