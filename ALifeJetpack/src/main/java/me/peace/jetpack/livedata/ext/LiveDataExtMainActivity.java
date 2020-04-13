package me.peace.jetpack.livedata.ext;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Observer;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;
import me.peace.jetpack.livedata.LiveDataBus;

public class LiveDataExtMainActivity extends BaseActivity {
    private Button set;
    private Button post;
    private Button go;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_live_data_ext_main;
    }

    @Override
    protected void init() {
        LiveDataEventBus.get().with(LiveDataExtMainActivity.class.getSimpleName(),String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                t(s + "," + LiveDataExtMainActivity.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initView() {
        set = findViewById(R.id.set);
        post = findViewById(R.id.post);
        go = findViewById(R.id.go);
    }

    @Override
    protected void initListener() {
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataEventBus.get().with(LiveDataExtMainActivity.class.getSimpleName()).setValue(getString(R.string.set_value));
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LiveDataEventBus.get().with(LiveDataExtMainActivity.class.getSimpleName()).postValue(getString(R.string.post_value));
                    }
                }).start();
            }
        });
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiveDataExtMainActivity.this,
                    LiveDataExtChildActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataEventBus.get().leave(LiveDataExtMainActivity.class.getSimpleName());
    }
}
