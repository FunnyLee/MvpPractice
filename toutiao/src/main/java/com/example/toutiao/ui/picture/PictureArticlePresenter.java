package com.example.toutiao.ui.picture;

import android.annotation.SuppressLint;

import com.example.base.net.NetCallBack;
import com.example.base.utils.TimeUtil;
import com.example.toutiao.bean.phote.PhotoArticleBean;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author: Funny
 * Time: 2018/9/7
 * Description: This is PictureArticlePresenter
 */
public class PictureArticlePresenter implements IPictureContract.Presenter {

    private PictureArticleFragment mFragment;

    private PictureArticleModel mModel;

    private List<PhotoArticleBean.DataBean> mDatas;

    private String mTime;

    private String mCategory;

    public PictureArticlePresenter(PictureArticleFragment fragment) {
        mFragment = fragment;
        mModel = new PictureArticleModel();
        mDatas = new ArrayList<>();
        mTime = TimeUtil.getCurrentTimeStamp();
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadData(String category) {
        mCategory = category;
        mModel.loadNetData(category, mTime, new NetCallBack<PhotoArticleBean.DataBean>() {
            @Override
            public void success(List<PhotoArticleBean.DataBean> datas) {
                //过滤标题相同的重复数据
                Observable.fromIterable(datas)
                        .filter(new Predicate<PhotoArticleBean.DataBean>() {
                            @Override
                            public boolean test(PhotoArticleBean.DataBean dataBean) throws Exception {
                                //这里给时间赋值，为为了实现加载更多
                                mTime = dataBean.behot_time + "";
                                for (PhotoArticleBean.DataBean data : mDatas) {
                                    if (dataBean.title.equals(data.title)) {
                                        return false;
                                    }
                                }
                                return true;
                            }
                        }).toList()
                        .subscribe(new Consumer<List<PhotoArticleBean.DataBean>>() {
                            @Override
                            public void accept(List<PhotoArticleBean.DataBean> dataBeans) throws Exception {
                                if (dataBeans != null && dataBeans.size() > 0) {
                                    doSetAdapter(dataBeans);
                                } else {
                                    doShowNoMore();
                                }
                            }
                        });
            }

            @Override
            public void fail(Throwable throwable) {
                doShowNetError();
            }
        });
    }

    @Override
    public void doLoadMoreData() {
        //加载更多数据和category无关，只和当前时间有关，加载更多时，时间参数要用数据里的behot_time
        doLoadData(mCategory);
    }

    @Override
    public void doSetAdapter(List<PhotoArticleBean.DataBean> datas) {
        mFragment.onHideLoading();
        mDatas.addAll(datas);
        mFragment.onSetAdapter(mDatas);
    }

    @Override
    public void doRefresh() {
        mDatas.clear();
        mFragment.onShowLoading();
        mTime = TimeUtil.getCurrentTimeStamp();
        doLoadData(mCategory);
    }

    @Override
    public void doShowNoMore() {
        mFragment.onHideLoading();
        mFragment.onShowNoMore();
    }

    @Override
    public void doShowNetError() {
        mFragment.onHideLoading();
        mFragment.onShowNetError();
    }
}
