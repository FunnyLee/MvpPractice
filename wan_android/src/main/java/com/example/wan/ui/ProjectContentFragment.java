package com.example.wan.ui;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.contract.IProjectContentContract;
import com.example.wan.entity.ProjectContentInfo;
import com.example.wan.presenter.ProjectContentPresenter;

import java.util.List;

/**
 * Author: Funny
 * Time: 2019/8/14
 * Description: This is ProjectContentFragment
 */
@Route(path = RouterManager.PROJECT_CONTENT_FRAGMENT)
public class ProjectContentFragment extends BaseMvpFragment<IProjectContentContract.Presenter> implements IProjectContentContract.View {

    //项目类别Id
    private String mCategoryId;
    //页码
    private int mPageNo = 1;


    @Override
    protected int getLayoutId() {
        return R.layout.fragment_project_content;
    }

    @Override
    public void onSetPresenter(IProjectContentContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new ProjectContentPresenter(this);
        }
    }

    @Override
    protected void initView(View view) {
        RecyclerView recyclerView = findViewById(R.id.recycler_view);
    }

    @Override
    protected void initData() {
        mCategoryId = getArguments().getString("categoryId");

    }


    @Override
    public void onLoadData() {
        mPresenter.doLoadProjectList(mPageNo, mCategoryId);
    }

    @Override
    public void onShowContentView(List<ProjectContentInfo.DatasInfo> data) {

    }

}
