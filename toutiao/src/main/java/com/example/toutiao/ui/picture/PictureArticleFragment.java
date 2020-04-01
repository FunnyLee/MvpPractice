package com.example.toutiao.ui.picture;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.base.base.BaseMvpFragment;
import com.example.toutiao.R;
import com.example.toutiao.entity.phote.PhotoArticleBean;
import com.example.toutiao.viewHolder.picture.PictureArticleViewBinder;

import java.util.List;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is PictureArticleFragment
 */
public class PictureArticleFragment extends BaseMvpFragment<IPictureContract.Presenter> implements IPictureContract.View {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;

    private String mCategoryId;

    protected boolean canLoadMore = false;

    private Items mDatas = new Items();
    private MultiTypeAdapter mAdapter;

    public static PictureArticleFragment newInstance(String categoryId) {
        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);
        PictureArticleFragment fragment = new PictureArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 在BaseFragment中的onCreate方法中执行
     *
     * @param presenter
     */
    @Override
    public void onSetPresenter(IPictureContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new PictureArticlePresenter(this);
        }
    }


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    protected void initView(View view) {
        mRecyclerView = findViewById(R.id.recycler_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mAdapter = new MultiTypeAdapter(mDatas);
        mAdapter.register(PhotoArticleBean.DataBean.class, new PictureArticleViewBinder());
        mRecyclerView.setAdapter(mAdapter);
    }

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
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        mPresenter.doLoadData(mCategoryId);
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
    public void onShowNoMore() {
        canLoadMore = false;
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
