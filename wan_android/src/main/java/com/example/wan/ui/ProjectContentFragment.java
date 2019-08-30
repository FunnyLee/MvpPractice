package com.example.wan.ui;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.adapter.ProjectContentAdapter;
import com.example.wan.contract.IProjectContentContract;
import com.example.wan.entity.ProjectContentInfo;
import com.example.wan.presenter.ProjectContentPresenter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/14
 * Description: This is ProjectContentFragment
 */
@Route(path = RouterManager.PROJECT_CONTENT_FRAGMENT)
public class ProjectContentFragment extends BaseMvpFragment<IProjectContentContract.Presenter> implements IProjectContentContract.View {

    //项目类别Id
    private String mCategoryId;
    //页码
    private int mPageNo = 1;

    private List<ProjectContentInfo.DatasInfo> mDatas = new ArrayList<>();
    private ProjectContentAdapter mAdapter;
    private SmartRefreshLayout mRefreshLayout;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_content;
    }

    @Override
    public void onSetPresenter(IProjectContentContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new ProjectContentPresenter(this);
        }
    }

    @Override
    protected void initView(View view) {
        mRefreshLayout = findViewById(R.id.refresh_layout);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
        mAdapter = new ProjectContentAdapter(mDatas);
        recyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("categoryId");
        onLoadData();
    }

    @Override
    protected void initEvent() {
        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                onLoadData();
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mDatas.clear();
                mPageNo = 1;
                onLoadData();
            }
        });
    }

    @Override
    public void onLoadData() {
        mPresenter.doLoadProjectList(mPageNo, mCategoryId);
    }

    @Override
    public void onShowContentView(List<ProjectContentInfo.DatasInfo> data) {
        mDatas.addAll(data);
        mAdapter.setNewData(mDatas);
        mPageNo += 1;
        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
    }

}
