package com.example.wan.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.contract.IProjectContract;
import com.example.wan.presenter.ProjectPresenter;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is ProjectFragment
 */

@Route(path = RouterManager.PROJECT_FRAGMENT)
public class ProjectFragment extends BaseMvpFragment<IProjectContract.Presenter> implements IProjectContract.View {

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void onSetPresenter(IProjectContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new ProjectPresenter(this);
        }
    }

    @Override
    protected void initView(View view) {
        TabLayout tabLayout = findViewById(R.id.tab_layout);
        ViewPager viewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(viewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadProjectCategory();
    }
}
