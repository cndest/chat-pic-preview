package com.cndest.picpreview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.R;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 17:24
 */
public class PicpTitle extends LinearLayout {

    private ImageView ivLeft;
    private TextView tvTitle;

    public PicpTitle(Context context) {
        this(context, null);
    }

    public PicpTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(layoutResId(), this, true);
        initView();
    }

    protected void initView() {
        ivLeft = findViewById(R.id.ivLeft);
        ivLeft.setOnClickListener(v -> {
            PicpCore.get().pageFinish();
        });
        tvTitle = findViewById(R.id.tvTitle);
    }

    protected int layoutResId() {
        return R.layout.pp_view_title;
    }

    public void setTitle(String title) {
        tvTitle.setText(title);
    }
}
