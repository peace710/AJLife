package me.peace.rx.java.aggregate;

import java.util.StringJoiner;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import me.peace.rx.LogUtils;

public class Collect {
    private static final String TAG = "Collect";

    public static void main(String[] args) {
        Collect collect = new Collect();
        collect.collect();
    }

    public void collect(){
        Observable.just("1","2","3","4")
        .collect(new Supplier<StringJoiner>() {
            @Override
            public StringJoiner get() throws Throwable {
                return new StringJoiner(" \uD83D\uDD96 ");
            }
        }, new BiConsumer<StringJoiner, String>() {
            @Override
            public void accept(StringJoiner stringJoiner, String s) throws Throwable {
                stringJoiner.add(s);
            }
        }).map(new Function<StringJoiner, String>() {
            @Override
            public String apply(StringJoiner stringJoiner) throws Throwable {
                return stringJoiner.toString();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator collect: s = [" + s + "]");
            }
        });
    }
}
