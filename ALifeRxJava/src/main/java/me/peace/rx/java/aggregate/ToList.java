package me.peace.rx.java.aggregate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class ToList {
    private static final String TAG = "ToList";

    public static void main(String[] args) {
        ToList toList = new ToList();
        toList.toList();
    }

    public void toList(){
        Observable.just(1,2,3).toList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toList: integers = [" + integers + "]");
            }
        });
    }
}
