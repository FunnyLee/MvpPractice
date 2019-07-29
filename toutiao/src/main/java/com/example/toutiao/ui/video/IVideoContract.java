package com.example.toutiao.ui.video;

import com.example.base.base.IBaseListView;
import com.example.base.base.IBasePresenter;
import com.example.base.net.NetCallBack;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

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

        void doRefresh();

        void doShowNetError();

    }

    interface Model {

        /**
         * 网络请求
         */
        void loadNetData(LifecycleProvider<ActivityEvent> provider, String category, String time, NetCallBack<MultiNewsArticleDataBean> netCallBack);

    }

}
