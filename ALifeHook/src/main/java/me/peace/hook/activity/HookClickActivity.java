package me.peace.hook.activity;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import me.peace.base.BaseActivity;
import me.peace.hook.Hook;
import me.peace.hook.HookUtils;
import me.peace.hook.R;
import me.peace.utils.DateUtils;
import me.peace.utils.StackTraceUtils;

public class HookClickActivity extends BaseActivity {
    private static final String TAG = "HookClickActivity";
    private Button click;
    private TextView text;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_hook_click;
    }

    @Override
    protected void init() {
        hook(click);
    }

    @Override
    protected void initView() {
        click = findViewById(R.id.click);
        text = findViewById(R.id.text);
        StackTraceUtils.trace(getClass());
    }

    @Override
    protected void initListener() {
        click.setOnClickListener(clickListener);
    }

    private View.OnClickListener clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            t(R.string.toast_content);
        }
    };

    private void hook(View v){
        try {
            //反射获取View里listenerInfo对象，调用了View的getListenerInfo方法
            Object obj = HookUtils.getObjectWithMethod(View.class,v,"getListenerInfo");
            Class clazz = Class.forName("android.view.View$ListenerInfo");
            //反射获取ListerInfo中的mOnClickListener对象
            Field field = HookUtils.getField(clazz,"mOnClickListener");
            View.OnClickListener src = (View.OnClickListener) field.get(obj);
            //创建动态代理，在mOnClickListener外包含我们的逻辑
            View.OnClickListener proxy = Hook.create(src,invokeListener);
            //将代理的listener重新设入listenerInfo里
            HookUtils.setField(obj,field,proxy);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    Hook.OnInvokeListener invokeListener = new Hook.OnInvokeListener() {
        private String str;
        private long time;

        @Override
        public void beforeInvoke(Object delegate, Object proxy, Method method, Object[] args) {
            time = System.currentTimeMillis();
            str = "[" + method.getName() + "] start at " + DateUtils.formatDateTime(time);
        }

        @Override
        public void afterInvoke(Object delegate, Object proxy, Method method, Object[] args, Object result) {
            long current = System.currentTimeMillis();
            str = str + "\n";
            str = str + "[" + method.getName() + "] end at " + DateUtils.formatDateTime(current);
            str = str + "\n";
            str = str + "[" + method.getName() + "] use " + (current - time) + "ms";
            text.setText(str);
        }
    };

}
