package me.peace.rx.java.combining;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class CombinLatest {
    private static final String TAG = "CombinLatest";

    public static void main(String[] args) {
        CombinLatest combinLatest = new CombinLatest();
        combinLatest.combinlatest();
    }

    public void combinlatest(){
        Observable<String> observable1 = Observable.just("8","100","10086");
        Observable<String> observable2 = Observable.just("f");
        Observable.combineLatest(observable1, observable2,
            new BiFunction<String, String, String>() {
            @Override
            public String apply(String t1, String t2) throws Throwable {
                return t1 + t2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator combinlatest: s = [" + s + "]");
            }
        });
    }
}
