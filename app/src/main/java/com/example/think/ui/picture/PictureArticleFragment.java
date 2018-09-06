package com.example.think.ui.picture;

import com.example.think.base.BaseListFragment;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is PictureArticleFragment
 */
public class PictureArticleFragment extends BaseListFragment<IPictureContract.Presenter> implements IPictureContract.View {

    
    public static PictureArticleFragment newInstance() {
        PictureArticleFragment fragment = new PictureArticleFragment();
        return fragment;
    }

    /**
     * 在BaseFragment中的onCreate方法中执行
     * @param presenter
     */
    @Override
    public void setPresenter(IPictureContract.Presenter presenter) {

    }

    /**
     * 在onCreateView中执行
     */
    @Override
    protected void initData() {

    }

    /**
     * 在onAcyivityCreated中执行
     */
    @Override
    public void fetchData() {
        onShowLoading();
        onLoadData();
    }

    @Override
    public void onLoadData() {

    }

    @Override
    public void onSetAdapter(List<?> list) {

    }




}
