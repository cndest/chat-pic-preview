package com.cndest.picpreview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.cndest.picpreview.R;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 17:24
 */
public class PicpBottomMenu extends LinearLayout {
    public PicpBottomMenu(Context context) {
        this(context, null);
    }

    public PicpBottomMenu(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pp_view_bottom, this, true);
    }
}
