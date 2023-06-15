package com.cndest.picpreview.ui;

import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.media.MediaPlayerEngine;
import com.cndest.picpreview.media.MediaPlayerView;
import com.cndest.picpreview.media.OnPlayerListener;
import com.cndest.picpreview.photoview.OnPhotoTapListener;
import com.cndest.picpreview.photoview.PhotoView;
import com.cndest.picpreview.utils.DensityUtil;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 11:58
 */
public class PreviewVideoHolder extends PreviewAbsHolder {
    private static final String TAG = "PreviewVideoHolder";
    private MediaPlayerView videoPlayer;
    private MediaPlayerEngine videoPlayerEngine;
    private ImageView ivPlayButton;
    private PhotoView coverImageView;
    private ProgressBar progress;
    private boolean isPlayed = false;
    private  LocalMedia media;

    protected final int screenWidth;
    protected final int screenHeight;
    protected final int screenAppInHeight;

    public PreviewVideoHolder(@NonNull View itemView) {
        super(itemView);

        this.screenWidth = DensityUtil.getRealScreenWidth(itemView.getContext());
        this.screenHeight = DensityUtil.getScreenHeight(itemView.getContext());
        this.screenAppInHeight = DensityUtil.getRealScreenHeight(itemView.getContext());


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

        coverImageView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                PicpCore.get().getPreviewListener().onViewTap();
            }
        });
        videoPlayer.setOnClickListener(v->{
            PicpCore.get().getPreviewListener().onViewTap();
        });
    }

    @Override
    public void initView(LocalMedia localMedia,int position) {
        this.media = localMedia;
        setScaleDisplaySize(localMedia);
        PicpCore.get().imageEngin().load(coverImageView.getContext(),coverImageView,localMedia.getPath());
    }

    @Override
    public void onViewAttachedToWindow() {
        videoPlayerEngine.onPlayerAttachedToWindow(videoPlayer);
        videoPlayerEngine.addPlayListener(mPlayerListener);
    }

    @Override
    public void onViewDetachedFromWindow() {
        videoPlayerEngine.onPlayerDetachedFromWindow(videoPlayer);
        videoPlayerEngine.removePlayListener(mPlayerListener);
        playerDefaultUI();
    }
    protected void setScaleDisplaySize(LocalMedia media) {
        if (screenWidth < screenHeight) {
            ViewGroup.LayoutParams layoutParams = videoPlayer.getLayoutParams();
            if (layoutParams instanceof FrameLayout.LayoutParams) {
                FrameLayout.LayoutParams playerLayoutParams = (FrameLayout.LayoutParams) layoutParams;
                playerLayoutParams.width = screenWidth;
                playerLayoutParams.height = screenAppInHeight;
                Log.d(TAG, "setScaleDisplaySize: width:"+screenWidth+",height:"+screenAppInHeight);
                playerLayoutParams.gravity = Gravity.CENTER;
            } else if (layoutParams instanceof RelativeLayout.LayoutParams) {
                RelativeLayout.LayoutParams playerLayoutParams = (RelativeLayout.LayoutParams) layoutParams;
                playerLayoutParams.width = screenWidth;
                playerLayoutParams.height = screenAppInHeight;
                playerLayoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
            } else if (layoutParams instanceof LinearLayout.LayoutParams) {
                LinearLayout.LayoutParams playerLayoutParams = (LinearLayout.LayoutParams) layoutParams;
                playerLayoutParams.width = screenWidth;
                playerLayoutParams.height = screenAppInHeight;
                playerLayoutParams.gravity = Gravity.CENTER;
            } else if (layoutParams instanceof ConstraintLayout.LayoutParams) {
                ConstraintLayout.LayoutParams playerLayoutParams = (ConstraintLayout.LayoutParams) layoutParams;
                playerLayoutParams.width = screenWidth;
                playerLayoutParams.height = screenAppInHeight;
                playerLayoutParams.topToTop = ConstraintSet.PARENT_ID;
                playerLayoutParams.bottomToBottom = ConstraintSet.PARENT_ID;
            }
        }
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

    public void onDestroy() {
        videoPlayerEngine.removePlayListener(mPlayerListener);
        videoPlayerEngine.destroy(videoPlayer);
    }

    @Override
    public void onStop() {
        resumePausePlay();
    }
}
