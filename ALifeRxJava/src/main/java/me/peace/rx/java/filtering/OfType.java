package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class OfType {
    private static final String TAG = "OfType";

    public static void main(String[] args) {
        OfType ofType = new OfType();
        ofType.ofType();
    }

    public void ofType(){
        Observable.just(1.0,"22",2f,12L,5).ofType(Integer.class)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ofType: integer = [" + integer + "]");
                }
            });
    }
}
