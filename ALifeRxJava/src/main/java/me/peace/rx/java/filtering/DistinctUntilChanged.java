package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class DistinctUntilChanged {
    private static final String TAG = "DistinctUntilChanged";

    public static void main(String[] args) {
        DistinctUntilChanged distinctUntilChanged = new DistinctUntilChanged();
        distinctUntilChanged.distinctUntilChanged();
    }

    public void distinctUntilChanged(){
        Observable.just(1,1,2,1,2,3,3,4).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator distinctUntilChanged: integer = [" + integer + "]");
            }
        });
    }
}
