package com.example.base.base;

import android.os.Bundle;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;
import com.trello.rxlifecycle2.navi.NaviLifecycle;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is BaseMvpFragment
 */
public abstract class BaseMvpFragment<P extends IBasePresenter> extends BaseFragment implements IBaseView<P> {

    protected P mPresenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if(mPresenter == null){
            onSetPresenter(mPresenter);
        }
    }

    public LifecycleProvider<ActivityEvent> autoRxLifeCycle() {
        //返回Rxlifecycler的provider对象
        return NaviLifecycle.createActivityLifecycleProvider(this);
    }

}
