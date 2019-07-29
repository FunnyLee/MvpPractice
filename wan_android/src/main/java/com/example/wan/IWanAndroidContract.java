package com.example.wan;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is IWanAndroidContract
 */
public interface IWanAndroidContract {

    interface View extends IBaseView<Presenter> {

        void setBanner();

    }


    interface Presenter extends IBasePresenter {

        void loadBanner();

        void loadData();
    }

}
