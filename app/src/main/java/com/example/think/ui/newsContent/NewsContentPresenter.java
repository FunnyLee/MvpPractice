package com.example.think.ui.newsContent;

import com.example.think.bean.news.MultiNewsArticleDataBean;

/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is NewsContentPresenter
 */
public class NewsContentPresenter implements INewsContentContract.Presenter {

    private NewsContentActivity mView;

    public NewsContentPresenter(NewsContentActivity view) {
        mView = view;
    }

    @Override
    public void doLoadData(MultiNewsArticleDataBean bean) {
        mView.onSetWeb(bean.getDisplay_url());
    }

    @Override
    public void doRefresh() {

    }

    @Override
    public void doShowNetError() {

    }
}
