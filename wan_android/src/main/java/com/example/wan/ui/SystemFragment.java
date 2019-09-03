package com.example.wan.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.adapter.ViewPagerFragmentAdapter;
import com.example.base.base.BaseFragment;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is SystemFragment
 */
@Route(path = RouterManager.SYSTEM_FRAGMENT)
public class SystemFragment extends BaseFragment {

    private List<BaseMvpFragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system;
    }

    @Override
    public void initImmersionBar() {
        ImmersionBar.with(getActivity()).statusBarView(R.id.status_view).init();
    }

    @Override
    protected void initView(View view) {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        mViewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    protected void initData() {
        mTitleList.add("体系");
        mTitleList.add("导航");
        SystemContentFragment systemContentFragment = (SystemContentFragment) ARouter.getInstance().build(RouterManager.SYSTEM_CONTENT_FRAGMENT).navigation();
        NavigationContentFragment navigationContentFragment = (NavigationContentFragment) ARouter.getInstance().build(RouterManager.NAVIGATION_CONTENT_RAGMENT).navigation();
        mFragmentList.add(systemContentFragment);
        mFragmentList.add(navigationContentFragment);
        ViewPagerFragmentAdapter fragmentAdapter = new ViewPagerFragmentAdapter(getFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(fragmentAdapter);
    }
}
