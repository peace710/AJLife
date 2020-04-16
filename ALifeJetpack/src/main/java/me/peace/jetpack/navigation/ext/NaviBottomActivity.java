package me.peace.jetpack.navigation.ext;

import android.os.Bundle;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.NavigationUI;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class NaviBottomActivity extends BaseActivity {
    private BottomNavigationView navigation;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_bottom_navi;
    }

    @Override
    protected void init() {
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        controller.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            }
        });

        NavigationUI.setupWithNavController(navigation,controller);
    }

    @Override
    protected void initView() {
        navigation = findViewById(R.id.bottom_menu);
    }

    @Override
    protected void initListener() {

    }
}
