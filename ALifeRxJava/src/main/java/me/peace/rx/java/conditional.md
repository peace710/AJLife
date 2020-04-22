# RxJava条件型操作符
### amb,ambArray
amb操作符  
ambArray操作符  
多个 Observable 中，无论发射的是 onNext 还是 onComplete 或者 onError，只接受第一个发射数据的那个 Observable，其它 Observable 
发射的数据都被忽略   
```
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
        
       Observable.ambArray(Observable.just("18").delay(5000,TimeUnit.MILLISECONDS),
            Observable.just("48")).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator ambArray: s = [" + s + "]");
            }
        });        
```
> 运行结果：</br>
>「Amb」 -> RxJava Observable Operator amb: o = [2] 「ThreadName ⇢ main」</br> 
>「Amb」 -> RxJava Observable Operator ambArray: s = [48] 「ThreadName ⇢ main」</br> 

### all
all操作符  
判断是否所有的数据项都满足某个条件  
```
        Observable.just(1,2,3,4,5,6).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer > 3;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator all: b = [" + b + "]");
            }
        });

        Observable.just(1,2,3,4,5,6).all(new Predicate<Integer>() {
            @Override
            public boolean test(Integer integer) throws Throwable {
                return integer > 0;
            }
        }).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean b) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator all: b = [" + b + "]");
            }
        });
```  
> 运行结果：</br>
>「All」 -> RxJava Observable Operator all: b = [false] 「ThreadName ⇢ main」</br> 
>「All」 -> RxJava Observable Operator all: b = [true] 「ThreadName ⇢ main」</br> 

### defaultIfEmpty
![defaultIfEmpty](http://reactivex.io/documentation/operators/images/defaultIfEmpty.c.png)
defaultIfEmpty操作符  
发射来自原始Observable的数据，如果原始Observable没有发射数据，就发射一个默认数据  
```
        Observable.empty().defaultIfEmpty("default object").subscribe(new Consumer<Object>() {
            @Override
            public void accept(Object o) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defaultIfEmpty: o = [" + o + "]");
            }
        });
``` 
> 运行结果：</br>
>「DefaultIfEmpty」 -> RxJava Observable Operator defaultIfEmpty: o = [default object] 「ThreadName ⇢ main」 

### skip
skip操作符    
忽略前几个发射的Observable的数据  
```
        Observable.just(1,2,3,4,5,6).skip(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skip: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「Skip」 -> RxJava Observable Operator skip: integer = [4] 「ThreadName ⇢ main」 </br>
>「Skip」 -> RxJava Observable Operator skip: integer = [5] 「ThreadName ⇢ main」 </br>
>「Skip」 -> RxJava Observable Operator skip: integer = [6] 「ThreadName ⇢ main」 </br>

### skipUntil
skipUntil操作符    
丢弃原始Observable发射的数据，直到第二个Observable发射了一个数据，然后发射原始Observable的剩余数据   
```
       Observable.interval(1,TimeUnit.SECONDS).skipUntil(Observable.just("3").delay(3,
           TimeUnit.SECONDS)).subscribe(new Consumer<Object>() {
               @Override
               public void accept(Object integer) throws Throwable {
                   LogUtils.d(TAG, "RxJava Observable Operator skipUntil: integer = [" + integer + "]");
               }
           });
```
> 运行结果：</br>
>「Skip」 -> RxJava Observable Operator skipUntil: integer = [2] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「Skip」 -> RxJava Observable Operator skipUntil: integer = [3] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「Skip」 -> RxJava Observable Operator skipUntil: integer = [4] 「ThreadName ⇢ RxComputationThreadPool-2」</br>
>「Skip」 -> RxJava Observable Operator skipUntil: integer = [5] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 

### skipWhile
skipWhile操作符    
丢弃原始Observable发射的数据，直到一个特定的条件为假，然后发射原始Observable剩余的数据     
```
        Observable.interval(1,TimeUnit.SECONDS).skipWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 6;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skipWhile: aLong = [" + aLong + "]");
            }
        });
```
> 运行结果：</br>
>「Skip」 -> RxJava Observable Operator skipWhile: aLong = [6] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「Skip」 -> RxJava Observable Operator skipWhile: aLong = [7] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「Skip」 -> RxJava Observable Operator skipWhile: aLong = [8] 「ThreadName ⇢ RxComputationThreadPool-1」</br>
>「Skip」 -> RxJava Observable Operator skipWhile: aLong = [9] 「ThreadName ⇢ RxComputationThreadPool-1」</br>

### skipLast
skipLast操作符    
忽略后几个发射的Observable的数据   
```
        Observable.just(1,2,3).skipLast(1).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator skipLast: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「Skip」 -> RxJava Observable Operator skipLast: integer = [1] 「ThreadName ⇢ main」</br> 
>「Skip」 -> RxJava Observable Operator skipLast: integer = [2] 「ThreadName ⇢ main」</br>

### take
take操作符    
只发射前几个的Observable的数据   
```
      Observable.just(1,2,3,4,5,6,7).take(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator take: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「Take」 -> RxJava Observable Operator take: integer = [1] 「ThreadName ⇢ main」</br> 
>「Take」 -> RxJava Observable Operator take: integer = [2] 「ThreadName ⇢ main」</br> 
>「Take」 -> RxJava Observable Operator take: integer = [3] 「ThreadName ⇢ main」</br>

### takeUntil
takeUntil操作符     
发射来自原始Observable的数据，直到第二个Observable发射了一个数据或一个通知  
```
       Observable.interval(1, TimeUnit.SECONDS).takeUntil(Observable.timer(3,TimeUnit.SECONDS)).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeUntil: aLong = [" + aLong + "]");
            }
        });
```
> 运行结果：</br>
>「Take」 -> RxJava Observable Operator takeUntil: aLong = [0] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「Take」 -> RxJava Observable Operator takeUntil: aLong = [1] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 
>「Take」 -> RxJava Observable Operator takeUntil: aLong = [2] 「ThreadName ⇢ RxComputationThreadPool-2」</br> 

### takeWhile
takeWhile操作符       
发射原始Observable的数据，直到一个特定的条件为真，然后跳过剩余的数据   
```
        Observable.interval(1, TimeUnit.SECONDS).takeWhile(new Predicate<Long>() {
            @Override
            public boolean test(Long aLong) throws Throwable {
                return aLong < 3;
            }
        }).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeWhile: aLong = [" + aLong + "]");
            }
        });
```
> 运行结果：</br>
>「Take」 -> RxJava Observable Operator takeWhile: aLong = [0] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「Take」 -> RxJava Observable Operator takeWhile: aLong = [1] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「Take」 -> RxJava Observable Operator takeWhile: aLong = [2] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 

### takeLast
takeLast操作符    
只发射后几个的Observable的数据   
```
      Observable.just(1,2,3,4,5,6,7).takeLast(3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator takeLast: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「Take」 -> RxJava Observable Operator takeLast: integer = [5] 「ThreadName ⇢ main」</br> 
>「Take」 -> RxJava Observable Operator takeLast: integer = [6] 「ThreadName ⇢ main」</br> 
>「Take」 -> RxJava Observable Operator takeLast: integer = [7] 「ThreadName ⇢ main」</br>

### contains
contains操作符      
判断Observable是否会发射一个指定的值  
```
        Observable.just(1,2,3,4,5,6,7).contains(5).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator contains: aBoolean = [" + aBoolean + 
                    "]");
            }
        });
```
> 运行结果：</br>
>「Contains」 -> RxJava Observable Operator contains: aBoolean = [true] 「ThreadName ⇢ main」 

### isEmpty
isEmpty操作符      
判断Observable是否发射了一个值    
```
        Observable.empty().isEmpty().subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator isEmpty: aBoolean = [" + aBoolean +
                    "]");
            }
        });
```
> 运行结果：</br>
>「IsEmpty」 -> RxJava Observable Operator isEmpty: aBoolean = [true] 「ThreadName ⇢ main」 

### sequenceEqual
sequenceEqual操作符      
判断两个Observables发射的序列是否相等  
```
        Observable.sequenceEqual(Observable.just(1,2,3), Observable.just(1,2)).subscribe(new Consumer<Boolean>() {
            @Override
            public void accept(Boolean aBoolean) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator sequenceEqual: aBoolean = [" + aBoolean +
                    "]");
            }
        });
```
> 运行结果：</br>
>「SequenceEqual」 -> RxJava Observable Operator sequenceEqual: aBoolean = [false] 「ThreadName ⇢ main」
