# RxJava错误处理型操作符
### doOnError
![doOnError](http://reactivex.io/documentation/operators/images/do.c.png)
doOnError操作符    
当源Observable遇到一个错误，对错误进行一行响应处理  
支持Flowable,Observable,Maybe,Single,Completable
```
       Observable.error(new NullPointerException("something is wrong")).doOnError(new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator doOnError: throwable = [" + throwable.getMessage() + "]");
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator doOnError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator doOnError(subscribe): throwable = [" + throwable.getMessage() + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator doOnError: complete");
            }
        });
```
> 运行结果：</br>  
>「DoOnError」 -> RxJava Observable Operator doOnError: throwable = [something is wrong] 「ThreadName ⇢ main」</br> 
>「DoOnError」 -> RxJava Observable Operator doOnError(subscribe): throwable = [something is wrong] 「ThreadName ⇢ main」</br>  

### onErrorComplete
![onErrorComplete](http://reactivex.io/documentation/operators/images/Catch.png)
onErrorComplete操作符  
接受Observable的错误事件，发射一个完成事件
支持Maybe,Completable   
```
        Completable.fromAction(new Action() {
            @Override
            public void run() throws Throwable {
                throw new IOException();
            }
        }).onErrorComplete(new Predicate<Throwable>() {
            @Override
            public boolean test(Throwable throwable) throws Throwable {
                return throwable instanceof IOException;
            }
        }).subscribe(new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator onErrorComplete complete");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorComplete: throwable = [" + throwable.getMessage() + "]");
            }
        });
```
> 运行结果：</br>  
>「OnErrorComplete」 -> RxJava Observable Operator onErrorComplete complete 「ThreadName ⇢ main」

### onErrorResumeNext
onErrorResumeNext操作符  
指示Observable在遇到错误时发射一个数据序列  
支持Flowable,Observable,Maybe,Single,Completable
```
Observable<Integer> observable = Observable.generate(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return 1;
            }
        }, new BiFunction<Integer, Emitter<Integer>, Integer>() {
            @Override
            public Integer apply(Integer integer, Emitter<Integer> integerEmitter) throws Throwable {
                integerEmitter.onNext(integer);
                return integer + 1;
            }
        });

        observable.scan(new BiFunction<Integer, Integer, Integer>() {
            @Override
            public Integer apply(Integer integer, Integer integer2) throws Throwable {
                return Math.multiplyExact(integer,integer2);
            }
        }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends Integer>>() {
            @Override
            public ObservableSource<? extends Integer> apply(Throwable throwable) throws Throwable {
                return Observable.empty();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator OnErrorResumeNext: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator OnErrorResumeNext: throwable = [" + throwable.getMessage() + "]");
            }
        });
```
> 运行结果：</br>  
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [1] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [2] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [6] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [24] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [120] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [720] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [5040] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [40320] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [362880] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [3628800] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [39916800] 「ThreadName ⇢ main」</br> 
>「OnErrorResumeNext」 -> RxJava Observable Operator OnErrorResumeNext: integer = [479001600] 「ThreadName ⇢ main」</br> 

### onErrorReturn
onErrorReturn操作符  
指示Observable在遇到错误时发射一个特定的数据    
支持Flowable,Observable,Maybe,Single
```
Single.just("2A").map(new Function<String, Integer>() {
            @Override
            public Integer apply(String s) throws Throwable {
                return Integer.parseInt(s,10);
            }
        }).onErrorReturn(new Function<Throwable, Integer>() {
            @Override
            public Integer apply(Throwable throwable) throws Throwable {
                if (throwable instanceof NumberFormatException) return 0;
                else throw new IllegalArgumentException();
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturn: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator onErrorReturn: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br>  
>「OnErrorReturn」 -> RxJava Observable Operator onErrorReturn: integer = [0] 「ThreadName ⇢ main」 

### onErrorReturnItem
onErrorReturnItem操作符  
指示Observable在遇到错误时发射一个特定的数据    
支持Flowable,Observable,Maybe,Single
```
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
```
> 运行结果：</br>  
>「onErrorReturnItem」 -> RxJava Observable Operator onErrorReturnItem: integer = [0] 「ThreadName ⇢ main」


### retry
retry操作符  
指示Observable遇到错误时重试    
支持Flowable,Observable,Maybe,Single,Completable
```
        Observable.interval(0,1, TimeUnit.SECONDS).flatMap(new Function<Long, ObservableSource<?>>() {
            @Override
            public ObservableSource<?> apply(Long aLong) throws Throwable {
                if (aLong >= 2){
                    return Observable.error(new NullPointerException("Something is wrong"));
                }
                return Observable.just(aLong);
            }
        }).retry(new BiPredicate<Integer, Throwable>() {
            @Override
            public boolean test(@NonNull Integer integer, @NonNull Throwable throwable) throws Throwable {
                return integer < 3;
            }
        }).blockingSubscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retry: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retry: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br>  
>「Retry」 -> RxJava Observable Operator retry: o = [0] 「ThreadName ⇢ main」</br>
>「Retry」 -> RxJava Observable Operator retry: o = [1] 「ThreadName ⇢ main」</br> 
>「Retry」 -> RxJava Observable Operator retry: o = [0] 「ThreadName ⇢ main」</br> 
>「Retry」 -> RxJava Observable Operator retry: o = [1] 「ThreadName ⇢ main」</br> 
>「Retry」 -> RxJava Observable Operator retry: o = [0] 「ThreadName ⇢ main」</br> 
>「Retry」 -> RxJava Observable Operator retry: o = [1] 「ThreadName ⇢ main」</br> 
>「Retry」 -> RxJava Observable Operator retry: throwable = [java.lang.NullPointerException: Something is wrong] 「ThreadName ⇢ main」</br> 

### retryUntil
retryUntil操作符  
指示Observablet一直重试遇到错误      
支持Flowable,Observable,Maybe  
```
      LongAdder error = new LongAdder();
        Observable<Long> source = Observable.interval(0,1, TimeUnit.SECONDS)
            .flatMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long aLong) throws Throwable {
                    if (aLong >= 2) return Observable.error(new NullPointerException("Something is wrong!"));
                    return Observable.just(aLong);
                }
            }).doOnError(new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    error.increment();
                }
            });

        source.retryUntil(new BooleanSupplier() {
            @Override
            public boolean getAsBoolean() throws Throwable {
                return error.intValue() >= 3;
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryUntil: aLong = [" + aLong + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryUntil: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br>  
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [1] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [1] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: aLong = [1] 「ThreadName ⇢ main」</br> 
>「RetryUntil」 -> RxJava Observable Operator retryUntil: throwable = [java.lang.NullPointerException: Something is wrong!] 「ThreadName ⇢ main」</br> 

### retryWhen
retryWhen操作符  
指示Observable遇到错误时，将错误传递给另一个Observable来决定是否要重新给订阅这个Observable  
支持Flowable,Observable,Maybe,Single,Completable  
```
        Observable<Long> source = Observable.interval(0,1, TimeUnit.SECONDS)
            .flatMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long aLong) throws Throwable {
                    if (aLong >= 2) return Observable.error(new NullPointerException("Something is wrong!"));
                    return Observable.just(aLong);
                }
            });

        source.retryWhen(new Function<Observable<Throwable>, ObservableSource<Long>>() {
            @Override
            public ObservableSource<Long> apply(Observable<Throwable> throwableObservable) throws Throwable {
                return throwableObservable.map(new Function<Throwable, Long>() {
                    @Override
                    public Long apply(Throwable throwable) throws Throwable {
                        return Long.valueOf(1);
                    }
                });
            }
        }).scan(new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Throwable {
                return Math.addExact(aLong,aLong2);
            }
        }).doOnNext(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen doOnNext: aLong = [" + aLong + "]");
            }
        }).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 3;
            }
        }).flatMapSingle(new Function<Long, SingleSource<Long>>() {
            @Override
            public SingleSource<Long> apply(Long aLong) throws Throwable {
                return Single.timer(aLong,TimeUnit.SECONDS);
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen: aLong = [" + aLong + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator retryWhen complete");
            }
        });
```
> 运行结果：</br>  
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [0] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [1] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [1] 「ThreadName ⇢ RxComputationThreadPool-4」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [2] 「ThreadName ⇢ RxComputationThreadPool-4」</br>
>「RetryWhen」 -> RxJava Observable Operator retryWhen: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [2] 「ThreadName ⇢ RxComputationThreadPool-7」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen doOnNext: aLong = [3] 「ThreadName ⇢ RxComputationThreadPool-7」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen: aLong = [0] 「ThreadName ⇢ main」</br> 
>「RetryWhen」 -> RxJava Observable Operator retryWhen complete 「ThreadName ⇢ main」</br>
