package com.example.wan.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.ISystemApi;
import com.example.wan.contract.INavigationContract;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.NavigationContentInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/9/6
 * Description: This is NavigationPresenter
 */
public class NavigationPresenter extends RxPresenter<INavigationContract.View> implements INavigationContract.Presenter {

    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public NavigationPresenter(INavigationContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadNavigation() {
        Observable<BaseWanAndroidResponse<List<NavigationContentInfo>>> observable = RetrofitFactory.getInstance().create(ISystemApi.class).getNavigationContent();
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<List<NavigationContentInfo>>>() {
            @Override
            public void accept(BaseWanAndroidResponse<List<NavigationContentInfo>> response) throws Exception {
                List<NavigationContentInfo> data = response.data;
                if (data != null && data.size() > 0) {
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
