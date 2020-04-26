package me.peace.rx.java.transforming;

import java.util.Arrays;
import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.annotations.NonNull;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.CompletableSource;
import io.reactivex.rxjava3.core.Maybe;
import io.reactivex.rxjava3.core.MaybeSource;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.ObservableEmitter;
import io.reactivex.rxjava3.core.ObservableOnSubscribe;
import io.reactivex.rxjava3.core.ObservableSource;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.core.SingleSource;
import io.reactivex.rxjava3.disposables.Disposable;
import io.reactivex.rxjava3.functions.Action;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class ConcatMap {
    private static final String TAG = "ConcatMap";

    public static void main(String[] args) {
        ConcatMap concatMap = new ConcatMap();
        concatMap.concatMap();
        concatMap.concatMapCompletable();
        concatMap.concatMapCompletableDelayError();
        concatMap.concatMapDelayError();
        concatMap.concatMapEager();
        concatMap.concatMapEagerDelayError();
        concatMap.concatMapIterable();
        concatMap.concatMapMaybe();
        concatMap.concatMapSingle();
    }

    public void concatMap(){
        Observable.range(0,5)
            .concatMap(new Function<Integer, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Integer integer) throws Throwable {
                    return Observable.timer(Math.round(Math.random()* 2), TimeUnit.SECONDS).map(new Function<Long, Integer>() {
                        @Override
                        public Integer apply(Long aLong) throws Throwable {
                            return integer;
                        }
                    });
                }
            }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMap: o = [" + o + "]");
            }
        });
    }

    public void concatMapCompletable(){
        Observable<Integer> source = Observable.just(2,1,3);
        Completable completable = source.concatMapCompletable(new Function<Integer, CompletableSource>() {
            @Override
            public CompletableSource apply(Integer integer) throws Throwable {
                return Completable.timer(integer,TimeUnit.SECONDS)
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            LogUtils.d(TAG,
                                "RxJava Observable Operator concatMapCompletable: integer = [" + integer + "]");
                        }
                    });
            }
        });
        completable.doOnComplete(new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapCompletable completable");
            }
        }).blockingAwait();
    }

    public void concatMapCompletableDelayError (){
        Observable<Integer> source = Observable.just(2,1,3);
        Completable completable = source.concatMapCompletableDelayError(new Function<Integer, CompletableSource>() {
            @Override
            public CompletableSource apply(Integer integer) throws Throwable {
                if (integer.equals(2)){
                    return Completable.error(new IllegalArgumentException());
                }
                return Completable.timer(integer,TimeUnit.SECONDS)
                    .doOnComplete(new Action() {
                        @Override
                        public void run() throws Throwable {
                            LogUtils.d(TAG,
                                "RxJava Observable Operator concatMapCompletableDelayError: integer = [" + integer + "]");
                        }
                    });
            }
        });
        completable.doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapCompletableDelayError: throwable = [" + throwable + "]");
            }
        }).onErrorComplete().blockingAwait();
    }

    public void concatMapDelayError (){
        Observable.intervalRange(1,3,0,1,TimeUnit.SECONDS)
            .concatMapDelayError(new Function<Long, ObservableSource<?>>() {
                @Override
                public ObservableSource<?> apply(Long value) throws Throwable {
                    if (value.equals(1L)){
                        return Observable.error(new IllegalArgumentException());
                    }
                    return Observable.just(value,value * value);
                }
            }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapDelayError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapDelayError: throwable = [" + throwable + "]");
            }
        });
    }

    public void concatMapEager(){
        Observable.range(0,5).concatMapEager(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.timer(Math.round(Math.random() * 3), TimeUnit.SECONDS)
                    .map(new Function<Long, Integer>() {
                        @Override
                        public Integer apply(Long aLong) throws Throwable {
                            return integer;
                        }
                    }).doOnNext(new Consumer<Integer>() {
                        @Override
                        public void accept(Integer integer) throws Throwable {
                            LogUtils.d(TAG,
                                "RxJava Observable Operator concatMapEager: integer = [" + integer + "]");
                        }
                    });
            }
        }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapEager:  o = [" +  o + "]");
            }
        });
    }

    public void concatMapEagerDelayError(){
        Observable<Integer> source = Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> emitter) throws Throwable {
                emitter.onNext(1);
                emitter.onNext(2);
                emitter.onError(new IllegalArgumentException());
            }
        });

        source.doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapEagerDelayError doOnError: throwable = [" +  throwable +
                        "]");
            }
        }).concatMapEagerDelayError(new Function<Integer, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Integer integer) throws Throwable {
                return Observable.timer(1,TimeUnit.SECONDS).map(new Function<Long, Integer>() {
                    @Override
                    public Integer apply(Long aLong) throws Throwable {
                        return integer;
                    }
                }).doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(Disposable disposable) throws Throwable {
                        LogUtils.d(TAG,
                            "RxJava Observable Operator concatMapEagerDelayError: integer = [" + integer +
                                "]");
                    }
                });
            }
        },true).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapEagerDelayError: o = [" + o +
                        "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapEagerDelayError: throwable = [" +  throwable +
                        "]");
            }
        });
    }

    public void concatMapIterable(){
        Observable.just("A","B","C").concatMapIterable(new Function<String, Iterable<?>>() {
            @Override
            public Iterable<?> apply(String s) throws Throwable {
                return Arrays.asList(s,s,s);
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapIterable: o = [" +  o +
                        "]");
            }
        });
    }
    
    public void concatMapMaybe(){
        Observable.just("5","3,14","2.71","FF")
            .concatMapMaybe(new Function<String, MaybeSource<?>>() {
                @Override
                public MaybeSource<?> apply(String s) throws Throwable {
                    return Maybe.fromCallable(new Callable<Double>() {
                        @Override
                        public Double call() throws Exception {
                            return Double.parseDouble(s);
                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            LogUtils.d(TAG,
                                "RxJava Observable Operator concatMapMaybe: throwable = [" +  throwable +
                                    "]");
                        }
                    }).onErrorComplete();
                }
            }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapMaybe: o = [" +  o +
                        "]");
            }
        });
    }

    public void concatMapSingle(){
        Observable.just("5","3,14","2.71","FF")
            .concatMapSingle(new Function<String, SingleSource<?>>() {
                @Override
                public SingleSource<?> apply(String s) throws Throwable {
                    return Single.fromCallable(new Callable<Double>() {
                        @Override
                        public Double call() throws Exception {
                            return Double.parseDouble(s);
                        }
                    }).doOnError(new Consumer<Throwable>() {
                        @Override
                        public void accept(Throwable throwable) throws Throwable {
                            LogUtils.d(TAG,
                                "RxJava Observable Operator concatMapSingle: throwable = [" +  throwable +
                                    "]");
                        }
                    }).onErrorReturnItem(42.0);
                }
            }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator concatMapSingle: o = [" +  o +
                        "]");
            }
        });
    }
}
