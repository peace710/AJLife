package me.peace.rx.java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Zip {
    private static final String TAG = "Zip";

    public static void main(String[] args) {
        Zip zip = new Zip();
        zip.zip();
    }

    public void zip(){
        Observable.zip(Observable.just("Hello"), Observable.just("World"),
            new BiFunction<String
        , String, String>() {
                @Override
                public String apply(String s, String s2) throws Throwable {
                    return s + s2;
                }
            }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator zip: s = [" + s + "]");
            }
        });

        Observable.just("Hello").zipWith(Observable.just("World"), new BiFunction<String, String,
            String>() {
            @Override
            public String apply(String s, String s2) throws Throwable {
                return s + s2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator zipWith: s = [" + s + "]");
            }
        });
    }
}
