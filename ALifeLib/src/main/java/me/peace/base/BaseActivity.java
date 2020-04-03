package me.peace.base;

import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(offerContentViewId());
        initView();
        initListener();
        init();
    }

    protected abstract int offerContentViewId();

    protected abstract void init();

    protected abstract void initView();

    protected abstract void initListener();

    protected void t(@StringRes int resId){
        Toast.makeText(this,resId,Toast.LENGTH_LONG).show();
    }

    protected void t(String text){
        Toast.makeText(this,text,Toast.LENGTH_LONG).show();
    }
}
