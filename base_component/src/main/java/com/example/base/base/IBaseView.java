package com.example.base.base;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Author: Funny
 * Time: 2018/8/17
 * Description: This is 一级View接口
 */
public interface IBaseView<P> {
    /**
     * 设置 presenter
     */
    void onSetPresenter(P presenter);

    /**
     * 绑定生命周期
     */
    LifecycleProvider<ActivityEvent> autoRxLifeCycle();
}
