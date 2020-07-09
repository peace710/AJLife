package me.peace.jetpack.startup;

import android.content.Context;
import android.util.Log;

import java.util.Collections;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.startup.Initializer;
import androidx.work.Configuration;
import androidx.work.WorkManager;

public class WorkManagerInitializer implements Initializer<WorkManager> {
    private static final String TAG = "WorkManagerInitializer";
    @NonNull
    @Override
    public WorkManager create(@NonNull Context context) {
        Log.d(TAG, "WorkManagerInitializer create() called with: context = [" + context + "]");
        Configuration configuration = new Configuration.Builder().build();
        WorkManager.initialize(context,configuration);
        return WorkManager.getInstance(context);
    }

    @NonNull
    @Override
    public List<Class<? extends Initializer<?>>> dependencies() {
        return Collections.emptyList();
    }
}
