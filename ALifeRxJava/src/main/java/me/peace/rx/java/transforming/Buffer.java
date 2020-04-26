package me.peace.rx.java.transforming;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Buffer {
    private static final String TAG = "Buffer";

    public static void main(String[] args) {
        Buffer buffer = new Buffer();
        buffer.buffer();
    }

    public void buffer(){
        Observable.range(0,10)
            .buffer(4).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator buffer: integers = [" + integers + "]");
            }
        });
    }
}
