package com.example.wan;

import android.graphics.Rect;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.PagerSnapHelper;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseMvpActivity;
import com.example.base.router.RouterManager;
import com.example.base.utils.WindowDispaly;
import com.example.wan.adapter.BannerAdapter;
import com.example.wan.adapter.BannerBgAdapter;
import com.example.wan.adapter.HomeArticleAdapter;
import com.example.wan.entity.HomeArticleInfo;
import com.example.wan.entity.HomeBannerInfo;
import com.gyf.immersionbar.ImmersionBar;

import java.util.ArrayList;
import java.util.List;

@Route(path = RouterManager.WAN_ANDROID_ACTIVITY)
public class WanAndroidActivity extends BaseMvpActivity<IWanAndroidContract.Presenter> implements IWanAndroidContract.View {

    private BannerBgAdapter mBannerBgAdapter;
    private BannerAdapter mBannerAdapter;
    private List<HomeBannerInfo> mBannerList = new ArrayList<>();

    private List<HomeArticleInfo.DatasInfo> mDatas = new ArrayList<>();
    private HomeArticleAdapter mArticleAdapter;

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
        ImmersionBar.with(this).transparentStatusBar().init();
    }

    @Override
    protected void initView() {
        RecyclerView bannerBgRecyclerView = findViewById(R.id.banner_bg_recycler_view);
        RecyclerView bannerRecyclerView = findViewById(R.id.banner_recycler_view);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        //背景RecyclerView
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        LinearLayoutManager bgLinearLayoutManager = new LinearLayoutManager(this);
        bgLinearLayoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        bannerBgRecyclerView.setLayoutManager(bgLinearLayoutManager);
        bannerBgRecyclerView.setNestedScrollingEnabled(false); //背景禁止滑动
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
                int position = super.findTargetSnapPosition(layoutManager, velocityX, velocityY);
                bannerBgRecyclerView.scrollToPosition(position);
                return position;
            }
        };
        pagerSnapHelper.attachToRecyclerView(bannerRecyclerView);

        //文章列表
        mArticleAdapter = new HomeArticleAdapter(R.layout.item_home_article_view, mDatas);
        recyclerView.setAdapter(mArticleAdapter);
    }

    @Override
    protected void initData() {
        onLoadData();
    }

    @Override
    public void onLoadData() {
        mPresenter.loadData();
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
    }

    @Override
    public void onShowContentView( List<HomeArticleInfo.DatasInfo> data) {
        mDatas.addAll(data);
        mArticleAdapter.setNewData(mDatas);
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
