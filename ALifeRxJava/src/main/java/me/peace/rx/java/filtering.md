# RxJava过滤型操作符
### debounce
debounce操作符    
当一个事件发送出来之后，在约定时间内没有再次发送这个事件，则发射这个事件，如果再次触发了，则重新计算时间    
支持Flowable,Observable
```
Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(1500);
                emitter.onNext("B");
                sleep(500);
                emitter.onNext("C");
                sleep(250);
                emitter.onNext("D");
                sleep(2000);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).debounce(1, TimeUnit.SECONDS)
        .blockingSubscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator debounce complete");
            }
        });
```

> 运行结果：</br>  
>「Debounce」 -> RxJava Observable Operator debounce: s = [A] 「ThreadName ⇢ main」</br> 
>「Debounce」 -> RxJava Observable Operator debounce: s = [D] 「ThreadName ⇢ main」</br>
>「Debounce」 -> RxJava Observable Operator debounce: s = [E] 「ThreadName ⇢ main」</br>
>「Debounce」 -> RxJava Observable Operator debounce complete 「ThreadName ⇢ main」</br>

### distinct
distinct操作符     
过滤掉重复数据     
支持Flowable,Observable  
```
        Observable.just(2,3,4,4,2,1).distinct().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator distinct: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br> 
>「Distinct」 -> RxJava Observable Operator distinct: integer = [2] 「ThreadName ⇢ main」</br> 
>「Distinct」 -> RxJava Observable Operator distinct: integer = [3] 「ThreadName ⇢ main」</br> 
>「Distinct」 -> RxJava Observable Operator distinct: integer = [4] 「ThreadName ⇢ main」</br> 
>「Distinct」 -> RxJava Observable Operator distinct: integer = [1] 「ThreadName ⇢ main」</br> 

### distinctUntilChanged
distinctUntilChanged操作符        
过滤掉连续重复的数据  
支持Flowable,Observable  
```
        Observable.just(1,1,2,1,2,3,3,4).distinctUntilChanged().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator distinctUntilChanged: integer = [" + integer + "]");
            }
        });
```  
> 运行结果：</br> 
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [1] 「ThreadName ⇢ main」</br>  
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [2] 「ThreadName ⇢ main」</br>  
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [1] 「ThreadName ⇢ main」</br>  
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [2] 「ThreadName ⇢ main」</br>  
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [3] 「ThreadName ⇢ main」</br>  
>「DistinctUntilChanged」 -> RxJava Observable Operator distinctUntilChanged: integer = [4] 「ThreadName ⇢ main」</br> 

### elementAt
elementAt操作符          
发射第N项数据    
支持Flowable,Observable  
```
     Observable<Long> source = Observable.<Long,Long>generate(new Supplier<Long>() {
            @Override
            public Long get() throws Throwable {
                return 1L;
            }
        }, new BiFunction<Long, Emitter<Long>, Long>() {
            @Override
            public Long apply(Long aLong, Emitter<Long> longEmitter) throws Throwable {
                longEmitter.onNext(aLong);
                return aLong + 1L;
            }
        }).scan(new BiFunction<Long, Long, Long>() {
            @Override
            public Long apply(Long aLong, Long aLong2) throws Throwable {
                return aLong * aLong2;
            }
        });


        Maybe<Long> element = source.elementAt(5);
        element.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAt: aLong = [" + aLong + "]");
            }
        });
```
> 运行结果：</br> 
>「ElementAt」 -> RxJava Observable Operator elementAt: aLong = [720] 「ThreadName ⇢ main」 

### elementAtOrError
elementAtOrError操作符          
发射第N项数据,如果没有则发射一个NoSuchElementException      
支持Flowable,Observable  
```
        Observable.just(1,2,3,4).elementAtOrError(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAtOrError: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator elementAtOrError: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br> 
>「ElementAtOrError」 -> RxJava Observable Operator elementAtOrError: throwable = [java.util.NoSuchElementException] 「ThreadName ⇢ main」  

### filter
filter操作符       
过滤数据      
支持Flowable,Observable,Maybe,Single
```
        Observable.just(1,2,3,4,5,6).filter(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer % 3 == 0;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator filter: integer = [" + integer + "]");
            }
        });
```  
> 运行结果：</br> 
>「Filter」 -> RxJava Observable Operator filter: integer = [3] 「ThreadName ⇢ main」</br>  
>「Filter」 -> RxJava Observable Operator filter: integer = [6] 「ThreadName ⇢ main」</br>  

### first
first操作符         
只发射第一项数据      
支持Flowable,Observable
```
        Observable.just(1,2,3).first(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator first: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator first: integer = [1] 「ThreadName ⇢ main」  

### firstElement
firstElement操作符           
只发射第一项数据       
支持Flowable,Observable
```
        Observable.just(1,2,3).firstElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstElement: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator firstElement: integer = [1] 「ThreadName ⇢ main」  

### firstOrError
firstOrError操作符  
只发射第一项数据,如果没有则发射一个NoSuchElementException         
支持Flowable,Observable
```
  Observable.empty().firstOrError().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstOrError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator firstOrError: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator firstOrError: throwable = [java.util.NoSuchElementException] 「ThreadName ⇢ main」  

### ignoreElement
ignoreElement操作符    
操作符忽略所有源Observable产生的结果，只会执行onCompleted()或者onError()方法             
支持Maybe,Single  
```
        Single.timer(1, TimeUnit.SECONDS).ignoreElement()
            .doOnComplete(new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ignoreElement complete");
                }
            }).blockingAwait();
```
> 运行结果：</br> 
>「IgnoreElement」 -> RxJava Observable Operator ignoreElement complete 「ThreadName ⇢ RxComputationThreadPool-1」  

### ignoreElements
![ignoreElements](http://reactivex.io/documentation/operators/images/ignoreElements.c.png)
ignoreElements操作符     
操作符忽略所有源Observable产生的结果，只会执行onCompleted()或者onError()方法             
支持Flowable,Observable  
```
        Observable.intervalRange(1,5,1,1,TimeUnit.SECONDS).ignoreElements().doOnComplete(new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ignoreElements complete");
                }
            }).blockingAwait();
``` 
> 运行结果：</br> 
>「IgnoreElements」 -> RxJava Observable Operator ignoreElements complete 「ThreadName ⇢ RxComputationThreadPool-1」 

### last
last操作符          
只发射最后一项数据        
支持Flowable,Observable
```
        Observable.just(1,2,3).last(4).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator last: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator last: integer = [3] 「ThreadName ⇢ main」  

### lastElement
lastElement操作符           
只发射最后一项数据        
支持Flowable,Observable
```
        Observable.just(1,2,3).lastElement().subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastElement: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator lastElement: integer = [3] 「ThreadName ⇢ main」  

### lastOrError
lastOrError操作符    
只发射最后一项数据,如果没有则发射一个NoSuchElementException           
支持Flowable,Observable
```
  Observable.empty().lastOrError().subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastOrError: o = [" + o + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator lastOrError: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br> 
>「First」 -> RxJava Observable Operator lastOrError: throwable = [java.util.NoSuchElementException] 「ThreadName ⇢ main」 

### ofType
ofType操作符  
只发射指定类型的数据         
支持Flowable,Observable,Maybe  
```
       Observable.just(1.0,"22",2f,12L,5).ofType(Integer.class)
            .subscribe(new Consumer<Integer>() {
                @Override
                public void accept(Integer integer) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator ofType: integer = [" + integer + "]");
                }
            });
```
> 运行结果：</br> 
>「OfType」 -> RxJava Observable Operator ofType: integer = [5] 「ThreadName ⇢ main」

### sample
sample操作符    
定期发射Observable最近的数据           
支持Flowable,Observable
```
  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).sample(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator sample complete");
                }
            });
```
> 运行结果：</br> 
>「Sample」 -> RxJava Observable Operator sample: s = [C] 「ThreadName ⇢ main」</br> 
>「Sample」 -> RxJava Observable Operator sample: s = [D] 「ThreadName ⇢ main」</br> 
>「Sample」 -> RxJava Observable Operator sample complete 「ThreadName ⇢ main」</br>

### throttleFirst
throttleFirst操作符     
定期发射Observable发射的第一项数据            
支持Flowable,Observable
```
Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleFirst(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleFirst complete");
                }
            });
```
> 运行结果：</br> 
>「Throttle」 -> RxJava Observable Operator throttleFirst: s = [A] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleFirst: s = [D] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleFirst complete 「ThreadName ⇢ main」</br>

### throttleLast
throttleLast操作符       
定期发射Observable发射的最后一项数据              
支持Flowable,Observable
```
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleLast(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLast complete");
                }
            });
```
> 运行结果：</br> 
>「Throttle」 -> RxJava Observable Operator throttleLast: s = [C] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleLast: s = [D] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleLast complete 「ThreadName ⇢ main」</br>

### throttleLatest
throttleLatest操作符         
定期发射Observable发射的最近的一项数据              
支持Flowable,Observable
```
  Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(500);
                emitter.onNext("B");
                sleep(200);
                emitter.onNext("C");
                sleep(800);
                emitter.onNext("D");
                sleep(600);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleLatest(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleLatest complete");
                }
            });
```
> 运行结果：</br> 
>「Throttle」 -> RxJava Observable Operator throttleLatest: s = [A] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleLatest: s = [C] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleLatest: s = [D] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleLatest complete 「ThreadName ⇢ main」</br>

### throttleWithTimeout
throttleWithTimeout操作符             
只有当Observable在指定的时间后还没有发射数据时，才发射一个数据                 
支持Flowable,Observable
```
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(1500);
                emitter.onNext("B");
                sleep(500);
                emitter.onNext("C");
                sleep(250);
                emitter.onNext("D");
                sleep(2000);
                emitter.onNext("E");
                emitter.onComplete();
            }
        }).subscribeOn(Schedulers.io()).throttleWithTimeout(1, TimeUnit.SECONDS)
            .blockingSubscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout: s = [" + s + "]");
                }
            }, new Consumer<Throwable>() {
                @Override
                public void accept(Throwable throwable) throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout: throwable = [" + throwable + "]");
                }
            }, new Action() {
                @Override
                public void run() throws Throwable {
                    LogUtils.d(TAG,
                        "RxJava Observable Operator throttleWithTimeout complete");
                }
            });
```
> 运行结果：</br> 
>「Throttle」 -> RxJava Observable Operator throttleWithTimeout: s = [A] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleWithTimeout: s = [D] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleWithTimeout: s = [E] 「ThreadName ⇢ main」</br> 
>「Throttle」 -> RxJava Observable Operator throttleWithTimeout complete 「ThreadName ⇢ main」</br>

### timeout
![timeout](http://reactivex.io/documentation/operators/images/timeout.c.png)
timeout操作符       
如果在一个指定的时间段后还没发射数据，就发射一个异常    
支持Flowable,Observable,Maybe,Single,Completable
```
    Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                emitter.onNext("A");
                sleep(800);            
                emitter.onNext("B");
                sleep(400);
                emitter.onNext("C");
                sleep(1200);
                emitter.onNext("D");
                emitter.onComplete();
            }
        }).timeout(1, TimeUnit.SECONDS)
        .subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout: throwable = [" + throwable + "]");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator timeout complete");
            }
        });
```
> 运行结果：</br> 
>「Timeout」 -> RxJava Observable Operator timeout: s = [A] 「ThreadName ⇢ main」</br> 
>「Timeout」 -> RxJava Observable Operator timeout: s = [B] 「ThreadName ⇢ main」</br> 
>「Timeout」 -> RxJava Observable Operator timeout: s = [C] 「ThreadName ⇢ main」</br> 
>「Timeout」 -> RxJava Observable Operator timeout: throwable = [java.util.concurrent.TimeoutException: The source did not signal an event for 1 seconds and has been terminated.] 「ThreadName ⇢ RxComputationThreadPool-1」</br>