package com.example.think.ui.picture;

import com.example.base.base.IBaseListView;
import com.example.base.base.IBasePresenter;
import com.example.think.bean.phote.PhotoArticleBean;
import com.example.think.net.NetCallBack;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/9/6
 * Description: This is INewsPictureContract
 */
public interface IPictureContract {

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
        void doLoadData(String category);

        /**
         * 加载更多
         */
        void doLoadMoreData();

        /**
         * 设置适配器
         */
        void doSetAdapter(List<PhotoArticleBean.DataBean> datas);

        /**
         * 加载完成
         */
        void doShowNoMore();
    }

    interface Model {
        /**
         * 网络请求
         */
        void loadNetData(String category, String time, NetCallBack<PhotoArticleBean.DataBean> netCallBack);
    }

}
