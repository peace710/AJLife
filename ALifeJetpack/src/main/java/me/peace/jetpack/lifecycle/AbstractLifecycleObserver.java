package me.peace.jetpack.lifecycle;

import org.jetbrains.annotations.NotNull;

import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.OnLifecycleEvent;

public interface AbstractLifecycleObserver extends LifecycleObserver {
    @OnLifecycleEvent(Lifecycle.Event.ON_CREATE)
    void onCreate(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_START)
    void onStart(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_RESUME)
    void onResume(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_PAUSE)
    void onPause(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_STOP)
    void onStop(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    void onDestroy(@NotNull LifecycleOwner lifecycleOwner);

    @OnLifecycleEvent(Lifecycle.Event.ON_ANY)
    void onAny(@NotNull LifecycleOwner lifecycleOwner, @NotNull Lifecycle.Event event);
}
