package me.peace.jetpack.navigation.ext;

import android.os.Bundle;

import com.google.android.material.navigation.NavigationView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.navigation.NavController;
import androidx.navigation.NavDestination;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;
import me.peace.base.BaseActivity;
import me.peace.jetpack.R;

public class NaviDrawerActivity extends BaseActivity {
    private AppBarConfiguration appBarConfiguration;
    private DrawerLayout drawerLayout;
    private NavigationView navigation;

    @Override
    protected int offerContentViewId() {
        return R.layout.activity_drawer_navi;
    }

    @Override
    protected void init() {
        NavController controller = Navigation.findNavController(this,R.id.nav_host_fragment);
        appBarConfiguration =
            new AppBarConfiguration.Builder(controller.getGraph()).setDrawerLayout(drawerLayout).build();
        NavigationUI.setupActionBarWithNavController(this,controller,appBarConfiguration);
        NavigationUI.setupWithNavController(navigation,controller);
        controller.addOnDestinationChangedListener(new NavController.OnDestinationChangedListener() {
            @Override
            public void onDestinationChanged(@NonNull NavController controller, @NonNull NavDestination destination, @Nullable Bundle arguments) {
            }
        });
    }

    @Override
    protected void initView() {
        drawerLayout = findViewById(R.id.drawer);
        navigation = findViewById(R.id.navi_drawer);
    }

    @Override
    protected void initListener() {

    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController controller = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(controller, appBarConfiguration) || super.onSupportNavigateUp();
    }
}
