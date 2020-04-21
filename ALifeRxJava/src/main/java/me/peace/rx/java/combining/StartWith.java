package me.peace.rx.java.combining;

import android.util.Log;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class StartWith {
    private static final String TAG = "StartWith";
    public static void main(String[] args) {
        StartWith startWith = new StartWith();
        startWith.startWith();
    }

    public void startWith(){
        Observable.just(10).startWith(Single.just(48)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator startWith: integer = [" + integer + "]");
            }
        });
    }
}
