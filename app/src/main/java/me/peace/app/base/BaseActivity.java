package me.peace.app.base;

import android.os.Bundle;

import androidx.annotation.Nullable;
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
}
