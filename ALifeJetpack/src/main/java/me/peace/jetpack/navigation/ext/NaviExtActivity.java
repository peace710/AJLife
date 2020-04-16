package me.peace.jetpack.navigation.ext;

import android.content.Intent;
import android.view.View;
import android.widget.Button;

import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class NaviExtActivity extends BaseActivity {
    private Button toolbar;
    private Button bottom;
    private Button drawer;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_navi_ext;
    }

    @Override
    protected void init() {

    }

    @Override
    protected void initView() {
        toolbar = findViewById(R.id.toolbar);
        bottom = findViewById(R.id.bottom);
        drawer = findViewById(R.id.drawer);
    }

    @Override
    protected void initListener() {
        toolbar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NaviExtActivity.this, NaviMenuActivity.class));
            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NaviExtActivity.this, NaviBottomActivity.class));
            }
        });

        drawer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(NaviExtActivity.this, NaviDrawerActivity.class));
            }
        });
    }
}
