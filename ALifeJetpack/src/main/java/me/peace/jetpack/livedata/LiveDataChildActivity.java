package me.peace.jetpack.livedata;

import android.view.View;
import android.widget.Button;

import androidx.lifecycle.Observer;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class LiveDataChildActivity extends BaseActivity {
    private Button set;
    private Button post;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_live_data_child;
    }

    @Override
    protected void init() {
        LiveDataBus.getInstance().with(LiveDataMainActivity.class.getSimpleName(),String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                t(s + "," + LiveDataChildActivity.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initView() {
        set = findViewById(R.id.set);
        post = findViewById(R.id.post);
    }

    @Override
    protected void initListener() {
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBus.getInstance().with(LiveDataMainActivity.class.getSimpleName()).setValue(getString(R.string.set_value));
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        LiveDataBus.getInstance().with(LiveDataMainActivity.class.getSimpleName()).postValue(getString(R.string.post_value));
                    }
                }).start();
            }
        });
    }

}
