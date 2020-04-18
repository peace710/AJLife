package me.peace.rx.java.creation;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Never {
    private static final String TAG = "Never";

    public static void main(String[] args) {
        Never never = new Never();
        never.never();
    }

    public void never(){
        Observable.<String>never().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never error");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never complete");
            }
        });
    }
}
