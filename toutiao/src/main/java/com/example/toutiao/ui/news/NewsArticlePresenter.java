package com.example.toutiao.ui.news;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.example.base.net.RetrofitFactory;
import com.example.base.utils.ErrorAction;
import com.example.toutiao.api.IMobileNewsApi;
import com.example.toutiao.entity.news.MultiNewsArticleBean;
import com.example.toutiao.entity.news.MultiNewsArticleDataBean;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/8/27
 * Description: This is NewsArticlePresenter
 */
public class NewsArticlePresenter implements IArticleContract.Presenter {

    private Gson mGson = new Gson();

    private String mCategory;

    private String time;

    private IArticleContract.View mView;

    private List<MultiNewsArticleDataBean> mDataList = new ArrayList<>();

    public NewsArticlePresenter(IArticleContract.View view) {
        this.mView = view;
        this.time = String.valueOf(System.currentTimeMillis() / 1000);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(String category) {

        if (mCategory == null) {
            mCategory = category;
        }

        //下拉刷新时，catagory参数不会变，time参数会改变
        Observable<MultiNewsArticleBean> observable = RetrofitFactory.getInstance()
                .create(IMobileNewsApi.class).getNewsArticleOne(category, time);

        observable.subscribeOn(Schedulers.io())
                .switchMap(new Function<MultiNewsArticleBean, ObservableSource<MultiNewsArticleDataBean>>() {
                    @Override
                    public ObservableSource<MultiNewsArticleDataBean> apply(MultiNewsArticleBean multiNewsArticleBean) throws Exception {
                        return null;
                    }
                });

        observable.subscribeOn(Schedulers.io())
                .switchMap((Function<MultiNewsArticleBean, Observable<MultiNewsArticleDataBean>>) multiNewsArticleBean -> {
                    List<MultiNewsArticleDataBean> dataList = new ArrayList<>();
                    for (MultiNewsArticleBean.DataBean dataBean : multiNewsArticleBean.getData()) {
                        dataList.add(mGson.fromJson(dataBean.getContent(), MultiNewsArticleDataBean.class));
                    }
                    return Observable.fromIterable(dataList);
                })
                .filter(dataBean -> {
                    time = dataBean.getBehot_time();
                    if (TextUtils.isEmpty(dataBean.getSource())) {
                        return false;
                    }
                    try {
                        // 过滤头条问答新闻
                        if (dataBean.getSource().contains("头条问答")
                                || dataBean.getTag().contains("ad")
                                || dataBean.getSource().contains("悟空问答")) {
                            return false;
                        }
                        // 过滤头条问答新闻
                        if (dataBean.getRead_count() == 0 || TextUtils.isEmpty(dataBean.getMedia_name())) {
                            String title = dataBean.getTitle();
                            if (title.lastIndexOf("？") == title.length() - 1) {
                                return false;
                            }
                        }
                    } catch (NullPointerException e) {
                        ErrorAction.print(e);
                    }
                    // 过滤重复新闻(与上次刷新的数据比较)
                    for (MultiNewsArticleDataBean bean : mDataList) {
                        if (bean.getTitle().equals(dataBean.getTitle())) {
                            return false;
                        }
                    }
                    return true;
                })
                .toList()
                .map(list -> {
                    // 过滤重复新闻(与本次刷新的数据比较,因为使用了2个请求,数据会有重复)
                    for (int i = 0; i < list.size() - 1; i++) {
                        for (int j = list.size() - 1; j > i; j--) {
                            if (list.get(j).getTitle().equals(list.get(i).getTitle())) {
                                list.remove(j);
                            }
                        }
                    }
                    return list;
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(list -> {
                    if (null != list && list.size() > 0) {
                        doSetAdapter(list);
                    } else {
                        doShowNoMore();
                    }
                }, throwable -> {
                    doShowNetError();
                    ErrorAction.print(throwable);
                });
    }

    @Override
    public void doLoadMoreData() {
        doLoadData(mCategory);
    }

    @Override
    public void doSetAdapter(List<MultiNewsArticleDataBean> list) {
        mDataList.addAll(list);
        mView.onShowContentView(mDataList);
        mView.onHideLoading();
    }

    @Override
    public void doShowNoMore() {
        mView.onHideLoading();
        mView.onShowNoMore();
    }

    @Override
    public void doRefresh() {
        if (mDataList.size() != 0) {
            mDataList.clear();
            time = String.valueOf(System.currentTimeMillis() / 1000);
        }
        mView.onShowLoading();
        doLoadData(mCategory);
    }

    @Override
    public void doShowNetError() {
        mView.onHideLoading();
        mView.onShowNetError();
    }
}
