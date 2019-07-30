package com.example.wan;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.IHomePageApi;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/7/29
 * Description: This is WanAndroidPresenter
 */
public class WanAndroidPresenter extends RxPresenter<IWanAndroidContract.View> implements IWanAndroidContract.Presenter {

    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public WanAndroidPresenter(IWanAndroidContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void loadBanner() {
        Observable<BaseWanAndroidResponse<List<HomeBannerInfo>>> observable = RetrofitFactory.getInstance().create(IHomePageApi.class).getBanner();
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<List<HomeBannerInfo>>>() {
            @Override
            public void accept(BaseWanAndroidResponse<List<HomeBannerInfo>> response) throws Exception {
                List<HomeBannerInfo> data = response.data;
                if (data != null && data.size() > 0) {
                    mView.setBanner(data);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    @Override
    public void loadData() {

    }

}
