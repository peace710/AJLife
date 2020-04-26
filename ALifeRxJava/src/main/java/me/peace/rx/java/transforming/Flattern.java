package me.peace.rx.java.transforming;

import java.util.Arrays;

import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Flattern {
    private static final String TAG = "Flattern";

    public static void main(String[] args) {
        Flattern flattern = new Flattern();
        flattern.flatternAsFlowable();
        flattern.flattenAsObservable();
    }

    public void flatternAsFlowable(){
        Single<Double> source = Single.just(2.0);
        source.flattenAsFlowable(new Function<Double, Iterable<? extends Double>>() {
            @Override
            public Iterable<? extends Double> apply(Double value) throws Throwable {
                return Arrays.asList(value,Math.pow(value,2),Math.pow(value,3));
            }
        }).subscribe(new Consumer<Double>() {
            @Override
            public void accept(Double value) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatternAsFlowable: value = [" +  value +
                        "]");
            }
        });
    }

    public void flattenAsObservable(){
        Single<Double> source = Single.just(2.0);
        source.flattenAsObservable(new Function<Double, Iterable<? extends Double>>() {
            @Override
            public Iterable<? extends Double> apply(Double value) throws Throwable {
                return Arrays.asList(value,Math.pow(value,2),Math.pow(value,3));
            }
        }).subscribe(new Consumer<Double>() {
            @Override
            public void accept(Double value) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flattenAsObservable: value = [" +  value +
                        "]");
            }
        });
    }
}
