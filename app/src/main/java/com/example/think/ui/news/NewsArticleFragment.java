package com.example.think.ui.news;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import com.example.think.R;
import com.example.think.base.BaseListFragment;
import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.viewHolder.news.NewsArticleImgViewBinder;
import com.example.think.viewHolder.news.NewsArticleTextViewBinder;
import com.example.think.viewHolder.news.NewsArticleVideoViewBinder;

import java.util.List;

import butterknife.BindView;
import me.drakeet.multitype.ClassLinker;
import me.drakeet.multitype.ItemViewBinder;
import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is NewsArticleView
 */
public class NewsArticleFragment extends BaseListFragment<IArticleContract.Presenter> implements IArticleContract.View {

    @BindView(R.id.recycler_view)
    RecyclerView mRecyclerView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;

    private String mCategoryId;

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
    public void setPresenter(IArticleContract.Presenter presenter) {
        if (presenter == null) {
            this.mPresenter = new NewsArticlePresenter(this);
        }
    }

    /**
     * 这个方法在BaseFragment中的onCreateView方法里执行
     */
    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("channelId");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MultiTypeAdapter(mDatas);
        //注册Adapter的多条目
        mAdapter.register(MultiNewsArticleDataBean.class)
                .to(new NewsArticleImgViewBinder(), new NewsArticleTextViewBinder(),new NewsArticleVideoViewBinder())
                .withClassLinker(new ClassLinker<MultiNewsArticleDataBean>() {
                    @NonNull
                    @Override
                    public Class<? extends ItemViewBinder<MultiNewsArticleDataBean, ?>> index(int position, @NonNull MultiNewsArticleDataBean multiNewsArticleDataBean) {
                        if(multiNewsArticleDataBean.isHas_video()){
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

    /**
     * 这个方法在LazyLoadFragment生命周期onActivityCreated里执行
     */
    @Override
    public void fetchData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        onShowLoading();
        mPresenter.doLoadData(mCategoryId);
    }

    /**
     * 在presenter网络请求的回调中执行
     * @param list
     */
    @Override
    public void onSetAdapter(List<?> list) {
        mDatas.clear();
        mDatas.addAll(list);
        mAdapter.notifyDataSetChanged();
        canLoadMore = true;
        mRecyclerView.stopScroll();
    }
}
