package com.example.think.ui.video;

import android.os.Bundle;
import android.view.View;

import com.example.think.base.BaseListFragment;
import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.viewHolder.news.NewsArticleVideoViewBinder;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is VideoArticleFragment
 */
public class VideoArticleFragment extends BaseListFragment<IVideoContract.Presenter> implements IVideoContract.View {

    private String mCategoryId;
    private Items mDatas = new Items();
    private MultiTypeAdapter mAdapter;


    public static VideoArticleFragment newInstance(String categoryId) {

        Bundle args = new Bundle();
        args.putString("categoryId", categoryId);
        VideoArticleFragment fragment = new VideoArticleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    /**
     * 在BaseFragment中的onCreate方法中执行
     *
     * @param presenter
     */
    @Override
    public void onSetPresenter(IVideoContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new VideoArticlePresenter(this);
        }
    }

    /**
     * 在onCreateView方法中执行
     */
    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("categoryId");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MultiTypeAdapter(mDatas);
        mAdapter.register(MultiNewsArticleDataBean.class, new NewsArticleVideoViewBinder());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 在onActivityCreated方法中执行
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


    @Override
    public void onSetAdapter(List<?> list) {
        mDatas.clear();
        mDatas.addAll(list);
        mAdapter.notifyDataSetChanged();
        canLoadMore = true;
        mRecyclerView.stopScroll();
    }


}
