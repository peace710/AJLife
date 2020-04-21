# RxJava组合型操作符
### startWith
startWith操作符  
在数据序列的开头增加一项数据  
如果你想一个Observable发射的数据末尾追加一个数据序列可以使用Concat操作符    
支持Flowable,Observable
```
        Observable.just(10).startWith(Single.just(48)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator startWith: integer = [" + integer + "]");
            }
        });
``` 
> 运行结果：</br>
>「StartWith」 -> RxJava Observable Operator startWith: integer = [48] 「ThreadName ⇢ main」</br> 
>「StartWith」 -> RxJava Observable Operator startWith: integer = [10] 「ThreadName ⇢ main」</br>

### merge
merge操作符  
将多个Observable合并为一个  
支持Flowable,Observable,Maybe,Single,Completable
```
        Observable.just(1,2,3).mergeWith(Single.just(4)).mergeWith(Observable.just(5,6,7)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator merge: integer = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「Merge」 -> RxJava Observable Operator merge: integer = [1] 「ThreadName ⇢ main」</br> 
>「Merge」 -> RxJava Observable Operator merge: integer = [2] 「ThreadName ⇢ main」</br> 
>「Merge」 -> RxJava Observable Operator merge: integer = [3] 「ThreadName ⇢ main」</br>
>「Merge」 -> RxJava Observable Operator merge: integer = [4] 「ThreadName ⇢ main」</br>
>「Merge」 -> RxJava Observable Operator merge: integer = [5] 「ThreadName ⇢ main」</br>
>「Merge」 -> RxJava Observable Operator merge: integer = [6] 「ThreadName ⇢ main」</br>
>「Merge」 -> RxJava Observable Operator merge: integer = [7] 「ThreadName ⇢ main」</br>

### mergeDelayError
![mergeDelayError](http://reactivex.io/documentation/operators/images/mergeDelayError.C.png)
mergeDelayError操作符  
合并多个Observables，让没有错误的Observable都完成后再发射错误通知  
支持Flowable,Observable,Maybe,Single,Completable
```
      Observable.mergeDelayError(Observable.error(new NullPointerException()),
            Observable.just(10)).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator mergeDelayError: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator mergeDelayError: throwable = [" + throwable + "]");
            }
        });
```
> 运行结果：</br>
>「MergeDelayError」 -> RxJava Observable Operator mergeDelayError: integer = [10] 「ThreadName ⇢ main」 </br>
>「MergeDelayError」 -> RxJava Observable Operator mergeDelayError: throwable = [java.lang.NullPointerException] 「ThreadName ⇢ main」</br>

### zip
![zip](http://reactivex.io/documentation/operators/images/zip.c.png)
zip操作符  
使用一个函数组合多个Observable发射的数据集合，然后再发射这个结果  
支持Flowable,Observable,Maybe,Single
```
     Observable.zip(Observable.just("Hello"), Observable.just("World"),
             new BiFunction<String
         , String, String>() {
                 @Override
                 public String apply(String s, String s2) throws Throwable {
                     return s + s2;
                 }
             }).subscribe(new Consumer<String>() {
             @Override
             public void accept(String s) throws Throwable {
                 LogUtils.d(TAG, "RxJava Observable Operator zip: s = [" + s + "]");
             }
         });
```
> 运行结果：</br>
>「Zip」 -> RxJava Observable Operator zip: s = [HelloWorld] 「ThreadName ⇢ main」

### combineLatest
combineLatest操作符  
当两个Observables中的任何一个发射了一个数据时，通过一个指定的函数组合每个Observable发射的最新数据（一共两个数据），然后发射这个函数的结果  
支持Flowable,Observable
```
      Observable<String> observable1 = Observable.just("8","100","10086");
        Observable<String> observable2 = Observable.just("f");
        Observable.combineLatest(observable1, observable2,
            new BiFunction<String, String, String>() {
            @Override
            public String apply(String t1, String t2) throws Throwable {
                return t1 + t2;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator combinlatest: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>
>「CombinLatest」 -> RxJava Observable Operator combinlatest: s = [10086f] 「ThreadName ⇢ main」 


### switchOnNext
![switchOnNext](http://reactivex.io/documentation/operators/images/switch.c.png)
switchOnNext操作符  
将一个发射Observables的Observable转换成另一个Observable，后者发射这些Observables最近发射的数据  
支持Flowable,Observable
```
        Observable<Observable<String>> time = Observable.interval(1, TimeUnit.SECONDS)
            .map(new Function<Long, Observable<String>>() {
                @Override
                public Observable<String> apply(Long aLong) throws Throwable {
                    return Observable.just(String.valueOf(aLong)).map(new Function<String, String>() {
                        @Override
                        public String apply(String s) throws Throwable {
                            return s;
                        }
                    });
                }
            });

        Observable.switchOnNext(time).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator switchOnNext: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>
>「SwitchOnNext」 -> RxJava Observable Operator switchOnNext: s = [0] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「SwitchOnNext」 -> RxJava Observable Operator switchOnNext: s = [1] 「ThreadName ⇢ RxComputationThreadPool-1」</br>
>「SwitchOnNext」 -> RxJava Observable Operator switchOnNext: s = [2] 「ThreadName ⇢ RxComputationThreadPool-1」</br>
>「SwitchOnNext」 -> RxJava Observable Operator switchOnNext: s = [3] 「ThreadName ⇢ RxComputationThreadPool-1」</br>
>「SwitchOnNext」 -> RxJava Observable Operator switchOnNext: s = [4] 「ThreadName ⇢ RxComputationThreadPool-1」</br>


