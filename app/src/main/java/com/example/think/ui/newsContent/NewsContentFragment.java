package com.example.think.ui.newsContent;

import com.example.think.R;
import com.example.think.base.BaseFragment;

/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is 新闻内容Fragment
 */
public class NewsContentFragment extends BaseFragment<INewsContentContract.Presenter> implements INewsContentContract.View {

    @Override
    public void setPresenter(INewsContentContract.Presenter presenter) {

    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_news_content;
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
    public void onSetWeb(String url) {

    }

}
