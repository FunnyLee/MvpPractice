package com.example.think.ui.news;

import com.example.think.R;
import com.example.think.base.BaseListFragment;

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
    public void setPresenter(Object presenter) {

    }
}
