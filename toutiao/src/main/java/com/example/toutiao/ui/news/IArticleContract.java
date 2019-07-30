package com.example.toutiao.ui.news;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/27
 * Description: This is INewsArticle
 */
public interface IArticleContract {

    interface View extends IBaseView<Presenter> {

        void onLoadData();

        /**
         * 显示加载动画
         */
        void onShowLoading();

        /**
         * 隐藏加载动画
         */
        void onHideLoading();

        /**
         * 显示网络错误
         */
        void onShowNetError();

        /**
         * 加载完毕
         */
        void onShowNoMore();


        /**
         * 设置 presenter
         */
        void onSetPresenter(Presenter presenter);

        /**
         * 显示内容视图
         */
        void onShowContentView(List<?> list);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String category);

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

        void doRefresh();

        void doShowNetError();

    }
}
