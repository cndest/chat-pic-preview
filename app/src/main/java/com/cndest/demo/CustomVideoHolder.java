package com.cndest.demo;

import android.view.View;

import androidx.annotation.NonNull;

import com.bumptech.glide.Glide;
import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PreviewAbsHolder;

import cn.jzvd.Jzvd;
import cn.jzvd.JzvdStd;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/15 17:40
 */
public class CustomVideoHolder extends PreviewAbsHolder {
    private JzvdStd videoPlayer;

    public CustomVideoHolder(@NonNull View itemView) {
        super(itemView);
        videoPlayer = itemView.findViewById(R.id.jz_video);
        videoPlayer.textureViewContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PicpCore.get().onViewTap();
            }
        });
        videoPlayer.posterImageView.setOnClickListener(v ->  PicpCore.get().onViewTap());
    }

    @Override
    public void bindData(LocalMedia localMedia, int position) {
        videoPlayer.setUp(
                localMedia.getPath(), localMedia.getFileName(), Jzvd.SCREEN_NORMAL);
        Glide.with(videoPlayer.getContext()).load(
                localMedia.getPath()).into(videoPlayer.posterImageView);
    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {
        Jzvd.goOnPlayOnPause();
    }

    @Override
    public void onDestroy() {
        Jzvd.releaseAllVideos();
    }

    @Override
    public void onStop() {
        Jzvd.goOnPlayOnResume();
    }
}
