package me.peace.rx.java.error;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import io.reactivex.rxjava3.functions.Supplier;
import me.peace.rx.LogUtils;

public class OnErrorResumeNext {
    private static final String TAG = "OnErrorResumeNext";

    public static void main(String[] args) {
        OnErrorResumeNext onErrorResumeNext = new OnErrorResumeNext();
        onErrorResumeNext.onErrorResumeNext();
    }

    public void onErrorResumeNext(){
        Observable<Integer> observable = Observable.generate(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return 1;
            }
        }, new BiFunction<Integer, Emitter<Integer>, Integer>() {
            @Override
            public Integer apply(Integer integer, Emitter<Integer> integerEmitter) throws Throwable {
                integerEmitter.onNext(integer);
                return integer + 1;
            }
        });

        observable.scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Throwable {
                return Math.multiplyExact(integer,integer2);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Throwable {
                return Observable.empty();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator OnErrorResumeNext: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator OnErrorResumeNext: throwable = [" + throwable.getMessage() + "]");
            }
        });
    }
}
