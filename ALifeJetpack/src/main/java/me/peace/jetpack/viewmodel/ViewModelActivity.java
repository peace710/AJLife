package me.peace.jetpack.viewmodel;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;
import me.peace.jetpack.livedata.ext.LiveDataEventBus;

public class ViewModelActivity extends BaseActivity {
    private TextView text;
    private TextView view_model_text;
    private Button increment;
    private int value;
    private ValueViewModel model;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_view_model;
    }

    @Override
    protected void init() {
        LiveDataEventBus.get().with(ValueViewModel.class.getSimpleName(),int.class).observe(this,
            new Observer<Integer>() {
                @Override
                public void onChanged(Integer integer) {
                    view_model_text.setText(getString(R.string.view_model) + integer);
                }
            });
        model = new ViewModelProvider(this).get(ValueViewModel.class);
    }

    @Override
    protected void initView() {
        text = findViewById(R.id.text);
        view_model_text = findViewById(R.id.view_model_text);
        increment = findViewById(R.id.increment);
    }

    @Override
    protected void initListener() {
        increment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                value++;
                text.setText(getString(R.string.common) + value);
                model.increase();
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        LiveDataEventBus.get().leave(ValueViewModel.class.getSimpleName());
    }
}
