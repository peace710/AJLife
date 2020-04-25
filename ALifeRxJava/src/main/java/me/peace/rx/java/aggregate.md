# RxJava聚合型操作符
### count
count操作符    
计算数据项的个数并发射结果     
支持Flowable,Observable,Maybe    
```
        Observable.just(1,2,3,4).count().subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long aLong) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator count: aLong = [" + aLong + "]");
            }
        });
```
> 运行结果：</br>  
>「Count」 -> RxJava Observable Operator count: aLong = [4] 「ThreadName ⇢ main」 

### reduce
reduce操作符      
对序列使用reduce()函数并发射最终的结果     
支持Flowable,Observable
```
        Observable.range(1,5)
            .reduce(new BiFunction<Integer, Integer, Integer>() {
                @Override
                public Integer apply(Integer a, Integer b) throws Throwable {
                    return a * b;
                }
            }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator reduce: integer = [" + integer + "]");
            }
        });
``` 
> 运行结果：</br>  
>「Reduce」 -> RxJava Observable Operator reduce: integer = [120] 「ThreadName ⇢ main」

### collect
collect操作符        
将原始Observable发射的数据放到一个单一的可变的数据结构中，然后返回一个发射这个数据结构的Observable      
支持Flowable,Observable 
```
 Observable.just("1","2","3","4")
        .collect(new Supplier<StringJoiner>() {
            @Override
            public StringJoiner get() throws Throwable {
                return new StringJoiner(" \uD83D\uDD96 ");
            }
        }, new BiConsumer<StringJoiner, String>() {
            @Override
            public void accept(StringJoiner stringJoiner, String s) throws Throwable {
                stringJoiner.add(s);
            }
        }).map(new Function<StringJoiner, String>() {
            @Override
            public String apply(StringJoiner stringJoiner) throws Throwable {
                return stringJoiner.toString();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator collect: s = [" + s + "]");
            }
        });
``` 
> 运行结果：</br>  
>「Collect」 -> RxJava Observable Operator collect: s = [1 🖖 2 🖖 3 🖖 4] 「ThreadName ⇢ main」

### toList
toList操作符       
收集原始Observable发射的所有数据到一个列表，然后返回这个列表      
支持Flowable,Observable  
```
       Observable.just(1,2,3).toList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toList: integers = [" + integers + "]");
            }
        });
```
> 运行结果：</br>  
>「ToList」 -> RxJava Observable Operator toList: integers = [[1, 2, 3]] 「ThreadName ⇢ main」 


### toSortedList
toSortedList操作符         
收集原始Observable发射的所有数据到一个有序列表，然后返回这个列表      
支持Flowable,Observable  
```
       Observable.just(1,9,5,6,2).toSortedList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toSortList: integers = [" + integers + "]");
            }
        });
```
> 运行结果：</br>  
>「ToSortList」 -> RxJava Observable Operator toSortList: integers = [[1, 2, 5, 6, 9]] 「ThreadName ⇢ main」 

### toMap
toMap操作符         
将序列数据转换为一个Map，Map的key是根据一个函数计算的     
支持Flowable,Observable  
```
 Observable.just(1,2,3,4).
            toMap(new Function<Integer, Integer>() {
                @Override
                public Integer apply(Integer integer) throws Throwable {
                    return integer;
                }
            }, new Function<Integer, String>() {
                @Override
                public String apply(Integer integer) throws Throwable {
                    return integer % 2 == 0 ? "even":"odd";
                }
            }).subscribe(new Consumer<Map<Integer, String>>() {
            @Override
            public void accept(Map<Integer, String> map) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toMap: map = [" + map + "]");
            }
        });
```  
> 运行结果：</br>  
>「ToMap」 -> RxJava Observable Operator toMap: map = [{1=odd, 2=even, 3=odd, 4=even}] 「ThreadName ⇢ main」 


### toMultimap
toMultimap操作符        
将序列数据转换为一个列表，同时也是一个Map，Map的key是根据一个函数计算的       
支持Flowable,Observable  
```
      Observable.just(1,2,3,4).toMultimap(new Function<Integer, String>() {
            @Override
            public String apply(Integer integer) throws Throwable {
                return integer % 2 == 0 ? "even" : "odd";
            }
        }, new Function<Integer, Integer>() {
            @Override
            public Integer apply(Integer integer) throws Throwable {
                return integer;
            }
        }).subscribe(new Consumer<Map<String, Collection<Integer>>>() {
            @Override
            public void accept(Map<String, Collection<Integer>> map) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toMultimap: map = [" + map + "]");
            }
        });
```
> 运行结果：</br>  
>「ToMultimap」 -> RxJava Observable Operator toMultimap: map = [{even=[2, 4], odd=[1, 3]}] 「ThreadName ⇢ main」




