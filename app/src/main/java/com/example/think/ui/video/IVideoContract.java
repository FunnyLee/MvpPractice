package com.example.think.ui.video;

import com.example.think.base.IBaseListView;
import com.example.think.base.IBasePresenter;
import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.net.NetCallBack;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is IVideoContract
 */
public interface IVideoContract {

    interface View extends IBaseListView<Presenter> {

        /**
         * 请求数据
         */
        void onLoadData();

    }

    interface Presenter extends IBasePresenter {

        /**
         * 请求数据
         */
        void doLoadData(String categoryId);

        /**
         * 设置适配器
         */
        void doSetAdapter(List<MultiNewsArticleDataBean> datas);

        /**
         * 加载更多
         */
        void doLoadMoreData();

        /**
         * 加载完成
         */
        void doShowNoMore();

    }

    interface Model {

        /**
         * 网络请求
         */
        void loadNetData(String category, String time, NetCallBack<MultiNewsArticleDataBean> netCallBack);

    }

}
