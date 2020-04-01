package com.example.wan.ui;

import androidx.core.app.Fragment;
import androidx.core.app.FragmentManager;
import androidx.core.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.base.BaseActivity;
import com.example.base.base.BaseFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.gyf.immersionbar.ImmersionBar;

/**
 * 定制化类
 */
@Route(path = RouterManager.WAN_ANDROID_MAIN_ACTIVITY)
public class WanAndroidMainActivityCustom extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_android_main;
    }

    @Override
    protected void initStatusBarColor() {
        //设置透明状态栏
        ImmersionBar.with(this).transparentStatusBar().init();
    }

    @Override
    protected void initView() {
        Toast.makeText(this, getClass().getName(), Toast.LENGTH_SHORT).show();
        mBottomNavigationView = findViewById(R.id.bottom_navigation);

        //默认选中首页
        BaseFragment homeFragment = (BaseFragment) ARouter.getInstance().build(RouterManager.HOME_FRAGMENT).navigation();
        switchFragment(homeFragment, R.id.frame_layout);
    }

    @Override
    protected void initData() {

    }

    @Override
    protected void initEvent() {
        mBottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.action_home) {
                    HomeFragment homeFragment = (HomeFragment) ARouter.getInstance().build(RouterManager.HOME_FRAGMENT).navigation();
                    switchFragment(homeFragment, R.id.frame_layout);
                } else if (id == R.id.action_project) {
                    ProjectFragment projectFragment = (ProjectFragment) ARouter.getInstance().build(RouterManager.PROJECT_FRAGMENT).navigation();
                    switchFragment(projectFragment, R.id.frame_layout);
                } else if (id == R.id.action_system) {
                    SystemFragment systemFragment = (SystemFragment) ARouter.getInstance().build(RouterManager.SYSTEM_FRAGMENT).navigation();
                    switchFragment(systemFragment, R.id.frame_layout);
                } else if (id == R.id.action_me) {

                }
                return true;
            }
        });
    }

    /**
     * 替换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment, @IdRes int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.commit();
    }
}
