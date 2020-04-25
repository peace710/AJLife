package me.peace.rx.java.aggregate;

import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class ToMap {
    private static final String TAG = "ToMap";

    public static void main(String[] args) {
        ToMap toMap = new ToMap();
        toMap.toMap();
    }

    public void toMap(){
        Observable.just(1,2,3,4).
            toMap(new Function<Integer, Integer>() {
                @Override
                public Integer apply(Integer integer) throws Throwable {
                    return integer;
                }
            }, new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) throws Throwable {
                    return integer % 2 == 0 ? "even":"odd";
                }
            }).subscribe(new Consumer<Map<Integer, String>>() {
            @Override
            public void accept(Map<Integer, String> map) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toMap: map = [" + map + "]");
            }
        });
    }
}
