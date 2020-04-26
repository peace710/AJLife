package me.peace.rx.java.transforming;

import org.reactivestreams.Publisher;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Flowable;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class FlatMap {
    private static final String TAG = "FlatMap";

    public static void main(String[] args) {
        FlatMap flatMap = new FlatMap();
//        flatMap.flatMap();
//        flatMap.flatMapCompletable();
//        flatMap.flatMapIterable();
//        flatMap.flatMapMaybe();
//        flatMap.flatMapObservable();
//        flatMap.flatMapPublisher();
        flatMap.flatMapSingle();
    }

    public void flatMap(){
        Observable.just(1,2,3).flatMap(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.intervalRange(1,3,0,1, TimeUnit.SECONDS)
                    .map(new Function<Long, String>() {
                        @Override
                        public String apply(Long aLong) throws Throwable {
                            return "(" + integer + "," +  aLong + ")";
                        }
                    });
            }
        }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMap: o = [" +  o +
                        "]");
            }
        });
    }

    public void flatMapCompletable(){
        Observable<Integer> source = Observable.just(2,1,3);
        source.flatMapCompletable(new Function<Integer, CompletableSource>() {
            @Override
            public CompletableSource apply(Integer integer) throws Throwable {
                return Completable.timer(integer,TimeUnit.SECONDS).doOnComplete(new Action() {
                    @Override
                    public void run() throws Throwable {
                        LogUtils.d(TAG,
                            "RxJava Observable Operator flatMapCompletable: integer = [" +  integer +
                                "]");
                    }
                });
            }
        }).doOnComplete(new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapCompletable complete");
            }
        }).blockingAwait();

    }

    public void flatMapIterable(){
        Observable.just(1,2,3,4).flatMapIterable(new Function<Integer, Iterable<?>>() {
            @Override
            public Iterable<?> apply(Integer integer) throws Throwable {
                switch (integer % 4){
                    case 1:
                        return Arrays.asList("A");
                    case 2:
                        return Arrays.asList("B","B");
                    case 3:
                        return Arrays.asList("C","C","C");
                    default:
                        return Arrays.asList();
                }
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapIterable: o = [" +  o +
                        "]");
            }
        });
    }

    public void flatMapMaybe(){
        Observable.just(9.0,16.0,-4.0)
            .flatMapMaybe(new Function<Double, MaybeSource<?>>() {
                @Override
                public MaybeSource<?> apply(Double value) throws Throwable {
                   if (value.compareTo(0.0) < 0) return Maybe.empty();
                   return Maybe.just(Math.sqrt(value));
                }
            }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapMaybe: o = [" +  o +
                        "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapMaybe: throwable = [" +  throwable +
                        "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapMaybe complete");
            }
        });
    }

    public void flatMapObservable(){
        Single<String> source = Single.just("Android,iPhone,Windows");
        Observable<String> names = source.flatMapObservable(new Function<String, ObservableSource<? extends String>>() {
            @Override
            public ObservableSource<? extends String> apply(String s) throws Throwable {
                return Observable.fromArray(s.split(",")).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Throwable {
                        return s.trim();
                    }
                });
            }
        });
        names.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapObservable: s = [" +  s +
                        "]");
            }
        });
    }

    public void flatMapPublisher(){
        Single<String> source = Single.just("Android,iPhone,Windows");
        Flowable<String> names = source.flatMapPublisher(new Function<String, Publisher<? extends String>>() {
            @Override
            public Publisher<? extends String> apply(String s) throws Throwable {
                return Flowable.fromArray(s.split(",")).map(new Function<String, String>() {
                    @Override
                    public String apply(String s) throws Throwable {
                        return s.trim();
                    }
                });
            }
        });
        names.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapPublisher: s = [" +  s +
                        "]");
            }
        });
    }

    public void flatMapSingle(){
        Observable.just(4,2,1,3)
            .flatMapSingle(new Function<Integer, SingleSource<?>>() {
                @Override
                public SingleSource<?> apply(Integer integer) throws Throwable {
                    return Single.timer(integer,TimeUnit.SECONDS).map(new Function<Long, Object>() {
                        @Override
                        public Object apply(Long aLong) throws Throwable {
                                return integer;
                        }
                    });
                }
            }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator flatMapSingle: o = [" +  o +
                        "]");
            }
        });
    }
}
