package com.example.toutiao.ui.news;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.base.base.BaseMvpFragment;
import com.example.toutiao.R;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;
import com.example.toutiao.viewHolder.news.NewsArticleImgViewBinder;
import com.example.toutiao.viewHolder.news.NewsArticleTextViewBinder;
import com.example.toutiao.viewHolder.news.NewsArticleVideoViewBinder;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is NewsArticleView
 */
public class NewsArticleFragment extends BaseMvpFragment<IArticleContract.Presenter> implements IArticleContract.View {

    RecyclerView mRecyclerView;
    SwipeRefreshLayout mRefreshLayout;

    private String mCategoryId;

    protected MultiTypeAdapter mAdapter;


    protected boolean canLoadMore = false;

    private Items mDatas = new Items();

    public static NewsArticleFragment newInstance(String categoryId) {
        Bundle args = new Bundle();
        args.putString("channelId", categoryId);
        NewsArticleFragment fragment = new NewsArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 这个方法在BaseFragment中的onCreate方法里执行
     *
     * @param presenter
     */
    @Override
    public void onSetPresenter(IArticleContract.Presenter presenter) {
        if (presenter == null) {
            mPresenter = new NewsArticlePresenter(this);
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
        //注册Adapter的多条目
        mAdapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(), new NewsArticleTextViewBinder(), new NewsArticleVideoViewBinder())
                .withClassLinker(new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(int position, @NonNull MultiNewsArticleDataBean multiNewsArticleDataBean) {
                        if (multiNewsArticleDataBean.isHas_video()) {
                            //返回视频条目
                            return NewsArticleVideoViewBinder.class;
                        }

                        List<MultiNewsArticleDataBean.ImageListBean> image_list = multiNewsArticleDataBean.getImage_list();
                        if (image_list != null && image_list.size() != 0) {
                            //返回图片文字条目
                            return NewsArticleImgViewBinder.class;
                        }

                        //返回普通文字条目
                        return NewsArticleTextViewBinder.class;
                    }
                });
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 这个方法在BaseFragment中的onCreateView方法里执行
     */
    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("channelId");
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
        /**
         * 列表Fragment，加载完毕，无更多数据
         */
        // TODO: 2018/9/6 无更多数据实现
        canLoadMore = false;
    }

    /**
     * 在presenter网络请求的回调中执行
     *
     * @param list
     */
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
}
