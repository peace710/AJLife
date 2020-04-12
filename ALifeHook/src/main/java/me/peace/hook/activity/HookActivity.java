package me.peace.hook.activity;

import android.app.Activity;
import android.app.Instrumentation;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.IBinder;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import me.peace.base.BaseActivity;
import me.peace.hook.HookUtils;
import me.peace.hook.R;

public class HookActivity extends BaseActivity {
    private static final String TAG = "HookActivity";
    private Button click;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_hook;
    }

    @Override
    protected void init() {
        hook();
    }

    @Override
    protected void initView() {
        click = findViewById(R.id.click);
    }

    @Override
    protected void initListener() {
        click.setOnClickListener(clickListener);
    }

    View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent(HookActivity.this,NewActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            getApplication().startActivity(intent);
        }
    };



    private void hook(){
        try {
            Class clazz = Class.forName("android.app.ActivityThread");
            Field thread = HookUtils.getField(clazz,"sCurrentActivityThread");
            Object obj = thread.get(null);
            Field field = HookUtils.getField(clazz,"mInstrumentation");
            Instrumentation instrumentation = (Instrumentation) field.get(obj);
            HookInstrumentation hookInstrumentation = new HookInstrumentation(instrumentation);
            field.set(obj,hookInstrumentation);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e){
            e.printStackTrace();
        }
    }

    static class HookInstrumentation extends Instrumentation{
        Instrumentation src;

        public HookInstrumentation(Instrumentation src) {
            this.src = src;
        }

        public Activity newActivity(ClassLoader cl, String className,
                                    Intent intent)
            throws InstantiationException, IllegalAccessException,
            ClassNotFoundException {
            return src.newActivity(cl,className,intent);
        }

        public ActivityResult execStartActivity(
            Context who, IBinder contextThread, IBinder token, String target,
            Intent intent, int requestCode, Bundle options){
            ActivityResult result = null;
            if (src != null){
                Log.i(TAG,"before start activity");
                result = invokeStartActivity(src,who,contextThread,token,target,
                    intent,requestCode,options);
                Log.i(TAG,"after start activity");
            }
            return result;
        }

        private ActivityResult invokeStartActivity(Instrumentation instrumentation,Context who, IBinder contextThread, IBinder token, String target,
                                         Intent intent, int requestCode, Bundle options){
            try {
                Method method = instrumentation.getClass().getDeclaredMethod(
                                "execStartActivity", Context.class, IBinder.class,IBinder.class,
                    String.class, Intent.class,int.class, Bundle.class);
                method.setAccessible(true);
                return (ActivityResult) method.invoke(instrumentation,who,contextThread,token,target,intent,
                    requestCode,
                    options);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e){
                e.printStackTrace();
            } catch (InvocationTargetException e){
                e.printStackTrace();
            }
            return null;
        }
    }
}
