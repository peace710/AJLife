package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class ElementAtOrError {
    private static final String TAG = "ElementAtOrError";

    public static void main(String[] args) {
        ElementAtOrError elementAtOrError = new ElementAtOrError();
        elementAtOrError.elementAtOrError();
    }

    public void elementAtOrError(){
        Observable.just(1,2,3,4).elementAtOrError(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAtOrError: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAtOrError: throwable = [" + throwable + "]");
            }
        });
    }
}
