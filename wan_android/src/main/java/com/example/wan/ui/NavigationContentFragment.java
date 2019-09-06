package com.example.wan.ui;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.example.base.base.BaseMvpFragment;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.example.wan.adapter.NavigationLeftAdapter;
import com.example.wan.adapter.NavigationRightAdapter;
import com.example.wan.contract.INavigationContract;
import com.example.wan.entity.NavigationContentInfo;
import com.example.wan.presenter.NavigationPresenter;

import java.util.ArrayList;
import java.util.List;

/**
 * Author: Funny
 * Time: 2019/9/3
 * Description: This is NavigationContentFragment
 */
@Route(path = RouterManager.NAVIGATION_CONTENT_RAGMENT)
public class NavigationContentFragment extends BaseMvpFragment<INavigationContract.Presenter> implements INavigationContract.View {

    private List<NavigationContentInfo> mDatas = new ArrayList<>();
    private NavigationLeftAdapter mLeftAdapter;
    private NavigationRightAdapter mRightAdapter;
    private LinearLayoutManager mRightManager;
    private RecyclerView mRightRecyclerView;
    private LinearLayoutManager mLeftManager;
    private RecyclerView mLeftRecyclerView;

    @Override
    public void onSetPresenter(INavigationContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new NavigationPresenter(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_navigation_content;
    }

    @Override
    protected void initView(View view) {
        mLeftRecyclerView = findViewById(R.id.left_recycler_view);
        mRightRecyclerView = findViewById(R.id.right_recycler_view);

        mLeftAdapter = new NavigationLeftAdapter(mDatas);
        mLeftManager = new LinearLayoutManager(mContext);
        mLeftRecyclerView.setLayoutManager(mLeftManager);
        mLeftRecyclerView.setAdapter(mLeftAdapter);

        mRightAdapter = new NavigationRightAdapter(mDatas);
        mRightManager = new LinearLayoutManager(mContext);
        mRightRecyclerView.setLayoutManager(mRightManager);
        mRightRecyclerView.setAdapter(mRightAdapter);
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    protected void initEvent() {
        mLeftAdapter.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                moveToPosition(mRightManager, mRightRecyclerView, position);
            }
        });

        mRightRecyclerView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                int firstItem = mRightManager.findFirstVisibleItemPosition();
                moveToPosition(mLeftManager, mLeftRecyclerView, firstItem);

                //右边滑动时，刷新左边的选中状态
                for (int i = 0; i < mDatas.size(); i++) {
                    NavigationContentInfo navigationContentInfo = mDatas.get(i);
                    if(firstItem == i){
                        navigationContentInfo.isSelected = true;
                    }else {
                        navigationContentInfo.isSelected = false;
                    }
                }
                mLeftAdapter.setNewData(mDatas);
            }
        });
    }

    @Override
    public void onLoadData() {
        mPresenter.doLoadNavigation();
    }

    @Override
    public void onShowContentView(List<NavigationContentInfo> data) {
        mDatas.addAll(data);
        data.get(0).isSelected = true;
        mLeftAdapter.setNewData(mDatas);

        mRightAdapter.setNewData(mDatas);
    }

    /**
     * RecyclerView移动到某一条目位置
     *
     * @param manager
     * @param mRecyclerView
     * @param n
     */
    private void moveToPosition(LinearLayoutManager manager, RecyclerView mRecyclerView, int n) {
        int firstItem = manager.findFirstVisibleItemPosition();
        int lastItem = manager.findLastVisibleItemPosition();
        if (n <= firstItem) {
            mRecyclerView.scrollToPosition(n);
        } else if (n <= lastItem) {
            int top = mRecyclerView.getChildAt(n - firstItem).getTop();
            mRecyclerView.scrollBy(0, top);
        } else {
            mRecyclerView.scrollToPosition(n);
        }
    }

}
