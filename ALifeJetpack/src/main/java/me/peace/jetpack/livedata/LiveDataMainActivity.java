package me.peace.jetpack.livedata;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.Observer;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class LiveDataMainActivity extends BaseActivity {
    private Button set;
    private Button post;
    private Button set2Child;
    private Button post2Child;
    private Button go;
    private MutableLiveData<String> stringLiveData;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_live_data_main;
    }

    @Override
    protected void init() {
        stringLiveData = new MutableLiveData<>();
        stringLiveData.observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
               t(s);
            }
        });

        LiveDataBus.getInstance().with(LiveDataMainActivity.class.getSimpleName(),String.class).observe(this, new Observer<String>() {
            @Override
            public void onChanged(String s) {
                t(s + "," + LiveDataMainActivity.class.getSimpleName());
            }
        });
    }

    @Override
    protected void initView() {
        set = findViewById(R.id.set);
        post = findViewById(R.id.post);
        set2Child = findViewById(R.id.set2child);
        post2Child = findViewById(R.id.post2child);
        go = findViewById(R.id.go);
    }

    @Override
    protected void initListener() {
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stringLiveData.setValue(getString(R.string.set_value));
            }
        });
        post.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        stringLiveData.postValue(getString(R.string.post_value));
                    }
                }).start();
            }
        });
        set2Child.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LiveDataBus.getInstance().with(LiveDataMainActivity.class.getSimpleName()).setValue(getString(R.string.set_value));
            }
        });
        post2Child.setOnClickListener(new View.OnClickListener() {
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
        go.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LiveDataMainActivity.this,LiveDataChildActivity.class));
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataBus.getInstance().remove(LiveDataMainActivity.class.getSimpleName());
    }
}
