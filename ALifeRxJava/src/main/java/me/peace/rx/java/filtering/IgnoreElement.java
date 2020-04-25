package me.peace.rx.java.filtering;

import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Action;
import me.peace.rx.LogUtils;

public class IgnoreElement {
    private static final String TAG = "IgnoreElement";

    public static void main(String[] args) {
        IgnoreElement ignoreElement = new IgnoreElement();
        ignoreElement.ignoreElement();
    }

    public void ignoreElement(){
        Single.timer(1, TimeUnit.SECONDS).ignoreElement()
            .doOnComplete(new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ignoreElement complete");
                }
            }).blockingAwait();
    }
}
