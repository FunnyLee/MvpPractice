package com.example.base.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is 封装一些RxJava的通用方法
 * 这个类暂时只使用在WanAndroid module中
 */
public class RxPresenter<V extends IBaseView> {

    protected V mView;

    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public RxPresenter(V view) {
        mView = view;
    }

    /**
     * 处理Rxjava线程切换
     * 使用RxLifeCycle对Rxjava生命周期进行管理
     *
     * @param <T>
     * @param observable
     * @return
     */
    public <T> Observable<T> add(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(mView.autoRxLifeCycle().bindToLifecycle());
    }

}
