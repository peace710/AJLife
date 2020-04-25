package me.peace.rx.java.aggregate;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Count {
    private static final String TAG = "Count";

    public static void main(String[] args) {
        Count count = new Count();
        count.count();
    }

    public void count(){
        Observable.just(1,2,3,4).count().subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator count: aLong = [" + aLong + "]");
            }
        });
    }
}
