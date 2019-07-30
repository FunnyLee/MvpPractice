package com.example.toutiao.ui.video;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
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

    interface View extends IBaseView<Presenter> {

        /**
         * 请求数据
         */
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
         * 设置适配器
         */
        void onShowContentView(List<?> list);

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

        /**
         * 下拉刷新
         */
        void doRefresh();

        /**
         * 显示网络错误
         */
        void doShowNetError();

    }

    interface Model {

        /**
         * 网络请求
         */
        void loadNetData(LifecycleProvider<ActivityEvent> provider, String category, String time, NetCallBack<MultiNewsArticleDataBean> netCallBack);

    }

}
