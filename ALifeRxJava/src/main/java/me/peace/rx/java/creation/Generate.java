package me.peace.rx.java.creation;

import android.util.Log;

import io.reactivex.rxjava3.core.Emitter;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.BiConsumer;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Supplier;
import me.peace.rx.LogUtils;

public class Generate {
    private static final String TAG = "Generate";
    public static void main(String[] args) {
        Generate generate = new Generate();
        generate.generate();
    }

    public void generate(){
        int start = 10;
        Observable.generate(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return start;
            }
        }, new BiConsumer<Integer, Emitter<String>>() {
            @Override
            public void accept(Integer integer, Emitter<String> emitter) throws Throwable {
                String score = "score is " + integer;
                emitter.onNext(score);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator generate: s = [" + s + "]");
            }
        });

        Flowable.generate(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return start;
            }
        }, new BiConsumer<Integer, Emitter<String>>() {
            @Override
            public void accept(Integer integer, Emitter<String> emitter) throws Throwable {
                String score = "score is " + integer;
                emitter.onNext(score);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Flowable Operator generate: s = [" + s + "]");
            }
        });
    }
}
