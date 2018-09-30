package com.example.think.viewHolder.news;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupMenu;
import android.widget.TextView;

import com.example.think.Constants;
import com.example.think.R;
import com.example.think.bean.news.MultiNewsArticleDataBean;
import com.example.think.ui.newsContent.NewsContentActivity;
import com.example.think.utils.ImageHelper;
import com.example.think.utils.TimeUtil;
import com.jakewharton.rxbinding2.view.RxView;
import com.jakewharton.rxbinding2.widget.RxPopupMenu;

import java.util.List;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Author: Funny
 * Time: 2018/8/31
 * Description: This is NewsArticleImgViewBinder
 */
public class NewsArticleImgViewBinder extends ItemViewBinder<MultiNewsArticleDataBean, NewsArticleImgViewBinder.ViewHolder> {

    @NonNull
    @Override
    protected NewsArticleImgViewBinder.ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_news_article_img, parent, false);
        return new ViewHolder(view);
    }

    @SuppressLint("CheckResult")
    @Override
    protected void onBindViewHolder(@NonNull NewsArticleImgViewBinder.ViewHolder holder, @NonNull MultiNewsArticleDataBean item) {
        Context context = holder.itemView.getContext();

        //设置用户头像
        MultiNewsArticleDataBean.UserInfoBean user_info = item.getUser_info();
        if (user_info != null) {
            String avatar_url = user_info.getAvatar_url();
            ImageHelper.loadCenterCrop(context, avatar_url, holder.mIvMedia);
        }

        //新闻时间
        String tvDataTime = item.getBehot_time();
        if (!TextUtils.isEmpty(tvDataTime)) {
            tvDataTime = TimeUtil.getTimeStampAgo(tvDataTime);
        }

        holder.mTvExtra.setText(String.format("%s - %s - %s", item.getSource(), item.getComment_count(), tvDataTime));
        holder.mTvTitle.setText(item.getTitle());
        holder.mTvAbstract.setText(item.getAbstractX());

        RxView.clicks(holder.mIvDots)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    //弹出分享菜单
                    PopupMenu popupMenu = new PopupMenu(context, holder.mIvDots, Gravity.END);
                    popupMenu.inflate(R.menu.menu_share);
                    RxPopupMenu.itemClicks(popupMenu)
                            .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                            .subscribe(menuItem -> {
                                if (menuItem.getItemId() == R.id.action_share) {
                                    Intent shareIntent = new Intent()
                                            .setAction(Intent.ACTION_SEND)
                                            .setType("text/plain")
                                            .putExtra(Intent.EXTRA_TEXT, "分享");
                                    context.startActivity(Intent.createChooser(shareIntent, context.getString(R.string.share_to)));
                                }
                            });
                    popupMenu.show();
                });

        String imgUrl = "http://p3.pstatp.com/";
        List<MultiNewsArticleDataBean.ImageListBean> image_list = item.getImage_list();
        if (image_list != null && image_list.size() > 0) {
            //这里是url
            String urL = image_list.get(0).getUrl();
            ImageHelper.loadCenterCrop(context, urL, holder.mIvImage);

            //这里是uri
            String uri = image_list.get(0).getUri();
            if (!TextUtils.isEmpty(uri)) {
                // TODO: 2018/8/31 这个imgUrl要传给里面的activity
                imgUrl += uri.replace("list", "large");
            }
        }

        //条目点击事件
        String picUrl = imgUrl;
        RxView.clicks(holder.itemView)
                .throttleFirst(Constants.CLICK_INTERVAL, TimeUnit.SECONDS)
                .subscribe(o -> {
                    NewsContentActivity.start(context, picUrl, item);
                    //ScrollingActivity.start(context);
                    //NavigationActivity.start(context);
                });
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.iv_media)
        ImageView mIvMedia;
        @BindView(R.id.tv_extra)
        TextView mTvExtra;
        @BindView(R.id.iv_dots)
        ImageView mIvDots;
        @BindView(R.id.header)
        LinearLayout mHeader;
        @BindView(R.id.tv_title)
        TextView mTvTitle;
        @BindView(R.id.tv_abstract)
        TextView mTvAbstract;
        @BindView(R.id.iv_image)
        ImageView mIvImage;
        @BindView(R.id.content)
        LinearLayout mContent;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
