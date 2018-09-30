package com.example.think.ui.newsContent;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.ContentLoadingProgressBar;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ImageView;

import com.example.think.R;
import com.example.think.base.BaseActivity;
import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.utils.ImageHelper;
import com.example.think.widget.AppBarStateChangeListener;

import butterknife.BindView;

/**
 * Author: Funny
 * Time: 2018/9/19
 * Description: This is 新闻内容Activity
 */

public class NewsContentActivity extends BaseActivity<INewsContentContract.Presenter> implements INewsContentContract.View {

    @BindView(R.id.iv_pic)
    ImageView mIvPic;
    @BindView(R.id.tool_bar)
    Toolbar mToolBar;
    @BindView(R.id.appbar_layout)
    AppBarLayout mAppbarLayout;
    @BindView(R.id.web_view)
    WebView mWebView;
    @BindView(R.id.refresh_layout)
    SwipeRefreshLayout mRefreshLayout;
    @BindView(R.id.pb_progress)
    ContentLoadingProgressBar mPbProgress;
    @BindView(R.id.fl_container)
    CoordinatorLayout mFlContainer;
    @BindView(R.id.collapsing_layout)
    CollapsingToolbarLayout mCollapsingLayout;
    private String mPicUrl;
    private MultiNewsArticleDataBean mBean;

    public static void start(Context context, String picUrl, MultiNewsArticleDataBean bean) {
        Intent starter = new Intent(context, NewsContentActivity.class);
        starter.putExtra("picUrl", picUrl);
        starter.putExtra("bean", bean);
        context.startActivity(starter);
    }

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
    protected void initData() {
        mPicUrl = getIntent().getStringExtra("picUrl");
        mBean = getIntent().getParcelableExtra("bean");

        //强行MVP模式
        mPresenter.doLoadData(mBean);
    }

    @Override
    protected int setStatusBarColor() {
        return R.color.transparent;
    }

    @Override
    protected void initView() {
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
        mAppbarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {
            @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {
                int offsetAbs = Math.abs(verticalOffset);
                int totalScrollRange = appBarLayout.getTotalScrollRange();
                mToolBar.setBackgroundColor(changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(offsetAbs * 1.0f) / totalScrollRange));

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    Window window = getWindow();
                    window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                    window.setStatusBarColor(changeAlpha(getResources().getColor(R.color.colorPrimary), Math.abs(offsetAbs * 1.0f) / totalScrollRange));
                }
            }
        });


        mAppbarLayout.addOnOffsetChangedListener(new AppBarStateChangeListener() {
            @Override
            public void onStateChanged(AppBarLayout appBarLayout, State state, int verticalOffset) {
                if (state == State.EXPANDED) {
                    //展开状态


                } else if (state == State.COLLAPSED) {

                    //折叠状态

                } else {

                    //中间状态

                }
            }
        });
    }


    public int changeAlpha(int color, float offset) {
        int alpha = (int) (Color.alpha(color) * offset);
        return Color.argb(alpha, 254, 50, 50);
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
        switch (item.getItemId()) {
            case R.id.action_open_comment:
                break;
            case R.id.action_open_media_home:
                break;
            case R.id.action_share:
                break;
            case android.R.id.home:
                //返回按钮
                finish();
                break;
            default:
                break;
        }
        return true;
    }

}
