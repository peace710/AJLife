package me.peace.rx.java.aggregate;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.functions.Consumer;
import me.peace.rx.LogUtils;

public class ToSortList {
    private static final String TAG = "ToSortList";

    public static void main(String[] args) {
        ToSortList toSortList = new ToSortList();
        toSortList.toSortList();
    }

    public void toSortList(){
        Observable.just(1,9,5,6,2).toSortedList().subscribe(new Consumer<List<Integer>>() {
            @Override
            public void accept(List<Integer> integers) throws Throwable {
                LogUtils.d(TAG, "RxJava Observable Operator toSortList: integers = [" + integers + "]");
            }
        });
    }
}
