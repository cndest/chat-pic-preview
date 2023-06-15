package com.cndest.picpreview.ui;

import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;

import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.media.MediaPlayerEngine;
import com.cndest.picpreview.media.MediaPlayerView;
import com.cndest.picpreview.media.OnPlayerListener;
import com.cndest.picpreview.photoview.PhotoView;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 11:58
 */
public class PreviewVideoHolder extends PreviewAbsHolder {
    private MediaPlayerView videoPlayer;
    private MediaPlayerEngine videoPlayerEngine;
    private ImageView ivPlayButton;
    private PhotoView coverImageView;
    private ProgressBar progress;
    private boolean isPlayed = false;
    private  LocalMedia media;

    public PreviewVideoHolder(@NonNull View itemView) {
        super(itemView);
        videoPlayer = itemView.findViewById(R.id.mediaView);
        videoPlayerEngine = new MediaPlayerEngine();
        ivPlayButton = itemView.findViewById(R.id.ivPlay);
        coverImageView = itemView.findViewById(R.id.photoView);
        progress = itemView.findViewById(R.id.progress);

        if (videoPlayer.getLayoutParams() == null) {
            videoPlayer.setLayoutParams(new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT));
        }

        videoPlayer.setVisibility(View.GONE);
        ivPlayButton.setOnClickListener(v->{
            dispatchPlay();
        });
    }

    @Override
    public void initView(LocalMedia localMedia,int position) {
        this.media = localMedia;
    }

    @Override
    public void onViewAttachedToWindow() {
        videoPlayerEngine.onPlayerAttachedToWindow(videoPlayer);
    }

    @Override
    public void onViewDetachedFromWindow() {
        videoPlayerEngine.onPlayerDetachedFromWindow(videoPlayer);
    }

    /**
     * 视频播放状态分发
     */
    private void dispatchPlay() {
        if (isPlayed) {
            if (isPlaying()) {
                onPause();
            } else {
                onResume();
            }
        } else {
            startPlay();
        }
    }

    /**
     * 恢复播放
     */
    private void onResume() {
        ivPlayButton.setVisibility(View.GONE);
        videoPlayerEngine.onResume(videoPlayer);
    }

    /**
     * 暂停播放
     */
    public void onPause() {
        ivPlayButton.setVisibility(View.VISIBLE);
        videoPlayerEngine.onPause(videoPlayer);
    }

    /**
     * 是否正在播放中
     */
    public boolean isPlaying() {
        return videoPlayerEngine.isPlaying(videoPlayer);
    }

    /**
     * 外部播放状态监听回调
     */
    private final OnPlayerListener mPlayerListener = new OnPlayerListener() {
        @Override
        public void onPlayerError() {
            playerDefaultUI();
        }

        @Override
        public void onPlayerReady() {
            playerIngUI();
        }

        @Override
        public void onPlayerLoading() {
            progress.setVisibility(View.VISIBLE);
        }

        @Override
        public void onPlayerEnd() {
            playerDefaultUI();
        }
    };

    /**
     * 开始播放视频
     */
    public void startPlay() {
        progress.setVisibility(View.VISIBLE);
        ivPlayButton.setVisibility(View.GONE);
        isPlayed = true;
        videoPlayerEngine.onStarPlayer(videoPlayer, media);
    }

    private void playerDefaultUI() {
        isPlayed = false;
        ivPlayButton.setVisibility(View.VISIBLE);
        progress.setVisibility(View.GONE);
        coverImageView.setVisibility(View.VISIBLE);
        videoPlayer.setVisibility(View.GONE);
    }

    private void playerIngUI() {
        progress.setVisibility(View.GONE);
        ivPlayButton.setVisibility(View.GONE);
        coverImageView.setVisibility(View.GONE);
        videoPlayer.setVisibility(View.VISIBLE);
    }

    /**
     * resume and pause play
     */
    public void resumePausePlay() {
        if (isPlaying()) {
            onPause();
        } else {
            onResume();
        }
    }

    public void release() {
        videoPlayerEngine.removePlayListener(mPlayerListener);
        videoPlayerEngine.destroy(videoPlayer);
    }
}
