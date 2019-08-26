package com.example.wan.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.IProjectApi;
import com.example.wan.contract.IProjectContentContract;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.ProjectContentInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/8/26
 * Description: This is ProjectContentPresenter
 */
public class ProjectContentPresenter extends RxPresenter<IProjectContentContract.View> implements IProjectContentContract.Presenter {
    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public ProjectContentPresenter(IProjectContentContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadProjectList(int pageNo, String categoryId) {
        Observable<BaseWanAndroidResponse<ProjectContentInfo>> observable = RetrofitFactory.getInstance().create(IProjectApi.class).getProjectContent(pageNo, categoryId);
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<ProjectContentInfo>>() {
            @Override
            public void accept(BaseWanAndroidResponse<ProjectContentInfo> response) throws Exception {
                List<ProjectContentInfo.DatasInfo> datas = response.data.datas;
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
