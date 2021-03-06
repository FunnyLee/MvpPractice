package com.example.base.router;

/**
 * Author: Funny
 * Time: 2019/7/25
 * Description: This is ARouter路径管理类
 */
public class RouterManager {

    /**
     * toutiao
     */
    public static final String GROUP_TOUTIAO = "/group_toutiao";

    public static final String MAIN_ACTIVTY_URL = GROUP_TOUTIAO + "/MainActivity";

    public static final String NEWS_CONTENT_ACTIVITY = GROUP_TOUTIAO + "/NewsContentActivity";

    public static final String NEWS_CHANNEL_ACTIVITY = GROUP_TOUTIAO + "/NewsChannelActivity";

    /**
     * wanAndroid
     */
    public static final String GROUP_WAN_ANDROID = "/group_wan_android";

    public static final String WAN_ANDROID_MAIN_ACTIVITY = GROUP_WAN_ANDROID + "/WanAndroidMainActivity";

    public static final String HOME_FRAGMENT = GROUP_WAN_ANDROID + "/HomeFragment";

    public static final String PROJECT_FRAGMENT = GROUP_WAN_ANDROID + "/ProjectFragment";

    public static final String PROJECT_CONTENT_FRAGMENT = GROUP_WAN_ANDROID + "/ProjectContentFragment";

    public static final String SYSTEM_FRAGMENT = GROUP_WAN_ANDROID + "/SystemFragment";

    public static final String SYSTEM_CONTENT_FRAGMENT = GROUP_WAN_ANDROID + "/SystemContentFragment";

    public static final String NAVIGATION_CONTENT_RAGMENT = GROUP_WAN_ANDROID + "/NavigationContentFragment";

    public static final String ARTICLE_WEBVIEW_ACTIVITY = GROUP_WAN_ANDROID + "/ArticleWebViewActivity";

}
