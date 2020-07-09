package me.peace.jetpack.startup;

import android.content.Context;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.work.WorkManager;

public class LoggerInitializer implements Initializer<JetpackLogger> {
    private static final String TAG = "LoggerInitializer";
    @NonNull
    @Override
    public JetpackLogger create(@NonNull Context context) {
        Log.d(TAG, "LoggerInitializer create() called with: context = [" + context + "]");
        return new JetpackLogger(WorkManager.getInstance(context));
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        List<Class<? extends Initializer<?>>> list = new ArrayList<>();
        list.add(WorkManagerInitializer.class);
        return list;
    }
}
