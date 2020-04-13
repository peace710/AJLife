package me.peace.jetpack.livedata.ext;

import androidx.annotation.MainThread;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;


@SuppressWarnings("WeakerAccess")
public class LiveEventData<T> extends LiveData<T> {

    private boolean postEvent;

    public LiveEventData(T value) {
        super(value);
    }

    public LiveEventData() {
        super();
    }

    @Override
    public void postValue(T value) {
        super.postValue(value);
    }

    @Override
    public void setValue(T value) {
        super.setValue(value);
        if (!postEvent) postEvent = true;
    }

    @MainThread
    public void observe(@NonNull LifecycleOwner owner, @NonNull Observer<? super T> observer) {
        super.observe(owner,new LiveEventObserver(observer));
    }

    class LiveEventObserver implements Observer<T>{
        private boolean canHandleEvent;
        Observer<? super T> observer;

        public LiveEventObserver(Observer<? super T> observer) {
            this.observer = observer;
        }

        @Override
        public void onChanged(T t) {
            if (!canHandleEvent && postEvent){
                canHandleEvent = true;
                return;
            }

            if (!canHandleEvent){
                canHandleEvent = true;
            }

            if (observer != null){
                observer.onChanged(t);
            }
        }
    }
}
