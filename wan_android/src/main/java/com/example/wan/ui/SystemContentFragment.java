package com.example.wan.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.adapter.SystemContentAdapter;
import com.example.wan.contract.ISystemContract;
import com.example.wan.entity.SystemContentInfo;
import com.example.wan.presenter.SystemPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is SystemContentFragment
 */
@Route(path = RouterManager.SYSTEM_CONTENT_FRAGMENT)
public class SystemContentFragment extends BaseMvpFragment<ISystemContract.Presenter> implements ISystemContract.View {

    private List<SystemContentInfo> mDatas = new ArrayList<>();
    private SystemContentAdapter mAdapter;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_system_content;
    }


    @Override
    public void onSetPresenter(ISystemContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new SystemPresenter(this);
        }
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new SystemContentAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        mPresenter.doLoadSystem();
    }

    @Override
    public void onShowContentView(List<SystemContentInfo> data) {
        mAdapter.setNewData(data);
    }
}
