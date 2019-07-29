package com.example.toutiao.ui.newsContent;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;

/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is INewsContentContract
 */
public interface INewsContentContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 设置网页
         */
        void onSetWeb(String url);

    }

    interface Presenter extends IBasePresenter {

        /**
         * 加载数据
         */
        void doLoadData(MultiNewsArticleDataBean bean);

        void doRefresh();

        void doShowNetError();

    }
}
