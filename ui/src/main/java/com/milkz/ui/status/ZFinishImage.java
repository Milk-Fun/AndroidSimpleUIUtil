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
public class ZFinishImage extends View {

    private Paint paint;
    private Paint paint2;
    private Path pathOk;
    private Path pathOk2;
    private PathMeasure pathMeasure;
    private boolean ifFirst;

    private float fraction;

    public ZFinishImage(Context context) {
        this(context, null);
    }

    public ZFinishImage(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.parseColor("#3C86FF"));
        paint.setStyle(Paint.Style.FILL);

        paint2 = new Paint();
        paint2.setColor(Color.WHITE);
        paint2.setStyle(Paint.Style.STROKE);
        paint2.setStrokeWidth(dp2px(5, getContext()));

        pathMeasure = new PathMeasure();

        Path pathCirCle = new Path();
        pathCirCle.addCircle(dp2px(30, getContext()), dp2px(30, getContext()), dp2px(30, getContext()), Path.Direction.CCW);
        pathCirCle.moveTo(dp2px(14, getContext()), dp2px(28, getContext()));
        pathCirCle.lineTo(dp2px(28, getContext()), dp2px(43, getContext()));
        pathCirCle.lineTo(dp2px(50, getContext()), dp2px(23, getContext()));
        pathOk = new Path();
        pathOk2 = new Path();
        pathMeasure.setPath(pathCirCle, false);

        ifFirst = true;
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
            float len = pathMeasure.getLength();
            float et = len * fraction;
            pathMeasure.getSegment(0, et, pathOk, true);
        } else {
            if (ifFirst){
                ifFirst = false;
                pathMeasure.nextContour();
            }
            float len = pathMeasure.getLength();
            float et = len * (fraction - 1);
            pathMeasure.getSegment(0,et,pathOk2,true);
        }

        canvas.drawPath(pathOk, paint);
        canvas.drawPath(pathOk2,paint2);
    }

    public static int dp2px(int value, Context context) {
        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, value, context.getResources().getDisplayMetrics());
    }
}
