package me.peace.rx.java.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import me.peace.rx.LogUtils;

public class IgnoreElements {
    private static final String TAG = "IgnoreElements";

    public static void main(String[] args) {
        IgnoreElements ignoreElements = new IgnoreElements();
        ignoreElements.ignoreElements();
    }

    public void ignoreElements(){
        Observable.intervalRange(1,5,1,1,TimeUnit.SECONDS).ignoreElements().doOnComplete(new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ignoreElements complete");
                }
            }).blockingAwait();
    }
}
