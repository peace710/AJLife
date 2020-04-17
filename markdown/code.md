# Markdown 代码
如果是段落上的一个函数或片段的代码可以用反引号把它包起来(`)，例如：
```
`printf()`函数
```
`printf()`函数

## 代码区块
代码区块使用 4 个空格或者一个制表符(Tab 键)      

          Single<String> single = Single.just("Single");
            single.subscribe(new Consumer<String>() {
                @Override
                public void accept(String s) throws Throwable {
                    LogUtils.d(TAG, "RxJava Single Operator just: s = [" + s + "]");
                }
            });
            
你也可以用 ``` 包裹一段代码，并指定一种语言(也可以不指定)
```
        ArrayList<String> array = new ArrayList<>(Arrays.asList("a","b","c","d","e","f","g"));
        Observable.fromIterable(array).subscribe(new Consumer<String>() {
            @Override
            public void accept(String s) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator fromIterable: s = [" + s + "]");
            }
        });
```


