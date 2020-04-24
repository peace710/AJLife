package me.peace.rx.java.error;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class OnErrorReturnItem {
    private static final String TAG = "onErrorReturnItem";

    public static void main(String[] args) {
        OnErrorReturnItem onErrorReturnItem = new OnErrorReturnItem();
        onErrorReturnItem.onErrorReturnItem();
    }

    public void onErrorReturnItem(){
        Single.just("2A").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return Integer.parseInt(s,10);
            }
        }).onErrorReturnItem(0).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturnItem: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturnItem: throwable = [" + throwable + "]");
            }
        });
    }
}
