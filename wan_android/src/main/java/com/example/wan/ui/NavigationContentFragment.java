package com.example.wan.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.adapter.NavigationLeftAdapter;
import com.example.wan.contract.INavigationContract;
import com.example.wan.entity.NavigationContentInfo;
import com.example.wan.presenter.NavigationPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is NavigationContentFragment
 */
@Route(path = RouterManager.NAVIGATION_CONTENT_RAGMENT)
public class NavigationContentFragment extends BaseMvpFragment<INavigationContract.Presenter> implements INavigationContract.View {

    private List<NavigationContentInfo> mLeftDatas = new ArrayList<>();
    private NavigationLeftAdapter mLeftAdapter;

    @Override
    public void onSetPresenter(INavigationContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new NavigationPresenter(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_content;
    }

    @Override
    protected void initView(View view) {
        RecyclerView leftRecyclerView = findViewById(R.id.left_recycler_view);
        RecyclerView rightRecyclerView = findViewById(R.id.right_recycler_view);

        mLeftAdapter = new NavigationLeftAdapter(mLeftDatas);
        leftRecyclerView.setAdapter(mLeftAdapter);
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        mPresenter.doLoadNavigation();
    }

    @Override
    public void onShowContentView(List<NavigationContentInfo> data) {
        mLeftDatas.addAll(data);
        mLeftAdapter.setNewData(mLeftDatas);
    }


}
