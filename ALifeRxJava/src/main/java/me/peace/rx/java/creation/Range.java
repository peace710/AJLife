package me.peace.rx.java.creation;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import io.reactivex.rxjava3.functions.Function;
import me.peace.rx.LogUtils;

public class Range {
    private static final String TAG = "Range";
    public static void main(String[] args) {
        Range range = new Range();
        range.range();
    }

    public void range(){
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
    }
}
