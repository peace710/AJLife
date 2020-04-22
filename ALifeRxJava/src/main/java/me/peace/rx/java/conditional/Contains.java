package me.peace.rx.java.conditional;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Contains {
    private static final String TAG = "Contains";

    public static void main(String[] args) {
        Contains contains = new Contains();
        contains.contains();
    }

    public void contains(){
        Observable.just(1,2,3,4,5,6,7).contains(5).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator contains: aBoolean = [" + aBoolean +
                    "]");
            }
        });
    }
}
