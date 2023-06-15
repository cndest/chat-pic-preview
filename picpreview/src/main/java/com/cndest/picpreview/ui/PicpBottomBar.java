package com.cndest.picpreview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.Nullable;

import com.cndest.picpreview.R;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 17:24
 */
public class PicpBottomBar extends LinearLayout {

    protected ImageView ivDownload,ivShare;

    //动画
    private TranslateAnimation mShowActionBottom;
    private TranslateAnimation mHiddenActionBottom;

    public PicpBottomBar(Context context) {
        this(context, null);
    }

    public PicpBottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pp_view_bottom, this, true);
        initAnimation();
        initView();
    }

    private void initView() {
        ivDownload = findViewById(R.id.ivDownload);
        ivShare = findViewById(R.id.ivShare);
    }

    /**
     * 初始化动画
     */
    private void initAnimation() {
        //显示
        mShowActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowActionBottom.setDuration(300);
        //隐藏
        mHiddenActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
        mHiddenActionBottom.setDuration(300);
    }

    @Override
    public void setVisibility(int visibility) {
        if (visibility == View.VISIBLE) {
            startAnimation(mShowActionBottom);
        } else {
            startAnimation(mHiddenActionBottom);
        }
        super.setVisibility(visibility);
    }
}
