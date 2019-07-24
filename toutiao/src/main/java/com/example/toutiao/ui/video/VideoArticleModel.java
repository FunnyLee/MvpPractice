package com.example.toutiao.ui.video;

import android.annotation.SuppressLint;
import com.example.base.net.NetCallBack;
import com.example.base.net.RetrofitFactory;
import com.example.base.net.NetCallBack;
import com.example.base.net.RetrofitFactory;
import com.example.toutiao.api.IMobileVideoApi;
import com.example.toutiao.bean.news.MultiNewsArticleBean;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.google.gson.Gson;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is VideoArticleModel网络请求
 */
public class VideoArticleModel implements IVideoContract.Model {

    @SuppressLint("CheckResult")
    @Override
    public void loadNetData(LifecycleProvider<ActivityEvent> provider, String category, String time, NetCallBack<MultiNewsArticleDataBean> netCallBack) {
        Observable<MultiNewsArticleBean> observable = RetrofitFactory.getInstance().create(IMobileVideoApi.class).getVideoArticle(category, time);
        observable.subscribeOn(Schedulers.io())
                .map(new Function<MultiNewsArticleBean, List<MultiNewsArticleDataBean>>() {
                    @Override
                    public List<MultiNewsArticleDataBean> apply(MultiNewsArticleBean multiNewsArticleBean) throws Exception {
                        List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
                        List<MultiNewsArticleBean.DataBean> data = multiNewsArticleBean.getData();
                        for (MultiNewsArticleBean.DataBean datum : data) {
                            String content = datum.getContent();
                            dataList.add(new Gson().fromJson(content, MultiNewsArticleDataBean.class));
                        }
                        return dataList;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .compose(provider.bindToLifecycle())
                .subscribe(new Consumer<List<MultiNewsArticleDataBean>>() {
                    @Override
                    public void accept(List<MultiNewsArticleDataBean> multiNewsArticleDataBeans) throws Exception {
                        netCallBack.success(multiNewsArticleDataBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallBack.fail(throwable);
                    }
                });


    }
}
