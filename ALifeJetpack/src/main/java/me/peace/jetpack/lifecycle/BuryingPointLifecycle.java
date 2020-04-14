package me.peace.jetpack.lifecycle;

import android.util.Log;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleOwner;

public class BuryingPointLifecycle implements AbstractLifecycleObserver {
    private static final String TAG = "BuryingPointLifecycle";
    private BuryingPoint point;

    @Override
    public void onCreate(@NotNull LifecycleOwner lifecycleOwner) {
        point = BuryingPoint.get();
        point.init();
        point.markLifecycle("onCreate");
    }

    @Override
    public void onStart(@NotNull LifecycleOwner lifecycleOwner) {
        point.markLifecycle("onStart");
    }

    @Override
    public void onResume(@NotNull LifecycleOwner lifecycleOwner) {
        point.markLifecycle("onResume");
    }

    @Override
    public void onPause(@NotNull LifecycleOwner lifecycleOwner) {
        point.markLifecycle("onPause");
    }

    @Override
    public void onStop(@NotNull LifecycleOwner lifecycleOwner) {
        point.markLifecycle("onStop");
    }

    @Override
    public void onDestroy(@NotNull LifecycleOwner lifecycleOwner) {
        point.markLifecycle("onDestroy");
        point.destroy();
    }

    @Override
    public void onAny(@NotNull LifecycleOwner lifecycleOwner, @NotNull Lifecycle.Event event) {
        Log.d(TAG, "onAny() called with: lifecycleOwner = [" + lifecycleOwner + "], event = [" + event + "]");
        point.markLifecycle("onAny");
    }
}
