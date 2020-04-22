package me.peace.rx.java.conditional;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.TimeUnit;

import androidx.annotation.NonNull;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class Amb {
    private static final String TAG = "Amb";
    public static void main(String[] args) {
        Amb amb = new Amb();
        amb.amb();
        amb.ambArray();
    }

    public void amb(){
        ArrayList<ObservableSource<?>> list = new ArrayList<>();
        list.add(Observable.just("8").delay(5000, TimeUnit.MILLISECONDS));
        list.add(Observable.just("2"));
        Observable.amb(new Iterable<ObservableSource<?>>() {
            @NonNull
            @Override
            public Iterator<ObservableSource<?>> iterator() {
                return list.iterator();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator amb: o = [" + o + "]");
            }
        });
    }

    public void ambArray(){
        Observable.ambArray(Observable.just("18").delay(5000,TimeUnit.MILLISECONDS),
            Observable.just("48")).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator ambArray: s = [" + s + "]");
            }
        });
    }
}
