package com.example.think.viewHolder.picture;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.think.R;
import com.example.think.bean.phote.PhotoArticleBean;
import com.example.base.utils.ImageHelper;
import com.example.base.utils.TimeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import me.drakeet.multitype.ItemViewBinder;

/**
 * Author: Funny
 * Time: 2018/9/10
 * Description: This is PictureArticleViewBinder
 */
public class PictureArticleViewBinder extends ItemViewBinder<PhotoArticleBean.DataBean, PictureArticleViewBinder.ViewHolder> {
    @NonNull
    @Override
    protected ViewHolder onCreateViewHolder(@NonNull LayoutInflater inflater, @NonNull ViewGroup parent) {
        View view = inflater.inflate(R.layout.item_picture_article, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    protected void onBindViewHolder(@NonNull ViewHolder holder, @NonNull PhotoArticleBean.DataBean item) {
        Context context = holder.itemView.getContext();
        //设置用户头像
        String avatarUrl = item.media_avatar_url;
        if (avatarUrl != null) {
            ImageHelper.loadCenterCrop(context, avatarUrl, holder.mIvMedia);
        }

        //加载图片
        if (item.getImage_list() != null) {
            int size = item.getImage_list().size();
            String[] ivs = new String[size];
            for (int i = 0; i < item.getImage_list().size(); i++) {
                ivs[i] = "https:" + item.getImage_list().get(i).getUrl();
            }
            switch (ivs.length) {
                case 1:
                    ImageHelper.loadCenterCrop(context, ivs[0], holder.mIv0);
                    break;
                case 2:
                    ImageHelper.loadCenterCrop(context, ivs[0], holder.mIv0);
                    ImageHelper.loadCenterCrop(context, ivs[1], holder.mIv1);
                    break;
                case 3:
                    ImageHelper.loadCenterCrop(context, ivs[0], holder.mIv0);
                    ImageHelper.loadCenterCrop(context, ivs[1], holder.mIv1);
                    ImageHelper.loadCenterCrop(context, ivs[2], holder.mIv2);
                    break;
            }
        }

        //设置title
        holder.mTvTitle.setText(item.title);

        String source = item.getSource();
        int comments_count = item.getComments_count();
        String time = item.getBehot_time() + "";
        if (!TextUtils.isEmpty(time)) {
            time = TimeUtil.getTimeStampAgo(time);
        }
        holder.mTvExtra.setText(String.format("%s - %d - %s", source, comments_count, time));

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
        @BindView(R.id.iv_0)
        ImageView mIv0;
        @BindView(R.id.iv_1)
        ImageView mIv1;
        @BindView(R.id.iv_2)
        ImageView mIv2;

        ViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }
    }
}
