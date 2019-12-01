package com.milkzz.android_ui.ui14;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by zuoqi on 2019/12/1
 */
public class FlowLayout extends ViewGroup {

    public FlowLayout(Context context) {
        super(context);
    }

    public FlowLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        int measureWidth = MeasureSpec.getSize(widthMeasureSpec);
        int measureWidthMode = MeasureSpec.getMode(widthMeasureSpec);
        int measureHeight = MeasureSpec.getSize(heightMeasureSpec);
        int measureHeightMode = MeasureSpec.getMode(heightMeasureSpec);

        int eachWidth = 0;
        int eachHeight = 0;
        int height = 0;
        int width = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {
            View child = getChildAt(i);
            measureChild(child, widthMeasureSpec, heightMeasureSpec);

            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = mlp.leftMargin + child.getMeasuredWidth() + mlp.rightMargin;
            int childHeight = mlp.topMargin + child.getMeasuredHeight() + mlp.bottomMargin;

            if (eachWidth + childWidth > measureWidth) {

                // 需要换行
                width = Math.max(width, eachWidth);
                height += eachHeight;
                eachHeight = childHeight;
                eachWidth = childWidth;
            } else {
                eachWidth += childWidth;
                eachHeight = Math.max(eachHeight, childHeight);
            }

            // 处理最后一行
            if (i == count - 1) {
                height += childHeight;
                width = Math.max(width, childWidth);
            }
        }

        setMeasuredDimension((measureWidthMode == MeasureSpec.EXACTLY) ? measureWidth : width,
                (measureHeightMode == MeasureSpec.EXACTLY) ? measureHeight : height);
    }


    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {

        int lineWidth = 0, lineHeight = 0;
        int top = 0, left = 0;

        int count = getChildCount();
        for (int i = 0; i < count; i++) {

            View child = getChildAt(i);

            MarginLayoutParams mlp = (MarginLayoutParams) child.getLayoutParams();
            int childWidth = mlp.leftMargin + child.getMeasuredWidth() + mlp.rightMargin;
            int childHeight = mlp.topMargin + child.getMeasuredHeight() + mlp.bottomMargin;

            if (lineWidth + childWidth > getMeasuredWidth()) {

                // 换行
                top += lineHeight;
                left = 0;
                lineHeight = childHeight;
                lineWidth = childWidth;
            } else {
                lineWidth += childWidth;
                lineHeight = Math.max(lineHeight,childHeight);
            }

            // 计算位置
            int lc = left + mlp.leftMargin;
            int tc = top + mlp.rightMargin;
            int rc = lc + child.getMeasuredWidth();
            int bc = tc + child.getMeasuredHeight();
            child.layout(lc, tc, rc, bc);

            // 更新位置
            left += childWidth;
        }
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
        return new MarginLayoutParams(LayoutParams.MATCH_PARENT,
                LayoutParams.MATCH_PARENT);
    }
}
