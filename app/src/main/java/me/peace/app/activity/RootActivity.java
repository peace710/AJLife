package me.peace.app.activity;

import android.content.res.Resources;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import me.peace.app.R;
import me.peace.app.adapter.StringAdapter;
import me.peace.app.base.BaseActivity;

public class RootActivity extends BaseActivity {
    private RecyclerView root;
    private List<String> list;
    private StringAdapter adapter;

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
        adapter = new StringAdapter(this,list);
        root.setLayoutManager(new LinearLayoutManager(this));
        root.setAdapter(adapter);
    }
}
