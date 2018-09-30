package com.example.think.base;

/**
 * Author: Funny
 * Time: 2018/8/14
 * Description: This is 一级Presenter接口
 */
public interface IBasePresenter {

    /**
     * 刷新数据
     */
    void doRefresh();

    /**
     * 显示网络错误
     */
    void doShowNetError();


}
