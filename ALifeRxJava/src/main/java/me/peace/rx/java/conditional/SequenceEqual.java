package me.peace.rx.java.conditional;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class SequenceEqual {
    private static final String TAG = "SequenceEqual";

    public static void main(String[] args) {
        SequenceEqual sequenceEqual = new SequenceEqual();
        sequenceEqual.sequenceEqual();
    }

    public void sequenceEqual(){
        Observable.sequenceEqual(Observable.just(1,2,3), Observable.just(1,2)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator sequenceEqual: aBoolean = [" + aBoolean +
                    "]");
            }
        });
    }
}
