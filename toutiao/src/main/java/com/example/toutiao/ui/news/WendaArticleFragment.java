package com.example.toutiao.ui.news;

import com.example.base.base.BaseListFragment;
import com.example.toutiao.R;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/16
 * Description: This is WendaArticleView
 */
public class WendaArticleFragment extends BaseListFragment {

    public static WendaArticleFragment newInstance() {
        WendaArticleFragment fragment = new WendaArticleFragment();
        return fragment;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wenda_artic;
    }

    @Override
    public void onSetAdapter(List list) {

    }

    @Override
    public void onSetPresenter(Object presenter) {

    }

    @Override
    public void fetchData() {

    }
}
