package com.example.base.base;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/10/19
 * Description: This is RxPresenter
 */
public class RxPresenter<V extends IBaseView> implements IBasePresenter {

    protected V mView;

    /**
     * 处理Rxjava线程切换
     * 使用RxLifeCycle对Rxjava生命周期进行管理
     *
     * @param observable
     * @param <T>
     * @return
     */
    public <T> Observable<T> add(Observable<T> observable) {
        return observable.subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread());
    }

}
