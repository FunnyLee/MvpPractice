package com.example.think.ui.picture;

import android.annotation.SuppressLint;

import com.example.think.bean.phote.PhotoArticleBean;
import com.example.think.net.IPhotoApi;
import com.example.think.net.NetCallBack;
import com.example.think.net.RetrofitFactory;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Author: Funny
 * Time: 2018/9/7
 * Description: This is PictureArticleModel
 */
public class PictureArticleModel implements IPictureContract.Model {

    @SuppressLint("CheckResult")
    @Override
    public void loadNetData(String category, String time, NetCallBack<PhotoArticleBean.DataBean> netCallBack) {
        Observable<PhotoArticleBean> observable = RetrofitFactory.getInstance().create(IPhotoApi.class).getPhotoArticle(category, time);
        observable.subscribeOn(Schedulers.io())
                .map(new Function<PhotoArticleBean, List<PhotoArticleBean.DataBean>>() {
                    @Override
                    public List<PhotoArticleBean.DataBean> apply(PhotoArticleBean photoArticleBean) throws Exception {
                        List<PhotoArticleBean.DataBean> data = photoArticleBean.data;
                        //移除最后一个，数据有重复
                        if (data != null && data.size() > 0) {
                            data.remove(data.size() - 1);
                        }
                        return data;
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<List<PhotoArticleBean.DataBean>>() {
                    @Override
                    public void accept(List<PhotoArticleBean.DataBean> dataBeans) throws Exception {
                        netCallBack.success(dataBeans);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        netCallBack.fail(throwable);
                    }
                });
    }
}
