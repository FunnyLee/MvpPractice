package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is IProjectContract
 */
public interface IProjectContract {

    interface View extends IBaseView<Presenter>{}

    interface Presenter extends IBasePresenter{

        void doLoadProjectCategory();

    }

}
