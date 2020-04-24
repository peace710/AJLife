package me.peace.rx.java.error;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class OnErrorReturn {
    private static final String TAG = "OnErrorReturn";

    public static void main(String[] args) {
        OnErrorReturn onErrorReturn = new OnErrorReturn();
        onErrorReturn.onErrorReturn();
    }

    public void onErrorReturn(){
        Single.just("2A").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return Integer.parseInt(s,10);
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                if (throwable instanceof NumberFormatException) return 0;
                else throw new IllegalArgumentException();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturn: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturn: throwable = [" + throwable + "]");
            }
        });
    }
}
