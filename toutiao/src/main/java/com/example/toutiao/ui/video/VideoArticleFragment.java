package com.example.toutiao.ui.video;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.base.base.BaseMvpFragment;
import com.example.toutiao.R;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;
import com.example.toutiao.viewHolder.news.NewsArticleVideoViewBinder;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is VideoArticleFragment
 */
public class VideoArticleFragment extends BaseMvpFragment<IVideoContract.Presenter> implements IVideoContract.View {

    private String mCategoryId;
    private Items mDatas = new Items();
    private MultiTypeAdapter mAdapter;
    protected RecyclerView mRecyclerView;
    protected SwipeRefreshLayout mRefreshLayout;

    protected boolean canLoadMore = false;

    public static VideoArticleFragment newInstance(String categoryId) {
        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);
        VideoArticleFragment fragment = new VideoArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onSetPresenter(IVideoContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new VideoArticlePresenter(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        mAdapter = new MultiTypeAdapter(mDatas);
        mAdapter.register(MultiNewsArticleDataBean.class, new NewsArticleVideoViewBinder());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 在onCreateView方法中执行
     */
    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("categoryId");
        onLoadData();
    }

    @Override
    protected void initEvent() {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager manager = (LinearLayoutManager) recyclerView.getLayoutManager();
                int itemCount = manager.getItemCount();
                int lastVisibleItemPosition = manager.findLastVisibleItemPosition();
                if (lastVisibleItemPosition == itemCount - 1) {
                    if (canLoadMore) {
                        canLoadMore = false;
                        mPresenter.doLoadMoreData();
                        Toast.makeText(getContext(), "加载更多", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
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
        });
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        mPresenter.doLoadData(mCategoryId);
    }

    @Override
    public void onShowNoMore() {
        canLoadMore = false;
    }

    @Override
    public void onShowContentView(List<?> list) {
        mDatas.clear();
        mDatas.addAll(list);
        mAdapter.notifyDataSetChanged();
        canLoadMore = true;
        mRecyclerView.stopScroll();
    }


    @Override
    public void onShowLoading() {
        mRefreshLayout.post(() -> {
            mRefreshLayout.setRefreshing(true);
        });
    }

    @Override
    public void onHideLoading() {
        mRefreshLayout.post(() -> {
            mRefreshLayout.setRefreshing(false);
        });
    }

    @Override
    public void onShowNetError() {
        Toast.makeText(getContext(), "网络不给力", Toast.LENGTH_SHORT).show();
        mAdapter.setItems(new Items());
        mAdapter.notifyDataSetChanged();
        canLoadMore = false;
    }




}
