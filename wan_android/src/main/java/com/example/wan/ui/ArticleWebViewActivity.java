package com.example.wan.ui;

import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.widget.LinearLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.BaseActivity;
import com.example.base.router.RouterManager;
import com.example.wan.R;
import com.just.agentweb.AgentWeb;

/**
 * ArticleWebViewActivity
 */
@Route(path = RouterManager.ARTICLE_WEBVIEW_ACTIVITY)
public class ArticleWebViewActivity extends BaseActivity {

    private Toolbar mToolbar;
    private AgentWeb mAgentWeb;
    private WebView mWebView;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_article_web_view;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.tool_bar);
        LinearLayout linearLayout = findViewById(R.id.linear_layout);

        String link = getIntent().getStringExtra("link");
        String title = getIntent().getStringExtra("title");

        mToolbar.setTitle(title);

        //设置ToolBar
        setSupportActionBar(mToolbar);
        //ToolBar左边增加返回箭头
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        //返回箭头可用
        getSupportActionBar().setHomeButtonEnabled(true);

        mAgentWeb = AgentWeb.with(this)
                .setAgentWebParent(linearLayout, new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT))
                .useDefaultIndicator()
                .createAgentWeb()
                .ready()
                .go(link);

        mWebView = mAgentWeb.getWebCreator().getWebView();
        mWebView.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_DOWN) {
                    if (keyCode == KeyEvent.KEYCODE_BACK) {
                        if (mWebView.canGoBack()) {
                            mWebView.goBack();
                            return true;
                        } else {
                            finish();
                        }
                    }
                }
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //Toolbar上面按钮的点击事件
        int i = item.getItemId();
        if (i == android.R.id.home) {//返回按钮
            if (mWebView.canGoBack()) {
                mWebView.goBack();
            }else {
                finish();
            }
        }
        return true;
    }
}
