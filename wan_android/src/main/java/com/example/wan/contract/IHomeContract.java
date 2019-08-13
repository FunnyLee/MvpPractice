package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.HomeArticleInfo;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is HomeContract
 */
public interface IHomeContract {

    interface View extends IBaseView<Presenter>{

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


        /**
         * 设置轮播图
         *
         * @param data
         */
        void onSetBanner(List<HomeBannerInfo> data);

        /**
         * 显示内容视图
         */
        void onShowContentView( List<HomeArticleInfo.DatasInfo> data);

    }

    interface Presenter extends IBasePresenter{

        void doLoadBanner();

        void doLoadData(int pageNo);

    }

}
