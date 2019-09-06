package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.NavigationContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/6
 * Description: This is INavigationContract
 */
public interface INavigationContract {

    interface View extends IBaseView<Presenter> {

        void onLoadData();

        void onShowContentView( List<NavigationContentInfo> data);

    }


    interface Presenter extends IBasePresenter {

        void doLoadNavigation();

    }


}
