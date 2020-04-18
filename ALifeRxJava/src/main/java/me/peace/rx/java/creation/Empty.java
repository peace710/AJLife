package me.peace.rx.java.creation;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Empty {
    private static final String TAG = "Empty";

    public static void main(String[] args) {
        Empty empty = new Empty();
        empty.empty();
    }

    public void empty(){
        Observable.<String>empty().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator empty: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator empty error");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator empty complete");
            }
        });
    }
}
