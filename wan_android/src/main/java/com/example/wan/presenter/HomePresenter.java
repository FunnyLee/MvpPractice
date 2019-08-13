package com.example.wan.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.IHomePageApi;
import com.example.wan.contract.IHomeContract;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.HomeArticleInfo;
import com.example.wan.entity.HomeBannerInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is WanAndroidMainPresenter
 */
public class HomePresenter extends RxPresenter<IHomeContract.View> implements IHomeContract.Presenter {
    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public HomePresenter(IHomeContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadBanner() {
        Observable<BaseWanAndroidResponse<List<HomeBannerInfo>>> observable = RetrofitFactory.getInstance().create(IHomePageApi.class).getHomeBanner();
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<List<HomeBannerInfo>>>() {
            @Override
            public void accept(BaseWanAndroidResponse<List<HomeBannerInfo>> response) throws Exception {
                List<HomeBannerInfo> data = response.data;
                if (data != null && data.size() > 0) {
                    mView.onSetBanner(data);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(int pageNo) {
        Observable<BaseWanAndroidResponse<HomeArticleInfo>> observable = RetrofitFactory.getInstance().create(IHomePageApi.class).getHomeArticle(pageNo);
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<HomeArticleInfo>>() {
            @Override
            public void accept(BaseWanAndroidResponse<HomeArticleInfo> response) throws Exception {
                List<HomeArticleInfo.DatasInfo> datas = response.data.datas;
                if (datas != null && datas.size() > 0) {
                    mView.onShowContentView(datas);
                }
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });
    }

}
