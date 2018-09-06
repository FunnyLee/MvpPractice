package com.example.think.ui.picture;

import com.example.think.base.IBaseListView;
import com.example.think.base.IBasePresenter;

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
        void loadData();

        /**
         * 加载更多
         */
        void loadMoreData();

        /**
         * 设置适配器
         */
        void setAdapter();

        /**
         * 加载完成
         */
        void onShowNoMore();
    }

}
