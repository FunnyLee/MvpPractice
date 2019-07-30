package com.example.wan;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is IWanAndroidContract
 */
public interface IWanAndroidContract {

    interface View extends IBaseView<Presenter> {

        /**
         * 加载数据
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



        void setBanner(List<HomeBannerInfo> data);

    }


    interface Presenter extends IBasePresenter {

        void loadBanner();

        void loadData();
    }

}
