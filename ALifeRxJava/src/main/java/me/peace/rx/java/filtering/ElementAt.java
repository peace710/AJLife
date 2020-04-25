package me.peace.rx.java.filtering;

import android.util.Log;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiFunction;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import me.peace.rx.LogUtils;

public class ElementAt {
    private static final String TAG = "ElementAt";

    public static void main(String[] args) {
        ElementAt elementAt = new ElementAt();
        elementAt.elementAt();
    }

    public void elementAt(){
        Observable<Long> source = Observable.<Long,Long>generate(new Supplier<Long>() {
            @Override
            public Long get() throws Throwable {
                return 1L;
            }
        }, new BiFunction<Long, Emitter<Long>, Long>() {
            @Override
            public Long apply(Long aLong, Emitter<Long> longEmitter) throws Throwable {
                longEmitter.onNext(aLong);
                return aLong + 1L;
            }
        }).scan(new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Throwable {
                return aLong * aLong2;
            }
        });


        Maybe<Long> element = source.elementAt(5);
        element.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAt: aLong = [" + aLong + "]");
            }
        });
    }
}
