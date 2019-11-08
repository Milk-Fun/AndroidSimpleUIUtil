package com.milkz.ui.progress;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import com.milkz.ui.R;

/**
 * Create by zuoqi@bhz.com.cn on 2019/10/9 17:12
 */
public class ZJuHuaProgress extends DialogFragment {

    private String text = null;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.setCancelable(false);
        View v = inflater.inflate(R.layout.zprogress_juhua, container);

        TextView tvText = v.findViewById(R.id.tv_content_z_progress);
        if (this.text != null && !this.text.equals("")){
            tvText.setText(text);
        }
        return v;
    }

    @Override
    public void onStart() {
        super.onStart();
        //设置背景色透明,在style中已设置backgroundDimEnabled为false,这里不需要.
        Window window = getDialog().getWindow();
        WindowManager.LayoutParams windowParams = window.getAttributes();
        windowParams.dimAmount = 0.0f;
        window.setAttributes(windowParams);
        window.setBackgroundDrawableResource(android.R.color.transparent);
    }

    @Override
    public void show(FragmentManager manager, String tag) {
        super.show(manager, tag);
        this.text = tag;
    }

    @Override
    public void dismiss() {
        super.dismiss();
        Log.d("ZZZ","dismiss");
    }
}
