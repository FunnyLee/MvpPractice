package com.example.think.ui.news;

import com.example.think.base.IBaseListView;
import com.example.think.base.IBasePresenter;
import com.example.think.bean.news.MultiNewsArticleDataBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/27
 * Description: This is INewsArticle
 */
public interface IArticleContract {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

        /**
         * 刷新
         */
        void onRefresh();
    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String... category);

        /**
         * 再起请求数据
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> dataBeen);

        /**
         * 加载完毕
         */
        void doShowNoMore();

    }
}
