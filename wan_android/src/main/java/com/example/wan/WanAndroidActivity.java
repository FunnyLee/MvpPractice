package com.example.wan;

import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v4.widget.NestedScrollView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpActivity;
import com.example.base.router.RouterManager;
import com.example.base.utils.WindowDispaly;
import com.example.wan.adapter.BannerAdapter;
import com.example.wan.adapter.BannerBgAdapter;
import com.example.wan.adapter.BannerIndicatorAdapter;
import com.example.wan.adapter.HomeArticleAdapter;
import com.example.wan.entity.BannerIndicatorInfo;
import com.example.wan.entity.HomeArticleInfo;
import com.example.wan.entity.HomeBannerInfo;
import com.gyf.immersionbar.ImmersionBar;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadMoreListener;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterManager.WAN_ANDROID_ACTIVITY)
public class WanAndroidActivity extends BaseMvpActivity<IWanAndroidContract.Presenter> implements IWanAndroidContract.View {

    private BannerBgAdapter mBannerBgAdapter;
    private BannerAdapter mBannerAdapter;
    private BannerIndicatorAdapter mIndicatorAdapter;
    private List<HomeBannerInfo> mBannerList = new ArrayList<>();
    private List<HomeArticleInfo.DatasInfo> mDatas = new ArrayList<>();
    private List<BannerIndicatorInfo> mIndicatorList = new ArrayList<>();
    private int mBannerHeight;

    private HomeArticleAdapter mArticleAdapter;
    private NestedScrollView mScrollView;
    private LinearLayout mHeadLl;
    private SmartRefreshLayout mRefreshLayout;


    private int mPageNo = 0;
    private RecyclerView mRecyclerView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_wan_android;
    }

    @Override
    public void onSetPresenter(IWanAndroidContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new WanAndroidPresenter(this);
        }
    }

    @Override
    protected void initStatusBarColor() {
        //设置透明状态栏
        ImmersionBar.with(this).transparentStatusBar().init();
    }

    @Override
    protected void initView() {
        mScrollView = findViewById(R.id.scroll_view);
        mHeadLl = findViewById(R.id.head_ll);

        mBannerHeight = (int) getResources().getDimension(R.dimen.dp_170);
        mHeadLl.getBackground().setAlpha(0);
        mRefreshLayout = findViewById(R.id.refresh_layout);

        RecyclerView bannerBgRecyclerView = findViewById(R.id.banner_bg_recycler_view);
        RecyclerView bannerRecyclerView = findViewById(R.id.banner_recycler_view);
        RecyclerView indicatorRecyclerView = findViewById(R.id.indicator_recycler_view);
        mRecyclerView = findViewById(R.id.recycler_view);

        //背景RecyclerView
        LinearLayoutManager bgLinearLayoutManager = new LinearLayoutManager(this);
        bgLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bannerBgRecyclerView.setLayoutManager(bgLinearLayoutManager);
        bannerBgRecyclerView.setNestedScrollingEnabled(false); //背景禁止滑动
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(bannerBgRecyclerView);
        mBannerBgAdapter = new BannerBgAdapter(R.layout.item_banner_bg_view, mBannerList);
        bannerBgRecyclerView.setAdapter(mBannerBgAdapter);

        //BannerRecyclerView
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bannerRecyclerView.setLayoutManager(linearLayoutManager);
        //设置第一张图片和最后一张图片也居中
        bannerRecyclerView.addItemDecoration(new GalleryItemDecoration());
        mBannerAdapter = new BannerAdapter(R.layout.item_banner_view, mBannerList);
        bannerRecyclerView.setAdapter(mBannerAdapter);
        PagerSnapHelper pagerSnapHelper = new PagerSnapHelper() {
            @Override
            public int findTargetSnapPosition(RecyclerView.LayoutManager layoutManager, int velocityX, int velocityY) {
                //Banner图显示哪个position图片的监听
                int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                //banner背景联动
                bannerBgRecyclerView.scrollToPosition(position);

                //indicator联动
                int realPosition = position % mBannerList.size();
                for (int i = 0; i < mIndicatorList.size(); i++) {
                    BannerIndicatorInfo info = mIndicatorList.get(i);
                    if (i == realPosition) {
                        info.isSelected = true;
                    } else {
                        info.isSelected = false;
                    }
                }
                mIndicatorAdapter.setNewData(mIndicatorList);
                return position;
            }
        };
        pagerSnapHelper.attachToRecyclerView(bannerRecyclerView);

        //指示器RecyclerView
        mIndicatorAdapter = new BannerIndicatorAdapter(R.layout.item_banner_indicator_view, mIndicatorList);
        LinearLayoutManager manager = new LinearLayoutManager(this);
        manager.setOrientation(LinearLayoutManager.HORIZONTAL);
        indicatorRecyclerView.setLayoutManager(manager);
        indicatorRecyclerView.setAdapter(mIndicatorAdapter);

        //文章列表
        mArticleAdapter = new HomeArticleAdapter(R.layout.item_home_article_view, mDatas);
        mRecyclerView.setAdapter(mArticleAdapter);
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    protected void initEvent() {

        //ScrollView滑动监听
        mScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {

                if (scrollY <= mBannerHeight) {
                    float alpha = (float) scrollY / mBannerHeight * 255;
                    mHeadLl.getBackground().setAlpha((int) alpha);
                } else {
                    mHeadLl.getBackground().setAlpha(255);
                }
            }
        });

        mRefreshLayout.setOnRefreshLoadMoreListener(new OnRefreshLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                mPresenter.loadData(mPageNo);
            }

            @Override
            public void onRefresh(@NonNull RefreshLayout refreshLayout) {
                mPageNo = 0;
                mPresenter.loadData(mPageNo);
            }
        });
    }

    @Override
    public void onLoadData() {
        mPresenter.loadData(mPageNo);
        mPresenter.loadBanner();
    }

    @Override
    public void onShowLoading() {

    }

    @Override
    public void onHideLoading() {

    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public void onSetBanner(List<HomeBannerInfo> data) {
        mBannerList.addAll(data);
        mBannerAdapter.setNewData(mBannerList);
        mBannerBgAdapter.setNewData(mBannerList);

        for (int i = 0; i < mBannerList.size(); i++) {
            BannerIndicatorInfo info = new BannerIndicatorInfo();
            if (i == 0) {
                info.isSelected = true;
            } else {
                info.isSelected = false;
            }
            mIndicatorList.add(info);
        }
        mIndicatorAdapter.setNewData(mIndicatorList);
    }

    @Override
    public void onShowContentView(List<HomeArticleInfo.DatasInfo> data) {
        mDatas.addAll(data);
        mArticleAdapter.setNewData(mDatas);
        mPageNo += 1;

        mRefreshLayout.finishLoadMore();
        mRefreshLayout.finishRefresh();
    }

    class GalleryItemDecoration extends RecyclerView.ItemDecoration {

        int mPageMargin = (int) getResources().getDimension(R.dimen.dp_10);//自定义默认item边距
        int mBorderPageVisibleWidth;//第一张图片和最后一张图片的边距

        public GalleryItemDecoration() {
            mBorderPageVisibleWidth = (WindowDispaly.getWidth() - (int) getResources().getDimension(R.dimen.dp_300) - mPageMargin) / 2;
        }

        @Override
        public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
            int positon = parent.getChildAdapterPosition(view); //获得当前item的position
            int itemCount = parent.getAdapter().getItemCount(); //获得item的数量
            int leftMargin;
            if (positon == 0) {
                leftMargin = mBorderPageVisibleWidth;
            } else {
                leftMargin = mPageMargin;
            }
            int rightMargin;
            if (positon == itemCount - 1) {
                rightMargin = mBorderPageVisibleWidth;
            } else {
                rightMargin = mPageMargin;
            }
            RecyclerView.LayoutParams lp = (RecyclerView.LayoutParams) view.getLayoutParams();
            lp.setMargins(leftMargin, 30, rightMargin, 30);
            view.setLayoutParams(lp);
            super.getItemOffsets(outRect, view, parent, state);
        }

    }
}
