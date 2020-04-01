package com.example.toutiao.widget;


import com.example.base.AppManager;
import com.example.toutiao.R;

import java.util.Collections;
import java.util.List;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.RecyclerView;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

/**
 * Author: Funny
 * Time: 2018/10/10
 * Description: This is ItemTouchCallback
 */
public class ItemTouchCallback<T> extends ItemTouchHelper.Callback {

    private List<T> mDatas;
    private RecyclerView.Adapter mAdapter;

    public ItemTouchCallback(List<T> datas, RecyclerView.Adapter adapter) {
        mDatas = datas;
        mAdapter = adapter;
    }

    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags;

        RecyclerView.LayoutManager layoutManager = recyclerView.getLayoutManager();
        if (layoutManager instanceof GridLayoutManager || layoutManager instanceof StaggeredGridLayoutManager) {
            dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT | ItemTouchHelper.UP;
        } else {
            dragFlags = ItemTouchHelper.DOWN | ItemTouchHelper.UP;
        }

        //支持滑动操作
        int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

        //不支持滑动操作
        //int swipeFlags = 0;
        return makeMovementFlags(dragFlags, swipeFlags);
    }

    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        //不同类型的条目不允许拖拽滑动
        if(viewHolder.getItemViewType() != target.getItemViewType()){
            return false;
        }

        //处理拖动排序
        // 使用Collection对数组进行重排序，目的是把我们拖动的Item换到下一个目标Item的位置
        Collections.swap(mDatas, viewHolder.getAdapterPosition(), target.getAdapterPosition());
        //通知Adapter它的Item发生了移动
        mAdapter.notifyItemMoved(viewHolder.getAdapterPosition(), target.getAdapterPosition());
        return true;
    }

    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        //处理滑动删除 //直接从数据中删除该Item的数据
        mDatas.remove(viewHolder.getAdapterPosition());
        //通知Adapter有Item被移除了
        mAdapter.notifyItemRemoved(viewHolder.getAdapterPosition());
    }

    @Override
    public boolean isLongPressDragEnabled() {
        return true;
    }

    @Override
    public boolean isItemViewSwipeEnabled() {
        return false;
    }

    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
        if(actionState != ItemTouchHelper.ACTION_STATE_IDLE){
            viewHolder.itemView.setBackground(AppManager.getAppManagerContext().getResources().getDrawable(R.drawable.channle_view_bg_selected_shape));
        }
    }

    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
        viewHolder.itemView.setBackground(AppManager.getAppManagerContext().getResources().getDrawable(R.drawable.channle_view_bg_normal_shape));
    }
}
