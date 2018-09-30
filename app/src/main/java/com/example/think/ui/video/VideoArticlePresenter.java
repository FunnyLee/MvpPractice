package com.example.think.ui.video;

import android.annotation.SuppressLint;
import android.text.TextUtils;

import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.net.NetCallBack;
import com.example.think.utils.TimeUtil;
import com.trello.rxlifecycle2.LifecycleProvider;
import com.trello.rxlifecycle2.android.ActivityEvent;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Predicate;

/**
 * Author: Funny
 * Time: 2018/9/17
 * Description: This is VideoArticlePresenter
 */
public class VideoArticlePresenter implements IVideoContract.Presenter {

    private VideoArticleModel mModel;
    private VideoArticleFragment mView;
    private String mTime;
    private List<MultiNewsArticleDataBean> mDatas;
    private String mCategory;
    private final LifecycleProvider<ActivityEvent> mProvider;

    public VideoArticlePresenter(VideoArticleFragment view) {
        mView = view;
        mModel = new VideoArticleModel();
        mTime = TimeUtil.getCurrentTimeStamp();
        mDatas = new ArrayList<>();
        mProvider = mView.autoRxLifeCycle();
    }

    @Override
    public void doLoadData(String categoryId) {
        mCategory = categoryId;
        mModel.loadNetData(mProvider,categoryId, mTime, new NetCallBack<MultiNewsArticleDataBean>() {
            @SuppressLint("CheckResult")
            @Override
            public void success(List<MultiNewsArticleDataBean> datas) {
                Observable.fromIterable(datas)
                        .filter(new Predicate<MultiNewsArticleDataBean>() {
                            @Override
                            public boolean test(MultiNewsArticleDataBean multiNewsArticleDataBean) throws Exception {
                                //这个时间参数用于加载更多
                                mTime = multiNewsArticleDataBean.getBehot_time();
                                String source = multiNewsArticleDataBean.getSource();
                                if (TextUtils.isEmpty(source)) {
                                    return false;
                                }

                                if (source.contains("头条") || source.contains("问答") || multiNewsArticleDataBean.getTag().contains("ad")) {
                                    return false;
                                }

                                //去除标题重复的新闻
                                for (MultiNewsArticleDataBean data : mDatas) {
                                    if (data.getTitle().equals(multiNewsArticleDataBean.getTitle())) {
                                        return false;
                                    }
                                }

                                return true;
                            }
                        })
                        .toList()
                        .compose(mProvider.bindToLifecycle())
                        .subscribe(new Consumer<List<MultiNewsArticleDataBean>>() {
                            @Override
                            public void accept(List<MultiNewsArticleDataBean> dataBeans) throws Exception {
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
    public void doSetAdapter(List<MultiNewsArticleDataBean> datas) {
        mDatas.addAll(datas);
        mView.onHideLoading();
        mView.onSetAdapter(mDatas);
    }

    @Override
    public void doLoadMoreData() {
        //加载更多数据和category无关，只和当前时间有关，加载更多时，时间参数要用数据里的behot_time
        doLoadData(mCategory);
    }

    @Override
    public void doShowNoMore() {
        mView.onHideLoading();
        mView.onShowNoMore();
    }

    @Override
    public void doRefresh() {
        mDatas.clear();
        mView.onShowLoading();
        mTime = TimeUtil.getCurrentTimeStamp();
        doLoadData(mCategory);
    }

    @Override
    public void doShowNetError() {
        mView.onHideLoading();
        mView.onShowNetError();
    }
}
