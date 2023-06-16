package com.cndest.picpreview;

import android.view.ViewGroup;

import com.cndest.picpreview.ui.PreviewAbsHolder;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/15 17:34
 */
public interface PreviewHolder {

    /**
     * @param viewType
     * @return
     */
    PreviewAbsHolder newHolder(ViewGroup container, int viewType);
}
