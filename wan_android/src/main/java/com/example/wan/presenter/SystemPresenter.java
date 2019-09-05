package com.example.wan.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.ISystemApi;
import com.example.wan.contract.ISystemContract;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.SystemContentInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/9/5
 * Description: This is SystemPresenter
 */
public class SystemPresenter extends RxPresenter<ISystemContract.View> implements ISystemContract.Presenter {

    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public SystemPresenter(ISystemContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadSystem() {
        Observable<BaseWanAndroidResponse<List<SystemContentInfo>>> observable = RetrofitFactory.getInstance().create(ISystemApi.class).getSystemContent();
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<List<SystemContentInfo>>>() {
            @Override
            public void accept(BaseWanAndroidResponse<List<SystemContentInfo>> response) throws Exception {
                List<SystemContentInfo> data = response.data;
                if(data != null && data.size() > 0){
                    mView.onShowContentView(data);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }
}
