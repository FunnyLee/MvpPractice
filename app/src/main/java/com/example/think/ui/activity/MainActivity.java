package com.example.think.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.example.think.R;
import com.example.think.base.ViewActivity;
import com.example.think.ui.channel.ChannelFragment;
import com.example.think.ui.news.NewsFragment;
import com.example.think.ui.picture.PictureFragment;
import com.example.think.ui.video.VideoFragment;

import butterknife.BindView;

public class MainActivity extends ViewActivity {

    @BindView(R.id.toolbar)
    Toolbar mToolbar;
    @BindView(R.id.frame_layout)
    FrameLayout mFrameLayout;
    @BindView(R.id.bottom_navigation)
    BottomNavigationView mBottomNavigation;
    @BindView(R.id.nav_view)
    NavigationView mNavView;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;

    private long firstTime = 0;


    public static void start(Context context) {
        Intent starter = new Intent(context, MainActivity.class);
        context.startActivity(starter);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Toolbar的搜索放大镜
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    protected void initView() {
        //设置Toolbar
        mToolbar.inflateMenu(R.menu.menu_activity_main);
        setSupportActionBar(mToolbar);
        //增加Toolbar的返回键
        //getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //Toolbar和侧滑菜单联动
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        mDrawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        //默认选中新闻
        mToolbar.setTitle(R.string.title_news);
        switchFragment(NewsFragment.newInstance(), R.id.frame_layout);
    }

    @Override
    protected void initEvent() {
        //底部导航栏的监听事件
        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_news:
                    mToolbar.setTitle(R.string.title_news);
                    NewsFragment newsFragment = NewsFragment.newInstance();
                    switchFragment(newsFragment, R.id.frame_layout);
                    break;
                case R.id.action_photo:
                    mToolbar.setTitle(R.string.title_photo);
                    PictureFragment pictureFragment = PictureFragment.newInstance();
                    switchFragment(pictureFragment, R.id.frame_layout);
                    break;
                case R.id.action_video:
                    mToolbar.setTitle(R.string.title_video);
                    VideoFragment videoFragment = VideoFragment.newInstance();
                    switchFragment(videoFragment, R.id.frame_layout);
                    break;
                case R.id.action_media:
                    mToolbar.setTitle(R.string.title_media);
                    ChannelFragment channelFragment = ChannelFragment.newInstance();
                    switchFragment(channelFragment, R.id.frame_layout);
                    break;
                default:
                    break;
            }
            return true;
        });

        //侧滑菜单条目的点击事件
        mNavView.setNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.nav_switch_night_mode:
                    break;
                case R.id.nav_setting:
                    break;
                case R.id.nav_share:
                    //分享
                    Intent shareIntent = new Intent()
                            .setAction(Intent.ACTION_SEND)
                            .setType("text/plain")
                            .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text) + getString(R.string.source_code_url));
                    startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                    mDrawerLayout.closeDrawers();
                    return false;
                default:
                    break;
            }
            return false;
        });
    }

    /**
     * 替换Fragment
     *
     * @param fragment
     */
    private void switchFragment(Fragment fragment, @IdRes int id) {
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(id, fragment);
        transaction.addToBackStack(null);
        transaction.commit();
    }

    @Override
    public void onBackPressed() {
        //双击退出APP
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, R.string.two_press_exit, Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
