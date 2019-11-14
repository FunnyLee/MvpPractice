package com.example.wan.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.example.base.adapter.ViewPagerFragmentAdapter;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.contract.IProjectContract;
import com.example.wan.entity.ProjectCategoryInfo;
import com.example.wan.presenter.ProjectPresenter;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is ProjectFragment
 */

@Route(path = RouterManager.PROJECT_FRAGMENT)
public class ProjectFragment extends BaseMvpFragment<IProjectContract.Presenter> implements IProjectContract.View {

    private List<BaseMvpFragment> mFragmentList = new ArrayList<>();
    private List<String> mTitleList = new ArrayList<>();
    private ViewPager mViewPager;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project;
    }

    @Override
    public void initImmersionBar() {
        View statusView = findViewById(R.id.status_view);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams( LinearLayout.LayoutParams.MATCH_PARENT,ImmersionBar.getStatusBarHeight(this));
        statusView.setLayoutParams(params);
        ImmersionBar.with(this).statusBarView(R.id.status_view).init();
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
        mViewPager = findViewById(R.id.view_pager);

        tabLayout.setupWithViewPager(mViewPager);
        tabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
    }

    @Override
    protected void initData() {
        mPresenter.doLoadProjectCategory();
    }

    @Override
    public void onShowContentView(List<ProjectCategoryInfo> data) {
        for (ProjectCategoryInfo info : data) {
            mTitleList.add(info.name);
            ProjectContentFragment projectContentFragment = (ProjectContentFragment) ARouter.getInstance()
                    .build(RouterManager.PROJECT_CONTENT_FRAGMENT)
                    .withString("categoryId", info.id)
                    .navigation();
            mFragmentList.add(projectContentFragment);
        }
        //一定要使用getChildFragmentManager，否则会出现页面加载错误
        ViewPagerFragmentAdapter fragmentAdapter = new ViewPagerFragmentAdapter(getChildFragmentManager(), mFragmentList, mTitleList);
        mViewPager.setAdapter(fragmentAdapter);
    }
}
