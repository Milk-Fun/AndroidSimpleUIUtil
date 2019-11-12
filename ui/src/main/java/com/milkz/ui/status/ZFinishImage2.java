package com.milkz.ui.status;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;

/**
 * Create by zuoqi@bhz.com.cn on 2019/11/12 9:59
 */
public class ZFinishImage2 extends View {

    private Paint paint2;
    private Paint paint3;
    private Path pathOk2;
    private PathMeasure pathMeasure;

    private float fraction;
    private static final int radius = 30; // 圆的半径
    private float r;
    private float privateX;
    private float privateY;
    private float fR = 0;

    public ZFinishImage2(Context context) {
        this(context, null);
    }

    public ZFinishImage2(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        r = dp2px(radius, getContext());
        privateX = dp2px(30, getContext());
        privateY = dp2px(30, getContext());

        paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(dp2px(5, getContext()));

        paint3 = new Paint();
        paint3.setColor(Color.parseColor("#3C86FF"));
        paint3.setStyle(Paint.Style.FILL);


        pathMeasure = new PathMeasure();

        Path pathCirCle = new Path();
        pathCirCle.moveTo(dp2px(14, getContext()), dp2px(28, getContext()));
        pathCirCle.lineTo(dp2px(28, getContext()), dp2px(43, getContext()));
        pathCirCle.lineTo(dp2px(50, getContext()), dp2px(23, getContext()));
        pathOk2 = new Path();
        pathMeasure.setPath(pathCirCle, false);

        startAnim();
    }

    private void startAnim() {
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 2);
        valueAnimator.setDuration(2000);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                fraction = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(Color.WHITE);

        if (fraction <= 1) {
            fR = r * fraction;
        } else {
            float len = pathMeasure.getLength();
            float et = len * (fraction - 1);
            pathMeasure.getSegment(0, et, pathOk2, true);
        }

        canvas.drawCircle(privateX, privateY, fR, paint3);
        canvas.drawPath(pathOk2, paint2);
    }

    private int dp2px(int value, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
