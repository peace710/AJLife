package me.peace.app.activity;

import android.content.res.Resources;
import android.os.Bundle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.peace.app.R;
import me.peace.app.adapter.StringAdapter;
import me.peace.app.gray.GrayActivity;
import me.peace.app.media.AudioMediaActivity;
import me.peace.base.BaseActivity;
import me.peace.hook.activity.HookActivity;
import me.peace.hook.activity.HookClickActivity;
import me.peace.jetpack.lifecycle.LifeCycleActivity;
import me.peace.jetpack.livedata.LiveDataMainActivity;
import me.peace.jetpack.livedata.ext.LiveDataExtMainActivity;
import me.peace.jetpack.navigation.NaviActivity;
import me.peace.jetpack.navigation.deeplink.DeepLinkActivity;
import me.peace.jetpack.navigation.ext.NaviExtActivity;
import me.peace.jetpack.viewmodel.ViewModelActivity;

public class RootActivity extends BaseActivity {

    private Class[] targetClass = new Class[]{GrayActivity.class,AudioMediaActivity.class,
        HookClickActivity.class ,
        HookActivity.class,
        LiveDataMainActivity.class, LiveDataExtMainActivity.class, LifeCycleActivity.class,
        ViewModelActivity.class, NaviActivity.class, NaviExtActivity.class, DeepLinkActivity.class};

    private RecyclerView root;
    private List<String> list;
    private List<Class> target;
    private StringAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_life_root;
    }

    @Override
    protected void init() {
        initMenu();
    }

    @Override
    protected void initView() {
        root = findViewById(R.id.root);
    }

    @Override
    protected void initListener() {

    }

    private void initMenu(){
        Resources resources = getResources();
        list = new ArrayList<>(Arrays.asList(resources.getStringArray(R.array.root)));
        target = new ArrayList<>(Arrays.asList(targetClass));
        adapter = new StringAdapter(this,list,target);
        root.setLayoutManager(new LinearLayoutManager(this));
        root.setAdapter(adapter);
    }
}
