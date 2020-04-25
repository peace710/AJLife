package me.peace.rx.java.filtering;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class First {
    private static final String TAG = "First";

    public static void main(String[] args) {
        First first = new First();
        first.first();
        first.firstElement();
        first.firstOrError();
    }

    public void first(){
        Observable.just(1,2,3).first(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator first: integer = [" + integer + "]");
            }
        });
    }

    public void firstElement(){
        Observable.just(1,2,3).firstElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstElement: integer = [" + integer + "]");
            }
        });
    }

    public void firstOrError(){
        Observable.empty().firstOrError().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstOrError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstOrError: throwable = [" + throwable + "]");
            }
        });
    }
}
