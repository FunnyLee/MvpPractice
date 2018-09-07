package com.example.think.ui.picture;

import android.os.Bundle;
import android.view.View;

import com.example.think.base.BaseListFragment;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is PictureArticleFragment
 */
public class PictureArticleFragment extends BaseListFragment<IPictureContract.Presenter> implements IPictureContract.View {


    private String mCategoryId;

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
    public void setPresenter(IPictureContract.Presenter presenter) {
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

        // TODO: 2018/9/7 新建Adapter 
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

    }


}
