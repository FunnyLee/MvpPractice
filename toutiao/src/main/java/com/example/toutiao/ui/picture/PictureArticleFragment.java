package com.example.toutiao.ui.picture;

import android.os.Bundle;
import android.view.View;

import com.example.base.base.BaseListFragment;
import com.example.toutiao.bean.phote.PhotoArticleBean;
import com.example.toutiao.viewHolder.picture.PictureArticleViewBinder;

import java.util.List;

import me.drakeet.multitype.Items;
import me.drakeet.multitype.MultiTypeAdapter;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is PictureArticleFragment
 */
public class PictureArticleFragment extends BaseListFragment<IPictureContract.Presenter> implements IPictureContract.View {


    private String mCategoryId;

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

    /**
     * 在onCreateView中执行
     */
    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("categoryId");
    }

    @Override
    protected void initView(View view) {
        super.initView(view);
        mAdapter = new MultiTypeAdapter(mDatas);
        mAdapter.register(PhotoArticleBean.DataBean.class, new PictureArticleViewBinder());
        mRecyclerView.setAdapter(mAdapter);
    }

    /**
     * 在onAcyivityCreated中执行
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
