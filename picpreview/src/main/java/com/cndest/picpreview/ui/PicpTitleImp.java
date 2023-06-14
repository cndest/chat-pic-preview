package com.cndest.picpreview.ui;

import android.content.Context;
import android.util.AttributeSet;

import androidx.annotation.Nullable;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 17:57
 */
public class PicpTitleImp extends PicpTitle{

    public PicpTitleImp(Context context) {
        super(context);
    }

    public PicpTitleImp(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public int bgColor() {
        return 0;
    }

    @Override
    public int textColor() {
        return 0;
    }
}
