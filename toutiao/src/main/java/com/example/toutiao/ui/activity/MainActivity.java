package com.example.toutiao.ui.activity;

import android.content.Intent;
import android.support.annotation.IdRes;
import android.support.design.internal.NavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.example.base.base.ViewActivity;
import com.example.base.greendao.daoManager.NewsDao;
import com.example.base.greendao.daoManager.PictureDao;
import com.example.base.greendao.daoManager.VideoDao;
import com.example.base.greendao.entity.NewsChannel;
import com.example.base.greendao.entity.PictureChannel;
import com.example.base.greendao.entity.VideoChannel;
import com.example.base.router.RouterManager;
import com.example.base.utils.ImageHelper;
import com.example.toutiao.R;
import com.example.toutiao.ui.channel.ChannelFragment;
import com.example.toutiao.ui.news.NewsFragment;
import com.example.toutiao.ui.picture.PictureFragment;
import com.example.toutiao.ui.video.VideoFragment;
import com.gyf.immersionbar.ImmersionBar;

import java.util.List;
import java.util.Random;

@Route(path = RouterManager.MAIN_ACTIVTY_URL)
public class MainActivity extends ViewActivity {

    Toolbar mToolbar;
    FrameLayout mFrameLayout;
    BottomNavigationView mBottomNavigation;
    NavigationView mNavView;
    DrawerLayout mDrawerLayout;
    View mStatusBarView;

    private View mMenuHeadView;

    private long firstTime = 0;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initStatusBarColor() {
        ImmersionBar.with(this).statusBarView(mStatusBarView).init();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        //Toolbar的搜索放大镜
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
        return true;
    }

    @Override
    protected void initView() {
        mToolbar = findViewById(R.id.toolbar);
        mFrameLayout = findViewById(R.id.frame_layout);
        mBottomNavigation = findViewById(R.id.bottom_navigation);
        mNavView = findViewById(R.id.nav_view);
        mDrawerLayout = findViewById(R.id.drawer_layout);
        mStatusBarView = findViewById(R.id.status_view);

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

        //去掉侧滑菜单的滑动条
        NavigationMenuView navigationMenuItemView = (NavigationMenuView) mNavView.getChildAt(0);
        if (navigationMenuItemView != null) {
            navigationMenuItemView.setVerticalScrollBarEnabled(false);
        }

        //侧滑菜单背景图片
        mMenuHeadView = mNavView.getHeaderView(0);
        ImageHelper.loadBgImage(MainActivity.this, getBgPic(), mMenuHeadView);

        //默认选中新闻
        mToolbar.setTitle(R.string.title_news);
        switchFragment(NewsFragment.newInstance(), R.id.frame_layout);
    }

    @Override
    protected void initData() {
        /**
         * 新闻channel
         */
        String[] channelNames = getResources().getStringArray(R.array.mobile_news_name);
        String[] channelIds = getResources().getStringArray(R.array.mobile_news_id);
        //如果查询得到的集合长度为0
        List<NewsChannel> entities = NewsDao.queryAll();
        if (entities.size() == 0) {
            for (int i = 0; i < channelNames.length; i++) {
                NewsChannel entity;
                if (i <= 5) {
                    //初始时，前6个为显示的频道
                    entity = new NewsChannel(null, channelIds[i], channelNames[i], true);
                } else {
                    entity = new NewsChannel(null, channelIds[i], channelNames[i], false);
                }
                //存入数据库
                NewsDao.insert(entity);
            }
        }

        /**
         * 图片channel
         */
        String[] pictureNames = getResources().getStringArray(R.array.picture_name);
        String[] pictureIds = getResources().getStringArray(R.array.picture_id);
        List<PictureChannel> pictureChannels = PictureDao.queryAll();
        if (pictureChannels.size() == 0) {
            for (int i = 0; i < pictureNames.length; i++) {
                PictureChannel entity = new PictureChannel(null, pictureIds[i], pictureNames[i]);
                PictureDao.insert(entity);
            }
        }

        /**
         * 视频
         */
        String[] videoNames = getResources().getStringArray(R.array.mobile_video_name);
        String[] videoIds = getResources().getStringArray(R.array.mobile_video_id);
        List<VideoChannel> videoChannels = VideoDao.queryAll();
        if (videoChannels.size() == 0) {
            for (int i = 0; i < videoNames.length; i++) {
                VideoChannel entity = new VideoChannel(null, videoNames[i], videoIds[i]);
                VideoDao.insert(entity);
            }
        }
    }

    @Override
    protected void initEvent() {
        //底部导航栏的监听事件
        mBottomNavigation.setOnNavigationItemSelectedListener(item -> {
            int i = item.getItemId();
            if (i == R.id.action_news) {
                mToolbar.setTitle(R.string.title_news);
                NewsFragment newsFragment = NewsFragment.newInstance();
                switchFragment(newsFragment, R.id.frame_layout);
            } else if (i == R.id.action_photo) {
                mToolbar.setTitle(R.string.title_photo);
                PictureFragment pictureFragment = PictureFragment.newInstance();
                switchFragment(pictureFragment, R.id.frame_layout);
            } else if (i == R.id.action_video) {
                mToolbar.setTitle(R.string.title_video);
                VideoFragment videoFragment = VideoFragment.newInstance();
                switchFragment(videoFragment, R.id.frame_layout);
            } else if (i == R.id.action_media) {
                mToolbar.setTitle(R.string.title_media);
                ChannelFragment channelFragment = ChannelFragment.newInstance();
                switchFragment(channelFragment, R.id.frame_layout);
            }
            return true;
        });

        //侧滑菜单条目的点击事件
        mNavView.setNavigationItemSelectedListener(item -> {
            int i = item.getItemId();
            if (i == R.id.nav_switch_night_mode) {

            } else if (i == R.id.nav_setting) {

            } else if (i == R.id.nav_share) {//分享
                Intent shareIntent = new Intent()
                        .setAction(Intent.ACTION_SEND)
                        .setType("text/plain")
                        .putExtra(Intent.EXTRA_TEXT, getString(R.string.share_app_text) + getString(R.string.source_code_url));
                startActivity(Intent.createChooser(shareIntent, getString(R.string.share_to)));
                mDrawerLayout.closeDrawers();
                return false;
            }
            return false;
        });

        //侧滑菜单关闭时，加载背景图
        mDrawerLayout.addDrawerListener(new DrawerLayout.SimpleDrawerListener() {
            @Override
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
            }

            @Override
            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                ImageHelper.loadBgImage(MainActivity.this, getBgPic(), mMenuHeadView);
            }
        });
    }

    private static String getBgPic() {
        Random random = new Random();
        return "http://106.14.135.179/ImmersionBar/" + random.nextInt(40) + ".jpg";
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
        super.onBackPressed();
        long secondTime = System.currentTimeMillis();
        if (secondTime - firstTime > 2000) {
            Toast.makeText(this, R.string.two_press_exit, Toast.LENGTH_SHORT).show();
            firstTime = secondTime;
        } else {
            System.exit(0);
        }
    }
}
