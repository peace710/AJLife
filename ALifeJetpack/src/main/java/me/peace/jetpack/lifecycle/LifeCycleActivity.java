package me.peace.jetpack.lifecycle;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class LifeCycleActivity extends BaseActivity {
    private BuryingPointLifecycle lifecycle;
    private Button go;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_life_cycle_main;
    }

    @Override
    protected void init() {
        lifecycle = new BuryingPointLifecycle();
        getLifecycle().addObserver(lifecycle);
    }

    @Override
    protected void initView() {
        go = findViewById(R.id.go);
    }

    @Override
    protected void initListener() {
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LifeCycleActivity.this,LifeCycleChildActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        getLifecycle().removeObserver(lifecycle);
    }
}
