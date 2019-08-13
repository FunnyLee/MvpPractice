package com.example.wan.presenter;

import android.annotation.SuppressLint;

import com.example.base.base.RxPresenter;
import com.example.base.net.RetrofitFactory;
import com.example.wan.api.IProjectApi;
import com.example.wan.contract.IProjectContract;
import com.example.wan.entity.BaseWanAndroidResponse;
import com.example.wan.entity.ProjectCategoryInfo;

import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Consumer;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is ProjectPresenter
 */
public class ProjectPresenter extends RxPresenter<IProjectContract.View> implements IProjectContract.Presenter {

    /**
     * Presenter中持有View对象
     *
     * @param view
     */
    public ProjectPresenter(IProjectContract.View view) {
        super(view);
    }

    @SuppressLint("CheckResult")
    @Override
    public void doLoadProjectCategory() {
        Observable<BaseWanAndroidResponse<List<ProjectCategoryInfo>>> observable = RetrofitFactory.getInstance().create(IProjectApi.class).getProjectCategory();
        add(observable).subscribe(new Consumer<BaseWanAndroidResponse<List<ProjectCategoryInfo>>>() {
            @Override
            public void accept(BaseWanAndroidResponse<List<ProjectCategoryInfo>> response) throws Exception {
                List<ProjectCategoryInfo> data = response.data;
            }
        }, new Consumer<Throwable>() {
            @Override
            public void accept(Throwable throwable) throws Exception {

            }
        });

    }
}
