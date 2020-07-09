package me.peace.jetpack.startup;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.startup.AppInitializer;

public class Initializer {
    private static volatile Initializer sInstance;

    private static final Object sLock = new Object();

    private Initializer(){

    }

    public static Initializer getInstance() {
        if (sInstance == null) {
            synchronized (sLock) {
                if (sInstance == null) {
                    sInstance = new Initializer();
                }
            }
        }
        return sInstance;
    }

    public void start(Context context){
        AppInitializer.getInstance(context).initializeComponent(LoggerInitializer.class);
    }
}
