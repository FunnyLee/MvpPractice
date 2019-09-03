package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is ISystemContract
 */
public interface ISystemContract {

    interface View extends IBaseView<Presenter>{

    }

    interface Presenter extends IBasePresenter{

    }
}
