package me.peace.rx.java.aggregate;

import java.util.Collection;
import java.util.Map;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class ToMultimap {
    private static final String TAG = "ToMultimap";

    public static void main(String[] args) {
        ToMultimap toMultimap = new ToMultimap();
        toMultimap.toMultimap();
    }

    public void toMultimap(){
        Observable.just(1,2,3,4).toMultimap(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                return integer % 2 == 0 ? "even" : "odd";
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer;
            }
        }).subscribe(new Consumer<Map<String, Collection<Integer>>>() {
            @Override
            public void accept(Map<String, Collection<Integer>> map) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toMultimap: map = [" + map + "]");
            }
        });
    }
}
