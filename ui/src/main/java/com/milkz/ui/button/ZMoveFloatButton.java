package com.milkz.ui.button;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.support.design.widget.FloatingActionButton;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.animation.DecelerateInterpolator;

/**
 * Create by zuoqi@bhz.com.cn on 2019/11/6 17:20
 * <p>
 * 可移动的悬浮按钮，但是这种悬浮按钮，无法设置按钮背景色为自定义background
 * </p>
 */
public class ZMoveFloatButton extends FloatingActionButton {

    private static final String TAG = "MoveFloatButton";

    private boolean isDrag;
    private boolean ifMove;
    private int lastX;
    private int lastY;
    private int screenWidth;
    private int screenHeight;
    private int screenWidthHalf;
    private int statusHeight;
    private int virtualHeight;

    public ZMoveFloatButton(Context context) {
        this(context, null);
    }

    public ZMoveFloatButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ZMoveFloatButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        screenWidth = ScreenUtils.getScreenWidth(getContext());
        screenWidthHalf = screenWidth / 2;
        screenHeight = ScreenUtils.getScreenHeight(getContext());
        statusHeight = ScreenUtils.getStatusHeight(getContext());
        virtualHeight = ScreenUtils.getVirtualBarHeigh(getContext());
    }

    // TODO 移动偶尔会触发点击事件

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        int rawX = (int) ev.getRawX();
        int rawY = (int) ev.getRawY();
        switch (ev.getAction() & MotionEvent.ACTION_MASK) {
            case MotionEvent.ACTION_DOWN:
                isDrag = false;
                ifMove = false;
                getParent().requestDisallowInterceptTouchEvent(true); // 在down的时候，屏蔽父类的事件屏蔽
                lastX = rawX;
                lastY = rawY;
                break;
            case MotionEvent.ACTION_MOVE:
                isDrag = true;
                //计算手指移动了多少
                int dx = rawX - lastX;
                int dy = rawY - lastY;
                //这里修复一些手机无法触发点击事件的问题
                int distance = (int) Math.sqrt(dx * dx + dy * dy);// 勾股计算移动距离
                Log.e("distance---->", distance + "");
                if (distance < 3) {//给个容错范围，不然有部分手机还是无法点击
                    isDrag = false;
                    break;
                }
                ifMove = true;
                float x = getX() + dx;
                float y = getY() + dy;

                //检测是否到达边缘 左上右下
                x = x < 0 ? 0 : x > screenWidth - getWidth() ? screenWidth - getWidth() : x;
                // y = y < statusHeight ? statusHeight : (y + getHeight() >= screenHeight ? screenHeight - getHeight() : y);
                if (y < 0) {
                    y = 0;
                }
                if (y > screenHeight - statusHeight - getHeight()) {
                    y = screenHeight - statusHeight - getHeight();
                }
                setX(x);
                setY(y);

                lastX = rawX;
                lastY = rawY;
                Log.e("move---->", "getX=" + getX() + "；screenWidthHalf=" + screenWidthHalf + " "
                        + isDrag + "  statusHeight=" + statusHeight + " virtualHeight" + virtualHeight
                        + " screenHeight" + screenHeight + "  getHeight=" + getHeight() + " y" + y);
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "ACTION_UP !");
                if (isDrag)
                    //恢复按压效果
                    setPressed(false);
                if (rawX >= screenWidthHalf) {
                    animate().setInterpolator(new DecelerateInterpolator())
                            .setDuration(500)
                            .xBy(screenWidth - getWidth() - getX())
                            .start();
                } else {
                    ObjectAnimator oa = ObjectAnimator.ofFloat(this, "x", getX(), 0);
                    oa.setInterpolator(new DecelerateInterpolator());
                    oa.setDuration(500);
                    oa.start();
                }
                break;
        }
        //如果是拖拽则消耗事件，否则正常传递即可。
        return (ifMove || isDrag || super.onTouchEvent(ev));
    }
}
