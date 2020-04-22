package me.peace.rx.java.conditional;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class DefaultIfEmpty {
    private static final String TAG = "DefaultIfEmpty";

    public static void main(String[] args) {
        DefaultIfEmpty defaultIfEmpty = new DefaultIfEmpty();
        defaultIfEmpty.defaultIfEmpty();
    }

    public void defaultIfEmpty(){
        Observable.empty().defaultIfEmpty("default object").subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defaultIfEmpty: o = [" + o + "]");
            }
        });
    }
}
