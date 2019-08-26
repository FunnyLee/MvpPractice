package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.ProjectContentInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/26
 * Description: This is ProjectContentContract
 */
public interface IProjectContentContract {

    interface View extends IBaseView<Presenter> {

        void onLoadData();

        void onShowContentView(List<ProjectContentInfo.DatasInfo> data);

    }

    interface Presenter extends IBasePresenter {

        void doLoadProjectList(int pageNo, String categoryId);

    }

}
