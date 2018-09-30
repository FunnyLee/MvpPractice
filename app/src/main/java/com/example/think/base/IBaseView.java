package com.example.think.base;

import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

/**
 * Author: Funny
 * Time: 2018/8/17
 * Description: This is 一级View接口
 */
public interface IBaseView<P> {
    /**
     * 显示加载动画
     */
    void onShowLoading();

    /**
     * 隐藏加载
     */
    void onHideLoading();

    /**
     * 显示网络错误
     */
    void onShowNetError();

    /**
     * 设置 presenter
     */
    void onSetPresenter(P presenter);

    /**
     * 绑定生命周期
     */
    LifecycleProvider<ActivityEvent> autoRxLifeCycle();
}
