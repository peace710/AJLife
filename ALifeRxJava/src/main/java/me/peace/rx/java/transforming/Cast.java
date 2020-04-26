package me.peace.rx.java.transforming;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Predicate;
import me.peace.rx.LogUtils;

public class Cast {
    private static final String TAG = "Cast";

    public static void main(String[] args) {
        Cast cast = new Cast();
        cast.cast();
    }

    public void cast(){
        Observable<Number> numbers = Observable.just(1, 4.0, 3f, 7, 12, 4.6, 5);
        numbers.filter(new Predicate<Number>() {
            @Override
            public boolean test(Number number) throws Throwable {
                return Integer.class.isInstance(number);
            }
        }).cast(Integer.class).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator cast: integer = [" + integer + "]");
            }
        });
    }
}
