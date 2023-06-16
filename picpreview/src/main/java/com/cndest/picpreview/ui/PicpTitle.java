package com.cndest.picpreview.ui;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
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
    //动画
    protected TranslateAnimation mShowActionBottom;
    protected TranslateAnimation mHiddenActionBottom;
    protected ImageView ivLeft;
    protected TextView tvTitle;

    public PicpTitle(Context context) {
        this(context, null);
    }

    public PicpTitle(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(layoutResId(), this, true);
        initAnimation();
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

    /**
     * 初始化动画
     */
    protected void initAnimation() {
        //显示
        mShowActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowActionBottom.setDuration(300);
        //隐藏
        mHiddenActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                -1.0f);
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
