package com.cndest.picpreview.ui;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cndest.picpreview.bean.LocalMedia;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 15:15
 */
public abstract class PreviewAbsHolder extends RecyclerView.ViewHolder {

    public PreviewAbsHolder(@NonNull View itemView) {
        super(itemView);
    }

    public abstract void initView(LocalMedia localMedia, int position);

    /**
     * 当前item的生命周期
     */
    public abstract void onViewAttachedToWindow();

    /**
     * 当前item划出界面
     */
    public abstract void onViewDetachedFromWindow();

    public abstract void onDestroy();
    public abstract void onStop();

}
