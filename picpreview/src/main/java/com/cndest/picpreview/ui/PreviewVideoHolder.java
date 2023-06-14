package com.cndest.picpreview.ui;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 11:58
 */
class PreviewVideoHolder extends PreviewAbsHolder {
    private TextView tvTest;
    public PreviewVideoHolder(@NonNull View itemView) {
        super(itemView);
        tvTest = itemView.findViewById(R.id.tvTest);
    }

    @Override
    public void initView(LocalMedia localMedia,int position) {
        tvTest.setText(position+"");
    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }
}
