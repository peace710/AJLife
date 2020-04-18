# RxJava创建型操作符
### just
![just](http://reactivex.io/documentation/operators/images/just.c.png)
just操作符  
将一个或多个对象转换成发射这个或这些对象的一个Observable  
支持Flowable,Observable,Maybe,Single
```
        String greeting = "Hello world!";
        Observable<String> observable = Observable.just(greeting);
        observable.subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Operator just: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>  
>「Just」 -> RxJava Observable Operator just: s = [Hello world!] 「ThreadName ⇢ main」 

### from
![from](http://reactivex.io/documentation/operators/images/from.c.png)
from操作符  
将一个Iterable, 一个Future, 或者一个数组转换成一个Observable  
fromIterable支持Flowable,Observable
```
     ArrayList<String> array = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g"));
        Observable.fromIterable(array).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromIterable: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>  
>「From」 -> RxJava Observable Operator fromIterable: s = [a] 「ThreadName ⇢ main」</br>
>「From」 -> RxJava Observable Operator fromIterable: s = [b] 「ThreadName ⇢ main」</br>
>「From」 -> RxJava Observable Operator fromIterable: s = [c] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromIterable: s = [d] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromIterable: s = [e] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromIterable: s = [f] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromIterable: s = [g] 「ThreadName ⇢ main」</br> 

fromArray支持Flowable,Observable
```
        //只支持引用数组
        Integer[] array = new Integer[8];
        for (int i = 0 ;i < array.length ;i++){
            array[i] = i;
        }
        Observable.fromArray(array).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                print("RxJava Observable Operator fromArray: s = [" + integer + "]");
            }
        });
```
> 运行结果：</br>
>「From」 -> RxJava Observable Operator fromArray: s = [0] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [1] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [2] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [3] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [4] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [5] 「ThreadName ⇢ main」</br>
>「From」 -> RxJava Observable Operator fromArray: s = [6] 「ThreadName ⇢ main」</br> 
>「From」 -> RxJava Observable Operator fromArray: s = [7] 「ThreadName ⇢ main」</br>

fromCallable支持Flowable,Observable,Maybe,Single,Completable
```
        Callable<String> callable = new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        };

        Observable.fromCallable(callable).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromArray: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>
>「From」 -> RxJava Observable Operator fromArray: s = [Hello World] 「ThreadName ⇢ main」 

fromAction支持Maybe,Completable
```
        Action action = new Action() {
            @Override
            public void run() throws Throwable {
                print("RxJava fromAction");
            }
        };
          
        Completable.fromAction(action).subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onComplete() {
                print("RxJava Completable Operator fromAction");
            }

            @Override
            public void onError(@NonNull Throwable e) {

            }
        });
```
> 运行结果：</br>
>「From」 -> RxJava fromAction 「ThreadName ⇢ main」 </br>
>「From」 -> RxJava Completable Operator fromAction 「ThreadName ⇢ main」 </br>

fromRunnable支持Maybe,Completable
```
         Runnable runnable = new Runnable() {
             @Override
             public void run() {
                 print("RxJava fromRunnable");
             }
         };
 
         Completable.fromRunnable(runnable).subscribe(new CompletableObserver() {
             @Override
             public void onSubscribe(@NonNull Disposable d) {
 
             }
 
             @Override
             public void onComplete() {
                 print("RxJava Completable Operator fromRunnable");
             }
 
             @Override
             public void onError(@NonNull Throwable e) {
 
             }
         });
```
> 运行结果：</br>
>「From」 -> RxJava fromRunnable 「ThreadName ⇢ main」 </br>
>「From」 -> RxJava Completable Operator fromRunnable 「ThreadName ⇢ main」 </br>

#### fromAction与fromRunnable区别是fromAction发生exception可以向外抛出，而fromRunnable不能

fromFuture支持Flowable,Observable,Maybe,Single,Completable
```
        ScheduledExecutorService executor = Executors.newSingleThreadScheduledExecutor();
        Future<String> future = executor.schedule(new Callable<String>() {
            @Override
            public String call() throws Exception {
                return "Hello World";
            }
        },1, TimeUnit.SECONDS);
        Observable.fromFuture(future).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                print("RxJava Observable Operator fromFuture: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>
>「From」 -> RxJava Observable Operator fromFuture: s = [Hello World] 「ThreadName ⇢ main」 

### from{reactive type}
封装或者转换一个响应类型到目标响应类型  

targetType \ sourceType | Publisher | Observable | Maybe | Single | Completable 
----|---------------|-----------|---------|-----------|----------------
Flowable | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | | | | |
Observable | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | | | | |
Maybe | | | | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png)
Single | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | | |
Completable | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) | ![image](https://raw.github.com/wiki/ReactiveX/RxJava/images/checkmark_on.png) |

```
 public void fromReactiveType(){
        Observable.fromPublisher(new CompletableFuturePublisher(CompletableFuture.<Integer>completedFuture(10086))).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                print("RxJava Observable Operator fromReactiveType: integer = [" + integer + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                print("RxJava Observable Operator fromReactiveType: throwable = [" + throwable + "]");
            }
        });
    }

    class CompletableFuturePublisher implements Publisher<Integer>{
        CompletableFuture<Integer> future;

        public CompletableFuturePublisher(CompletableFuture<Integer> future) {
            this.future = future;
        }

        @Override
        public void subscribe(Subscriber<? super Integer> subscriber) {
//            future = null;
            subscriber.onSubscribe(new CompletableFutureSubscription(subscriber,future));
        }
    }

    class CompletableFutureSubscription implements Subscription{
        Subscriber<? super Integer> publisher;
        CompletableFuture<Integer> future;

        public CompletableFutureSubscription(Subscriber<? super Integer> publisher, CompletableFuture<Integer> future) {
            this.publisher = publisher;
            this.future = future;
        }

        @Override
        public void request(long n) {
            try {
                print("CompletableFutureSubscription request() onNext");
                publisher.onNext(future.get(5,TimeUnit.SECONDS));
                print("CompletableFutureSubscription request() onComplete");
                publisher.onComplete();
            } catch (Exception e) {
                e.printStackTrace();
                print("CompletableFutureSubscription request() onError");
                publisher.onError(e);
            }
        }

        @Override
        public void cancel() {

        }
    }
```
> 运行结果：</br>
>「From」 -> CompletableFutureSubscription request() onNext 「ThreadName ⇢ main」 </br>
>「From」 -> RxJava Observable Operator fromReactiveType: integer = [10086] 「ThreadName ⇢ main」</br> 
>「From」 -> CompletableFutureSubscription request() onComplete 「ThreadName ⇢ main」 </br>

### generate
generate操作符    
每次可以发射一个事件,有状态的值,会连续发射,直到执行了OnComplete或OnError   
支持Flowable,Observable
```
 int start = 10;
        Observable.generate(new Supplier<Integer>() {
            @Override
            public Integer get() throws Throwable {
                return start;
            }
        }, new BiConsumer<Integer, Emitter<String>>() {
            @Override
            public void accept(Integer integer, Emitter<String> emitter) throws Throwable {
                String score = "score is " + integer;
                emitter.onNext(score);
                emitter.onComplete();
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator generate: s = [" + s + "]");
            }
        });
```
> 运行结果：</br>
>「Generate」 -> RxJava Observable Operator generate: s = [score is 10] 「ThreadName ⇢ main」 

### create
![create](http://reactivex.io/documentation/operators/images/create.c.png)
create操作符  
创建一个Observable，可以发射多具事件
支持Flowable,Observable,Maybe,Single,Completable
```
     ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ObservableOnSubscribe<String> handler = new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> emitter) throws Throwable {
                Future<String> future = service.schedule(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        emitter.onNext("hello");
                        emitter.onNext("world");
                        emitter.onComplete();
                        return null;
                    }
                },1, TimeUnit.SECONDS);
                emitter.setCancellable(new Cancellable() {
                    @Override
                    public void cancel() throws Throwable {
                        future.cancel(false);
                    }
                });
            }
        };
        Observable.create(handler).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator create: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {

            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator create complete");
            }
        });
        
        Thread.sleep(2000);
        service.shutdown();
```
> 运行结果：</br>
>「Create」 -> RxJava Observable Operator create: s = [hello] 「ThreadName ⇢ pool-1-thread-1」</br>
>「Create」 -> RxJava Observable Operator create: s = [world] 「ThreadName ⇢ pool-1-thread-1」</br> 
>「Create」 -> RxJava Observable Operator create complete 「ThreadName ⇢ pool-1-thread-1」 </br>

### defer
![defer](http://reactivex.io/documentation/operators/images/defer.c.png)
defer操作符  
直到有观察者订阅时才创建Observable，并且为每个观察者创建一个新的Observable
支持Flowable,Observable,Maybe,Single,Completable
```
      Observable<Long> observable = Observable.defer(new Supplier<ObservableSource<? extends Long>>() {
            @Override
            public ObservableSource<? extends Long> get() throws Throwable {
                long time = System.currentTimeMillis();
                return Observable.just(time);
            }
        });

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defer: time = [" + time + "]");
            }
        });

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        observable.subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator defer: time = [" + time + "]");
            }
        });
```
> 运行结果：</br>
> 「Defer」 -> RxJava Observable Operator defer: time = [1587222789130] 「ThreadName ⇢ main」</br> 
> 「Defer」 -> RxJava Observable Operator defer: time = [1587222790135] 「ThreadName ⇢ main」</br>

### range
![range](http://reactivex.io/documentation/operators/images/range.c.png)
range操作符  
创建一个发射特定整数序列的Observable  
支持Flowable,Observable
```
      String greeting = "Hello World!";
        Observable<Integer> indexes = Observable.range(0,greeting.length());
        Observable<Character> characters = indexes.map(new Function<Integer, Character>() {
            @Override
            public Character apply(Integer integer) throws Throwable {
                return greeting.charAt(integer);
            }
        });

        characters.subscribe(new Consumer<Character>() {
            @Override
            public void accept(Character character) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator range: character = [" + character + "]");
            }
        });
```
> 运行结果：</br>
>「Range」 -> RxJava Observable Operator range: character = [H] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [e] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [l] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [l] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [o] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [ ] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [W] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [o] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [r] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [l] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [d] 「ThreadName ⇢ main」 </br>
>「Range」 -> RxJava Observable Operator range: character = [!] 「ThreadName ⇢ main」 </br>


### interval
![interval](http://reactivex.io/documentation/operators/images/interval.c.png)
interval操作符  
创建一个按照给定的时间间隔发射整数序列的Observable  
支持Flowable,Observable
```
     Observable.interval(1, TimeUnit.SECONDS).subscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG,
                    "RxJava Observable Operator interval: time = [" + System.currentTimeMillis() + "," + time +"]");
            }
        });
```
> 运行结果：</br>
>「Interval」 -> RxJava Observable Operator interval: time = [1587223848282,0] 「ThreadName ⇢ RxComputationThreadPool-1」</br> 
>「Interval」 -> RxJava Observable Operator interval: time = [1587223849281,1] 「ThreadName ⇢ RxComputationThreadPool-1」</br>
>「Interval」 -> RxJava Observable Operator interval: time = [1587223850281,2] 「ThreadName ⇢ RxComputationThreadPool-1」</br>

### timer
![timer](http://reactivex.io/documentation/operators/images/timer.c.png)
timer操作符  
创建一个在给定的延时之后发射单个数据的Observable  
支持Flowable,Observable,Maybe,Single,Completable
```
       Observable<Long> observable = Observable.timer(5, TimeUnit.SECONDS);
        observable.blockingSubscribe(new Consumer<Long>() {
            @Override
            public void accept(Long time) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator timer: time = [" + time + "]");
            }
        });
```
> 运行结果：</br>
>「Timer」 -> RxJava Observable Operator timer: time = [0] 「ThreadName ⇢ main」


### empty
![empty](http://reactivex.io/documentation/operators/images/empty.c.png)
empty操作符  
创建一个什么都不做直接通知完成的Observable    
支持Flowable,Observable,Maybe,Completable
```        Observable.<String>empty().subscribe(new Consumer<String>() {
               @Override
               public void accept(String s) throws Throwable {
                   LogUtils.d(TAG, "RxJava Observable Operator empty: s = [" + s + "]");
               }
           }, new Consumer<Throwable>() {
               @Override
               public void accept(Throwable throwable) throws Throwable {
                   LogUtils.d(TAG, "RxJava Observable Operator empty error");
               }
           }, new Action() {
               @Override
               public void run() throws Throwable {
                   LogUtils.d(TAG, "RxJava Observable Operator empty complete");
               }
           });
```
> 运行结果：</br>
>「Empty」 -> RxJava Observable Operator empty complete 「ThreadName ⇢ main」

### never
![never](http://reactivex.io/documentation/operators/images/never.c.png)
never操作符  
创建一个不发射任何数据的Observable  
支持Flowable,Observable,Maybe,Single,Completable
```
       Observable.<String>never().subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never error");
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator never complete");
            }
        });
```
> 运行结果：</br>
> 没有任何结果 

### error
![error](http://reactivex.io/documentation/operators/images/throw.c.png)
error操作符  
创建一个什么都不做直接通知错误的Observable   
支持Flowable,Observable,Maybe,Single,Completable
```
 Observable.<String>error(new NullPointerException()).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error: s = [" + s + "]");
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error:" + throwable);
            }
        }, new Action() {
            @Override
            public void run() throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator error complete");
            }
        });
```
> 运行结果：</br>
>「Error」 -> RxJava Observable Operator error:java.lang.NullPointerException 「ThreadName ⇢ main」 

### onErrorResumeNext
onErrorResumeNext操作符  
Observable出现异常可以继续往下执行  
```
 Observable.range(1,3).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(Integer integer) throws Throwable {
                Observable.fromCallable(new Callable<String>() {
                    @Override
                    public String call() throws Exception {
                        if (Math.random() < 0.5){
                            throw new IOException();
                        }
                        throw new IllegalArgumentException();
                    }
                }).onErrorResumeNext(new Function<Throwable, ObservableSource<? extends String>>() {
                    @Override
                    public ObservableSource<? extends String> apply(Throwable throwable) throws Throwable {
                        if (throwable instanceof IllegalArgumentException){
                            return Observable.empty();
                        }
                        return Observable.error(throwable);
                    }
                }).subscribe(new Consumer<String>() {
                    @Override
                    public void accept(String s) throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator onErrorResumeNext: s = [" + s + "]");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator onErrorResumeNext error:" + throwable);
                    }
                }, new Action() {
                    @Override
                    public void run() throws Throwable {
                        LogUtils.d(TAG, "RxJava Observable Operator onErrorResumeNext complete");
                    }
                });
            }
        });
```
> 运行结果：</br>
>「Error」 -> RxJava Observable Operator onErrorResumeNext complete 「ThreadName ⇢ main」</br> 
>「Error」 -> RxJava Observable Operator onErrorResumeNext complete 「ThreadName ⇢ main」</br> 
>「Error」 -> RxJava Observable Operator onErrorResumeNext error:java.io.IOException 「ThreadName ⇢ main」</br>





