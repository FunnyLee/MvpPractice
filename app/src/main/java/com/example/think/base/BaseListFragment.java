package com.example.think.base;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.think.R;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/8/17
 * Description: This is BaseListFragment
 */
public abstract class BaseListFragment<P extends IBasePresenter> extends LazyLoadFragment<P> implements IBaseListView<P>,SwipeRefreshLayout.OnRefreshListener{

    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;
    protected MultiTypeAdapter mAdapter;

    protected boolean canLoadMore = false;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = view.findViewById(R.id.recycler_view);
        mRefreshLayout = view.findViewById(R.id.refresh_layout);
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager manager = new LinearLayoutManager(getContext());
        mRecyclerView.setLayoutManager(manager);
        //下拉刷新
        mRefreshLayout.setOnRefreshListener(this);
    }


    @Override
    public void onShowLoading() {
        /**
         * 列表Fragment，显示加载视图，设置mRefreshLayout的刷新状态为true
         */
        mRefreshLayout.post(() -> {
            mRefreshLayout.setRefreshing(true);
        });
    }

    @Override
    public void onHideLoading() {
        /**
         * 列表Fragment，隐藏加载视图，设置mRefreshLayout刷新状态为false
         */
        mRefreshLayout.post(() -> {
            mRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onShowNetError() {
        /**
         * 列表Fragment，加载时显示网络错误
         */
        Toast.makeText(getContext(), "网络不给力", Toast.LENGTH_SHORT).show();
        mAdapter.setItems(new Items());
        mAdapter.notifyDataSetChanged();
        canLoadMore = false;
    }

    @Override
    public void onShowNoMore() {
        /**
         * 列表Fragment，加载完毕，无更多数据
         */
        // TODO: 2018/9/6 无更多数据实现
        canLoadMore = false;
    }

    @Override
    public void onRefresh() {
        LinearLayoutManager manager = (LinearLayoutManager) mRecyclerView.getLayoutManager();
        int firstVisibleItemPosition = manager.findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            mPresenter.doRefresh();
            return;
        }
        mRecyclerView.scrollToPosition(5);
        mRecyclerView.smoothScrollToPosition(0);
    }
}
