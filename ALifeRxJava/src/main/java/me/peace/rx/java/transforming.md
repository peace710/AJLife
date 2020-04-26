# RxJava变换型操作符
### buffer
![buffer](http://reactivex.io/documentation/operators/images/Buffer.png)
buffer操作符      
定期将来自Observable的数据分拆成一些Observable，然后发射这些，而不是每次发射一项   
支持Flowable,Observable  
```
        Observable.range(0,10)
            .buffer(4).subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator buffer: integers = [" + integers + "]");
            }
        });
```
> 运行结果：</br>  
>「Buffer」 -> RxJava Observable Operator buffer: integers = [[0, 1, 2, 3]] 「ThreadName ⇢ main」</br> 
>「Buffer」 -> RxJava Observable Operator buffer: integers = [[4, 5, 6, 7]] 「ThreadName ⇢ main」</br> 
>「Buffer」 -> RxJava Observable Operator buffer: integers = [[8, 9]] 「ThreadName ⇢ main」</br> 

### cast
cast操作符      
在发射之前强制将Observable发射的所有数据转换为指定类型  
支持Flowable,Observable,Maybe,Single
```
     Observable<Number> numbers = Observable.just(1, 4.0, 3f, 7, 12, 4.6, 5);
        numbers.filter(new Predicate<Number>() {
            @Override
            public boolean test(Number number) throws Throwable {
                return Integer.class.isInstance(number);
            }
        }).cast(Integer.class).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator cast: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>  
>「Cast」 -> RxJava Observable Operator cast: integer = [1] 「ThreadName ⇢ main」</br>  
>「Cast」 -> RxJava Observable Operator cast: integer = [7] 「ThreadName ⇢ main」</br>  
>「Cast」 -> RxJava Observable Operator cast: integer = [12] 「ThreadName ⇢ main」</br>  
>「Cast」 -> RxJava Observable Operator cast: integer = [5] 「ThreadName ⇢ main」</br>  

### concatMap
concatMap操作符       
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Observable      
支持Flowable,Observable,Maybe
```
      Observable.range(0,3)
            .concat(new Function<Integer, ObservableSource<?>>() {
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
```
> 运行结果：</br>  
>「ConcatMap」 -> RxJava Observable Operator concatMap: o = [0] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMap: o = [1] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMap: o = [2] 「ThreadName ⇢ main」</br>

### concatMapCompletable
concatMapCompletable操作符         
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Completable      
支持Flowable,Observable
```
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
```
> 运行结果：</br>  
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletable: integer = [2] 「ThreadName ⇢ RxComputationThreadPool-6」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletable: integer = [1] 「ThreadName ⇢ RxComputationThreadPool-7」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletable: integer = [3] 「ThreadName ⇢ RxComputationThreadPool-8」</br>
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletable completable 「ThreadName ⇢ RxComputationThreadPool-8」</br> 

### concatMapCompletableDelayError
concatMapCompletableDelayError操作符         
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Completable,error延迟执行
支持Flowable,Observable
```
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
```
> 运行结果：</br>  
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletableDelayError: integer = [1] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletableDelayError: integer = [3] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapCompletableDelayError: throwable = [java.lang.IllegalArgumentException] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 

### concatMapDelayError
concatMapDelayError操作符         
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Observable,error延迟执行        
支持Flowable,Observable  
```
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
```
> 运行结果：</br>  
>「ConcatMap」 -> RxJava Observable Operator concatMapDelayError: o = [2] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapDelayError: o = [4] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapDelayError: o = [3] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapDelayError: o = [9] 「ThreadName ⇢ main」</br> 
>「ConcatMap」 -> RxJava Observable Operator concatMapDelayError: throwable = [java.lang.IllegalArgumentException] 「ThreadName ⇢ main」</br>

### flatMap
![flatMap](http://reactivex.io/documentation/operators/images/flatMap.c.png)
flatMap操作符  
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Observable        
支持Flowable,Observable,Maybe,Single  
##### flatMap与concatMap区别,flatMap是无序的,concatMap是有序的,concatMap最终输出的顺序与原序列保持一致,而flatMap则不一定,有可能出现交错
```
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
```
> 运行结果：</br>  
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(1,1)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(2,1)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(3,1)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(1,2)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(3,2)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(2,2)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(1,3)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(2,3)] 「ThreadName ⇢ main」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMap: o = [(3,3)] 「ThreadName ⇢ main」</br> 

### flatMapCompletable
flatMapCompletable操作符           
将Observable发射的数据集合变换为Observables集合，然后将这些数据放进一个Completable        
支持Flowable,Observable  
```
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
```
> 运行结果：</br>  
>「FlatMap」 -> RxJava Observable Operator flatMapCompletable: integer = [1] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMapCompletable: integer = [2] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMapCompletable: integer = [3] 「ThreadName ⇢ RxComputationThreadPool-3」</br> 
>「FlatMap」 -> RxJava Observable Operator flatMapCompletable complete 「ThreadName ⇢ RxComputationThreadPool-3」</br>  
 
### groupBy
![groupBy](http://reactivex.io/documentation/operators/images/groupBy.c.png)
groupBy操作符             
将Observable分拆为Observable集合，将原始Observable发射的数据按Key分组，每一个Observable发射一组不同的数据         
支持Flowable,Observable  
```
        Observable<String> animals = Observable.just(
            "Android", "Xiaomi", "Apple", "Huawei", "Sony");

        animals.groupBy(new Function<String, String>() {
            @Override
            public String apply(String s) throws Throwable {
                return String.valueOf(s.charAt(0));
            }
        }, new Function<String, String>() {
            @Override
            public String apply(String s) throws Throwable {
                return s.toUpperCase();
            }
        }).concatMapSingle(new Function<GroupedObservable<String, String>, SingleSource<?>>() {
            @Override
            public SingleSource<?> apply(GroupedObservable<String, String> observable) throws Throwable {
                return observable.toList();
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator groupBy: o = [" +  o + "]");
            }
        });
```
> 运行结果：</br>  
>「GroupBy」 -> RxJava Observable Operator groupBy: o = [[ANDROID, APPLE]] 「ThreadName ⇢ main」</br> 
>「GroupBy」 -> RxJava Observable Operator groupBy: o = [[XIAOMI]] 「ThreadName ⇢ main」</br> 
>「GroupBy」 -> RxJava Observable Operator groupBy: o = [[HUAWEI]] 「ThreadName ⇢ main」</br> 
>「GroupBy」 -> RxJava Observable Operator groupBy: o = [[SONY]] 「ThreadName ⇢ main」</br> 

### map
map操作符               
对序列的每一项都应用一个函数来变换Observable发射的数据序列           
支持Flowable,Observable,Maybe,Single  
```
       Observable.just(1,2,3).map(new Function<Integer,Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer * integer;
            }
        }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator map: integer = [" +  integer + "]");
            }
        });
```  
> 运行结果：</br>  
>「Map」 -> RxJava Observable Operator map: integer = [1] 「ThreadName ⇢ main」</br>  
>「Map」 -> RxJava Observable Operator map: integer = [4] 「ThreadName ⇢ main」</br>  
>「Map」 -> RxJava Observable Operator map: integer = [9] 「ThreadName ⇢ main」</br>

### scan
scan操作符                 
对Observable发射的每一项数据应用一个函数，然后按顺序依次发射每一个值            
支持Flowable,Observable
```
        Observable.just(5,3,8,1,7)
            .scan(0,new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b) throws Throwable {
                    return a + b;
                }
            }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator scan: integer = [" +  integer + "]");
            }
        });
```  
> 运行结果：</br>  
>「Scan」 -> RxJava Observable Operator scan: integer = [0] 「ThreadName ⇢ main」</br> 
>「Scan」 -> RxJava Observable Operator scan: integer = [5] 「ThreadName ⇢ main」</br> 
>「Scan」 -> RxJava Observable Operator scan: integer = [8] 「ThreadName ⇢ main」</br> 
>「Scan」 -> RxJava Observable Operator scan: integer = [16] 「ThreadName ⇢ main」</br> 
>「Scan」 -> RxJava Observable Operator scan: integer = [17] 「ThreadName ⇢ main」</br> 
>「Scan」 -> RxJava Observable Operator scan: integer = [24] 「ThreadName ⇢ main」</br>

### switchMap
switchMap操作符                
将Observable发射的数据集合变换为Observables集合，然后只发射这些Observables最近发射的数据             
支持Flowable,Observable
```
     Observable.interval(0,1, TimeUnit.SECONDS)
            .switchMap(new Function<Long, ObservableSource<Long>>() {
                @Override
                public ObservableSource<Long> apply(Long a) throws Throwable {
                    return Observable.interval(0,750,TimeUnit.MILLISECONDS)
                        .map(new Function<Long, Long>() {
                            @Override
                            public Long apply(Long b) throws Throwable {
                                return a;
                            }
                        });
                }
            }).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long value) throws Throwable {
                return value < 3;
            }
        }).blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long value) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator switchMap: value = [" +  value + "]");
            }
        });
```
> 运行结果：</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [0] 「ThreadName ⇢ main」</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [0] 「ThreadName ⇢ main」</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [1] 「ThreadName ⇢ main」</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [1] 「ThreadName ⇢ main」</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [2] 「ThreadName ⇢ main」</br>  
>「SwitchMap」 -> RxJava Observable Operator switchMap: value = [2] 「ThreadName ⇢ main」</br>  

### window
![window](http://reactivex.io/documentation/operators/images/window.C.png)
window操作符               
定期将来自Observable的数据分拆成一些Observable窗口，然后发射这些窗口，而不是每次发射一项             
支持Flowable,Observable 
```
        Observable.range(1,10)
            .window(2,3).flatMapSingle(new Function<Observable<Integer>, SingleSource<?>>() {
            @Override
            public SingleSource<?> apply(Observable<Integer> observable) throws Throwable {
                return observable.map(new Function<Integer, String>() {
                    @Override
                    public String apply(Integer integer) throws Throwable {
                        return String.valueOf(integer);
                    }
                }).reduce(new StringJoiner(",", "[", "]"), new BiFunction<StringJoiner, String, StringJoiner>() {
                    @Override
                    public StringJoiner apply(StringJoiner stringJoiner, String s) throws Throwable {
                        return stringJoiner.add(s);
                    }
                });
            }
        }).subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator window: o = [" +  o + "]");
            }
        });
```
> 运行结果：</br>  
>「Window」 -> RxJava Observable Operator window: o = [[1,2]] 「ThreadName ⇢ main」</br> 
>「Window」 -> RxJava Observable Operator window: o = [[4,5]] 「ThreadName ⇢ main」</br> 
>「Window」 -> RxJava Observable Operator window: o = [[7,8]] 「ThreadName ⇢ main」</br> 
>「Window」 -> RxJava Observable Operator window: o = [[10]] 「ThreadName ⇢ main」</br> 