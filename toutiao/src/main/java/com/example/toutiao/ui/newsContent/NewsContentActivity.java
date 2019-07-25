package com.example.toutiao.ui.newsContent;

import android.graphics.Bitmap;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseActivity;
import com.example.base.router.RouterManager;
import com.example.base.utils.ImageHelper;
import com.example.toutiao.R;
import com.example.toutiao.bean.news.MultiNewsArticleDataBean;
import com.gyf.immersionbar.ImmersionBar;


/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is 新闻内容Activity
 */

@Route(path = RouterManager.NEWS_CONTENT_ACTIVITY)
public class NewsContentActivity extends BaseActivity<INewsContentContract.Presenter> implements INewsContentContract.View {

    ImageView mIvPic;
    Toolbar mToolBar;
    AppBarLayout mAppbarLayout;
    WebView mWebView;
    SwipeRefreshLayout mRefreshLayout;
    ContentLoadingProgressBar mPbProgress;
    CoordinatorLayout mFlContainer;
    CollapsingToolbarLayout mCollapsingLayout;

    @Autowired(name = "picUrl")
    String mPicUrl;

    @Autowired(name = "bean")
    MultiNewsArticleDataBean mBean;

    @Override
    public void onSetPresenter(INewsContentContract.Presenter presenter) {
        if (mPresenter == null) {
            mPresenter = new NewsContentPresenter(this);
        }
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_news_content;
        //return R.layout.fragment_news_content_img;
    }

    @Override
    protected void initStatusBarColor() {
        ImmersionBar.with(this).titleBar(mToolBar).init();
    }

    @Override
    protected void initData() {
        mPicUrl = getIntent().getStringExtra("picUrl");
        mBean = getIntent().getParcelableExtra("bean");

        //强行MVP模式
        mPresenter.doLoadData(mBean);
    }

    @Override
    protected void initView() {
        mIvPic = findViewById(R.id.iv_pic);
        mToolBar = findViewById(R.id.tool_bar);
        mAppbarLayout = findViewById(R.id.appbar_layout);
        mWebView = findViewById(R.id.web_view);
        mRefreshLayout = findViewById(R.id.refresh_layout);
        mPbProgress = findViewById(R.id.pb_progress);
        mFlContainer = findViewById(R.id.fl_container);
        mCollapsingLayout = findViewById(R.id.collapsing_layout);


        //设置ToolBar
        setSupportActionBar(mToolBar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);

        mCollapsingLayout.setTitle(mBean.getMedia_name());

        ImageHelper.loadCenterCrop(this, mPicUrl, mIvPic);
    }

    @Override
    protected void initEvent() {
        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, int state, int verticalOffset) {
                if (state == AppBarStateChangeListener.EXPANDED) {
                    //展开状态
                    ImmersionBar.with(NewsContentActivity.this).statusBarColor(R.color.transparent).init();
                } else if (state == AppBarStateChangeListener.COLLAPSED) {
                    //折叠状态
                    ImmersionBar.with(NewsContentActivity.this).statusBarColor(R.color.theme_color).init();
                } else {
                    //中间状态
                    ImmersionBar.with(NewsContentActivity.this).statusBarColor(R.color.transparent).init();
                }
            }
        });
    }

    @Override
    public void onSetWeb(String url) {
        //允许使用js
        WebSettings webSettings = mWebView.getSettings();
        webSettings.setJavaScriptEnabled(true);

        /**
         * LOAD_CACHE_ONLY: 不使用网络，只读取本地缓存数据
         * LOAD_DEFAULT: （默认）根据cache-control决定是否从网络上取数据。
         * LOAD_NO_CACHE: 不使用缓存，只从网络获取数据.
         * LOAD_CACHE_ELSE_NETWORK，只要本地有，无论是否过期，或者no-cache，都使用缓存中的数据。
         */
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE);//不使用缓存，只从网络获取数据.

        //支持屏幕缩放
        webSettings.setSupportZoom(true);
        webSettings.setBuiltInZoomControls(true);

        mWebView.setWebChromeClient(new WebChromeClient());

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageStarted(WebView view, String url, Bitmap favicon) {
                onShowLoading();
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                onHideLoading();
            }
        });

        //加载url
        mWebView.loadDataWithBaseURL(null, url, "text/html", "utf-8", null);

    }

    @Override
    public void onShowLoading() {
        mPbProgress.show();
    }

    @Override
    public void onHideLoading() {
        mPbProgress.hide();
        mRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onShowNetError() {

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //加载标题栏的菜单目录
        getMenuInflater().inflate(R.menu.menu_activity_content, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toolbar上面按钮的点击事件
        int i = item.getItemId();
        if (i == R.id.action_open_comment) {

        } else if (i == R.id.action_open_media_home) {

        } else if (i == R.id.action_share) {

        } else if (i == android.R.id.home) {//返回按钮
            finish();
        }
        return true;
    }

    abstract class AppBarStateChangeListener implements AppBarLayout.OnOffsetChangedListener {

        public static final int EXPANDED = 1;
        public static final int COLLAPSED = 2;
        public static final int IDLE = 3;

        private int currentStata = IDLE;

        @Override
        public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
            int offsetAbs = Math.abs(i);
            int totalScrollRange = appBarLayout.getTotalScrollRange();
            if (i == 0) {
                if (currentStata != EXPANDED) {
                    onStateChanged(appBarLayout, EXPANDED, i);
                }
                currentStata = EXPANDED;
            } else if (totalScrollRange - offsetAbs <= 2 * ImmersionBar.getStatusBarHeight(NewsContentActivity.this)) {
                //自定义的折叠状态，多出了一个状态栏的高度，目的是能无缝修改状态栏的颜色
                if (currentStata != COLLAPSED) {
                    onStateChanged(appBarLayout, COLLAPSED, i);
                }
                currentStata = COLLAPSED;
            } else {
                if (currentStata != IDLE) {
                    onStateChanged(appBarLayout, IDLE, i);
                }
                currentStata = IDLE;
            }
        }

        public abstract void onStateChanged(AppBarLayout appBarLayout, int state, int verticalOffset);
    }
}
