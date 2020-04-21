package me.peace.rx.java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class MergeDelayError {
    private static final String TAG = "MergeDelayError";

    public static void main(String[] args) {
        MergeDelayError mergeDelayError = new MergeDelayError();
        mergeDelayError.mergeDelayError();
    }

    public void mergeDelayError(){
        Observable.mergeDelayError(Observable.error(new NullPointerException()),
            Observable.just(10)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator mergeDelayError: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator mergeDelayError: throwable = [" + throwable + "]");
            }
        });
    }
}
