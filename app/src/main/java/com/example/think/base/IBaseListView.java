package com.example.think.base;

import java.util.List;

/**
 * Author: Funny
 * Time: 2018/8/27
 * Description: This is IBaseListView，列表Fragment中该有的行为
 */
public interface IBaseListView<T> extends IBaseView<T> {

    ///////////////////////////////////////////////////////////////////////////
    // 这四个方法在BaseListFragment中处理，因为所有的加载动作都是一样的
    ///////////////////////////////////////////////////////////////////////////
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
     * 加载完毕
     */
    void onShowNoMore();


    ///////////////////////////////////////////////////////////////////////////
    // 这两个方法交给BaseListFragment的子类去实现，因为每个子类设置的presenter和adapter是不同的
    ///////////////////////////////////////////////////////////////////////////
    /**
     * 设置 presenter
     */
    void onSetPresenter(T presenter);

    /**
     * 设置适配器
     */
    void onSetAdapter(List<?> list);

}
