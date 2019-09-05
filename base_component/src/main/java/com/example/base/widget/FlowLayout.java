package com.example.base.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

/**
 * 流式布局
 *
 * @author liangzx
 * @version 1.0
 * @time 2018-05-19 16:41
 **/
public class FlowLayout extends ViewGroup {

    private static final String TAG = "FlowLayout";

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public FlowLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected LayoutParams generateLayoutParams(LayoutParams p) {
        return new MarginLayoutParams(p);
    }

    @Override
    public LayoutParams generateLayoutParams(AttributeSet attrs) {
        return new MarginLayoutParams(getContext(), attrs);
    }

    @Override
    protected LayoutParams generateDefaultLayoutParams() {
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
    }

    /**
     * 负责设置子控件的测量模式和大小 根据所有子控件设置自己的宽和高
     */
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 获得它的父容器为它设置的测量模式和大小
        int sizeWidth = MeasureSpec.getSize(widthMeasureSpec);
        int sizeHeight = MeasureSpec.getSize(heightMeasureSpec);
        int modeWidth = MeasureSpec.getMode(widthMeasureSpec);
        int modeHeight = MeasureSpec.getMode(heightMeasureSpec);
        Log.e(TAG, sizeWidth + "," + sizeHeight);

        int contentWidth = sizeWidth - getPaddingLeft() - getPaddingRight();//内容的宽度
        int contentHeight = sizeHeight - getPaddingTop() - getPaddingBottom();//内容的高度
        int width = 0;
        int height = 0;
        int lineWidth = 0;//记录每一行的宽度，width不断取最大宽度
        int lineHeight = 0;//每一行的高度，累加至height
        int cCount = getChildCount();
        if (mFlowMode == FlowMode.HORIZENTAL) {
            for (int i = 0; i < cCount; i++) {
                View child = getChildAt(i);
                // 测量每一个child的宽和高
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                // 得到child的lp
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                // 当前子空间实际占据的宽度
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                // 当前子空间实际占据的高度
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                /**
                 * 如果加入当前child超出最大宽度，则得到目前最大宽度给width，累加height 然后开启新行
                 */
                if (lineWidth + childWidth > contentWidth) {
                    width = Math.max(lineWidth, childWidth);// 取最大的
                    lineWidth = childWidth; // 重新开启新行，开始记录
                    // 叠加当前高度，
                    height += lineHeight;
                    // 开启记录下一行的高度
                    lineHeight = childHeight;
                } else {// 否则累加值lineWidth,lineHeight取最大高度
                    lineWidth += childWidth;
                    lineHeight = Math.max(lineHeight, childHeight);
                }
                // 如果是最后一个，则将当前记录的最大宽度和当前lineWidth做比较
                if (i == cCount - 1) {
                    width = Math.max(width, lineWidth);
                    height += lineHeight;
                }
            }
            width = getPaddingLeft() + width + getPaddingRight();
            height = getPaddingTop() + height + getPaddingBottom();
        } else {
            for (int i = 0; i < cCount; i++) {
                View child = getChildAt(i);
                // 测量每一个child的宽和高
                measureChild(child, widthMeasureSpec, heightMeasureSpec);
                // 得到child的lp
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                // 当前子空间实际占据的宽度
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                // 当前子空间实际占据的高度
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

                /**
                 * 如果加入当前child，则超出最大高度，则的到目前最大高度给height，累加width 然后开启新列
                 */
                if (lineHeight + childHeight > contentHeight) {
                    height = Math.max(lineHeight, childHeight);// 取最大的
                    lineHeight = childHeight; // 重新开启新行，开始记录
                    // 叠加当前高度，
                    width += lineWidth;
                    // 开启记录下一行的高度
                    lineWidth = childWidth;
                } else
                // 否则累加值lineHeight,lineWidth取最大高度
                {
                    lineHeight += childHeight;
                    lineWidth = Math.max(lineWidth, childWidth);
                }
                // 如果是最后一个，则将当前记录的最大高度和当前lineHeight做比较
                if (i == cCount - 1) {
                    height = Math.max(height, lineHeight);
                    width += lineWidth;
                }
            }
            width = getPaddingLeft() + width + getPaddingRight();
            height = getPaddingTop() + height + getPaddingBottom();
        }
        // 遍历每个子元素
        setMeasuredDimension((modeWidth == MeasureSpec.EXACTLY) ? sizeWidth : width, (modeHeight == MeasureSpec.EXACTLY) ? sizeHeight : height);
    }

    /**
     * 存储所有的View，按行记录
     */
    private List<List<View>> mAllViews = new ArrayList<List<View>>();
    /**
     * 记录每一行的最大高度
     */
    private List<Integer> mLineHeight = new ArrayList<Integer>();
    /**
     * 记录每一列的最大宽度
     */
    private List<Integer> mLineWidth = new ArrayList<Integer>();

    public enum FlowMode {
        HORIZENTAL, VERTICAL
    }

    public void setFlowMode(FlowMode flowMode) {
        if (flowMode != mFlowMode){
            mFlowMode = flowMode;
            requestLayout();
        } else {
            mFlowMode = flowMode;
        }
    }

    public FlowMode getFlowMode() {
        return mFlowMode;
    }

    FlowMode mFlowMode = FlowMode.HORIZENTAL;

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        mAllViews.clear();
        if (mFlowMode == FlowMode.HORIZENTAL) {
            mLineHeight.clear();
        } else {
            mLineWidth.clear();
        }
        int paddingLeft = getPaddingLeft();
        int paddingTop = getPaddingTop();
        int paddingRight = getPaddingRight();
        int paddingBottom = getPaddingBottom();

        int contentWidth = getMeasuredWidth() - paddingLeft - paddingRight;
        int contentHeight = getMeasuredHeight() - paddingTop - paddingBottom;

        int lineWidth = 0;//起始行宽
        int lineHeight = 0;//起始行高

        // 存储每一行所有的childView
        List<View> lineViews = new ArrayList<View>();
        int cCount = getChildCount();

        if (mFlowMode == FlowMode.HORIZENTAL) {
            // 遍历所有的孩子
            for (int i = 0; i < cCount; i++) {
                View child = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

                if (lineWidth + childWidth <= contentWidth) {
                    lineWidth = lineWidth + childWidth;
                    lineHeight = Math.max(childHeight, lineHeight);
                    lineViews.add(child);
                } else {
                    //记录之前的测量结果
                    mAllViews.add(lineViews);
                    mLineHeight.add(lineHeight);
                    //将超长的放下一行
                    lineViews = new ArrayList<>();
                    lineWidth = childWidth;
                    lineHeight = Math.max(childHeight, lineHeight);
                    lineViews.add(child);
                }
            }

            // 记录最后一行
            mLineHeight.add(lineHeight);
            mAllViews.add(lineViews);

            int left = paddingLeft;
            int top = paddingTop;

//        int left = 0;
//        int top = 0;
            // 得到总行数
            int lineNums = mAllViews.size();
            for (int i = 0; i < lineNums; i++) {
                // 每一行的所有的views
                lineViews = mAllViews.get(i);
                // 当前行的最大高度
                lineHeight = mLineHeight.get(i);
                // 遍历当前行所有的View
                for (int j = 0; j < lineViews.size(); j++) {
                    View child = lineViews.get(j);
                    if (child.getVisibility() == View.GONE) {
                        continue;
                    }
                    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                    // 计算childView的left,top,right,bottom
                    int lc = left + lp.leftMargin;
                    int tc = top + lp.topMargin;
                    int rc = lc + child.getMeasuredWidth();
                    int bc = tc + child.getMeasuredHeight();
                    child.layout(lc, tc, rc, bc);
                    left += child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                }
                left = paddingLeft;
                top += lineHeight;
            }
        } else {
            for (int i = 0; i < cCount; i++) {
                View child = getChildAt(i);
                MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                int childWidth = child.getMeasuredWidth() + lp.leftMargin + lp.rightMargin;
                int childHeight = child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;

                if (lineHeight + childHeight <= contentHeight) {
                    lineHeight = lineHeight + childHeight;
                    lineWidth = Math.max(childWidth, lineWidth);
                    lineViews.add(child);
                } else {
                    //记录之前的测量结果
                    mAllViews.add(lineViews);
                    mLineWidth.add(lineWidth);
                    //将超长的放下一行
                    lineViews = new ArrayList<>();
                    lineHeight = childHeight;
                    lineWidth = Math.max(childWidth, lineWidth);
                    lineViews.add(child);
                }
            }

            // 记录最后一行
            mLineWidth.add(lineWidth);
            mAllViews.add(lineViews);

            int left = paddingLeft;
            int top = paddingTop;

            // 得到总行数
            int lineNums = mAllViews.size();
            for (int i = 0; i < lineNums; i++) {
                // 每一列的所有的views
                lineViews = mAllViews.get(i);
                // 当前行的最大款度
                lineWidth = mLineWidth.get(i);
                // 遍历当前行所有的View
                for (int j = 0; j < lineViews.size(); j++) {
                    View child = lineViews.get(j);
                    if (child.getVisibility() == View.GONE) {
                        continue;
                    }
                    MarginLayoutParams lp = (MarginLayoutParams) child.getLayoutParams();
                    // 计算childView的left,top,right,bottom
                    int lc = left + lp.leftMargin;
                    int tc = top + lp.topMargin;
                    int rc = lc + child.getMeasuredWidth();
                    int bc = tc + child.getMeasuredHeight();
                    child.layout(lc, tc, rc, bc);
                    top += child.getMeasuredHeight() + lp.topMargin + lp.bottomMargin;
                }
                top = paddingTop;
                left += lineWidth;
            }
        }
    }
}
