package me.peace.rx.java.conditional;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class IsEmpty {
    private static final String TAG = "IsEmpty";

    public static void main(String[] args) {
        IsEmpty isEmpty = new IsEmpty();
        isEmpty.isEmpty();
    }

    public void isEmpty(){
        Observable.empty().isEmpty().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator isEmpty: aBoolean = [" + aBoolean +
                    "]");
            }
        });
    }
}
