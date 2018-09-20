package com.example.think.ui.newsContent;

import com.example.think.base.IBasePresenter;
import com.example.think.base.IBaseView;
import com.example.think.bean.news.MultiNewsArticleDataBean;

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

    }
}
