package me.peace.app.application;

import android.app.Activity;
import android.app.Application;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.View;

import me.peace.jetpack.startup.Initializer;

public class App extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        registerActivityLifecycleCallbacks(new ActivityLifecycleCallbacks() {
            @Override
            public void onActivityCreated(Activity activity, Bundle savedInstanceState) {

            }

            @Override
            public void onActivityStarted(Activity activity) {
                if (isGray()) {
                    Paint paint = new Paint();
                    ColorMatrix cm = new ColorMatrix();
                    cm.setSaturation(0);
                    paint.setColorFilter(new ColorMatrixColorFilter(cm));
                    activity.getWindow().getDecorView().setLayerType(View.LAYER_TYPE_HARDWARE, paint);
                }
            }

            @Override
            public void onActivityResumed(Activity activity) {

            }

            @Override
            public void onActivityPaused(Activity activity) {

            }

            @Override
            public void onActivityStopped(Activity activity) {

            }

            @Override
            public void onActivitySaveInstanceState(Activity activity, Bundle outState) {

            }

            @Override
            public void onActivityDestroyed(Activity activity) {

            }
        });

        Initializer.getInstance().start(this);
    }

    private boolean isGray(){
        return false;
    }
}
