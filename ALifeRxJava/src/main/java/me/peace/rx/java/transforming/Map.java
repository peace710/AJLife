package me.peace.rx.java.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Map {
    private static final String TAG = "Map";

    public static void main(String[] args) {
        Map map = new Map();
        map.map();
    }

    public void map(){
        Observable.just(1,2,3).map(new Function<Integer,Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer * integer;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator map: integer = [" +  integer + "]");
            }
        });
    }
}
