package com.example.toutiao.ui.news;

import com.example.base.base.BaseMvpFragment;
import com.example.toutiao.R;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is WendaArticleView
 */
public class WendaArticleFragment extends BaseMvpFragment {

    public static WendaArticleFragment newInstance() {
        WendaArticleFragment fragment = new WendaArticleFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wenda_artic;
    }


    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onSetPresenter(Object presenter) {

    }
}
