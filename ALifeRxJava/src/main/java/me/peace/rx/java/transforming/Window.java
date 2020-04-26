package me.peace.rx.java.transforming;

import java.util.StringJoiner;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Window {
    private static final String TAG = "Window";

    public static void main(String[] args) {
       Window window = new Window();
       window.window();
    }

    public void window(){
        Observable.range(1,10)
            .window(2,3).flatMapSingle(new Function<Observable<Integer>, SingleSource<?>>() {
            @Override
            public SingleSource<?> apply(Observable<Integer> observable) throws Throwable {
                return observable.map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        return String.valueOf(integer);
                    }
                }).reduce(new StringJoiner(",", "[", "]"), new BiFunction<StringJoiner, String, StringJoiner>() {
                    @Override
                    public StringJoiner apply(StringJoiner stringJoiner, String s) throws Throwable {
                        return stringJoiner.add(s);
                    }
                });
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator window: o = [" +  o + "]");
            }
        });

    }
}
