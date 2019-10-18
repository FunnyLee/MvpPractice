package com.example.wan.ui;

import android.support.annotation.IdRes;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.base.BaseActivity;
import com.example.base.base.BaseFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterManager.WAN_ANDROID_MAIN_ACTIVITY)
public class WanAndroidMainActivity extends BaseActivity {

    private BottomNavigationView mBottomNavigationView;

    private List<BaseFragment> mFragmentList = new ArrayList<>();

    private Fragment mCurrentFragment = null;


    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_android_main;
    }

    @Override
    protected void initView() {
        Toast.makeText(this, getClass().getName(), Toast.LENGTH_SHORT).show();
        mBottomNavigationView = findViewById(R.id.bottom_navigation);
        initFragment();
        showFragment(0);
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
                    showFragment(0);
                } else if (id == R.id.action_project) {
                    showFragment(1);
                } else if (id == R.id.action_system) {
                    showFragment(2);
                } else if (id == R.id.action_me) {

                }
                return true;
            }
        });
    }

    private void initFragment() {
        HomeFragment homeFragment = (HomeFragment) ARouter.getInstance().build(RouterManager.HOME_FRAGMENT).navigation();
        ProjectFragment projectFragment = (ProjectFragment) ARouter.getInstance().build(RouterManager.PROJECT_FRAGMENT).navigation();
        SystemFragment systemFragment = (SystemFragment) ARouter.getInstance().build(RouterManager.SYSTEM_FRAGMENT).navigation();
        mFragmentList.add(homeFragment);
        mFragmentList.add(projectFragment);
        mFragmentList.add(systemFragment);
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

    /**
     * 使用show、hide来管理fragment
     */
    private void showFragment(int position) {
        if (mFragmentList != null && mFragmentList.size() > 0) {
            Fragment fragment = mFragmentList.get(position);
            if (null != fragment && mCurrentFragment != fragment) {
                FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                if (mCurrentFragment != null) {
                    transaction.hide(mCurrentFragment);
                }
                mCurrentFragment = fragment;
                if (!fragment.isAdded()) {
                    transaction.add(R.id.frame_layout, fragment);
                } else {
                    transaction.show(fragment);
                }
                transaction.commit();
            }
        }
    }
}
