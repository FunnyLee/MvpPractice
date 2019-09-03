package com.example.wan.contract;

import com.example.base.base.IBasePresenter;
import com.example.base.base.IBaseView;
import com.example.wan.entity.ProjectCategoryInfo;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/13
 * Description: This is IProjectContract
 */
public interface IProjectContract {

    interface View extends IBaseView<Presenter>{

        void onShowContentView(List<ProjectCategoryInfo> data);

    }

    interface Presenter extends IBasePresenter{

        /**
         * 加载分类数据
         */
        void doLoadProjectCategory();

    }

}
