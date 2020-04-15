package me.peace.jetpack.viewmodel;

import android.util.Log;

import androidx.lifecycle.ViewModel;
import me.peace.jetpack.livedata.ext.LiveDataEventBus;

public class ValueViewModel extends ViewModel {
    private static final String TAG = "ValueViewModel";

    private int value;

    public void increase() {
        value++;
        update();
    }

    public void update(){
        LiveDataEventBus.get().with(ValueViewModel.class.getSimpleName()).setValue(value);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
        Log.d(TAG, "onCleared() called");
        value = 0;
    }
}
