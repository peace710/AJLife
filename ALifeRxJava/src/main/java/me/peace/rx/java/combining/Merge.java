package me.peace.rx.java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Merge {
    private static final String TAG = "Merge";

    public static void main(String[] args) {
        Merge merge = new Merge();
        merge.merge();
    }

    public void merge(){
        Observable.just(1,2,3).mergeWith(Single.just(4)).mergeWith(Observable.just(5,6,7)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator merge: integer = [" + integer + "]");
            }
        });
    }
}
