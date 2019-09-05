package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.SystemContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/5
 * Description: This is ISystemContract
 */
public interface ISystemContract {

    interface View extends IBaseView<Presenter> {

        void onLoadData();

        void onShowContentView(List<SystemContentInfo> data);

    }


    interface Presenter extends IBasePresenter {

        /**
         * 加载体系数据
         */
        void doLoadSystem();

    }

}
