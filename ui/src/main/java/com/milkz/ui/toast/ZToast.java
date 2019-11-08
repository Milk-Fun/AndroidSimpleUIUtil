package com.milkz.ui.toast;

import android.content.Context;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.milkz.ui.R;

import java.util.Timer;
import java.util.TimerTask;

/**
 * Create by zuoqi@bhz.com.cn on 2019/11/8 10:41
 */
public class ZToast extends Toast {

    private static Toast toast;

    public ZToast(Context context) {
        super(context);
    }

    /**
     * 自定义toast，可以实现显示对应时间
     *
     * @param context 环境
     * @param text    文本
     * @param time    显示时间
     */
    public static void showToast(Context context, String text, long time) {
        View v = LayoutInflater.from(context).inflate(R.layout.ztoast_define, null);
        TextView tv = v.findViewById(R.id.tv_toast);
        tv.setText(text);
        if (toast != null) {
            toast.cancel();
        }
        toast = new Toast(context);
        toast.setView(v);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setGravity(Gravity.CENTER, 0, 0);

        final Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                toast.show();
            }
        }, 0, 3000);
        new Timer().schedule(new TimerTask() {
            @Override
            public void run() {
                toast.cancel();
                timer.cancel();
            }
        }, time);

    }

}
