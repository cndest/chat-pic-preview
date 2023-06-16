package com.cndest.picpreview;

import android.view.ViewGroup;

import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PreviewAbsHolder;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 16:36
 */
public class PicpCore {

    private PicPreview picPreview;
    private PreviewListener previewListener;
    private PreviewActivity previewActivity;

    private LocalMedia curData;

    private PicpCore() {

    }

    private static class PicpCoreHolder {
        private static final PicpCore instance = new PicpCore();
    }

    public static PicpCore get() {
        return PicpCoreHolder.instance;
    }

    public void init(PicPreview picPreview) {
        this.picPreview = picPreview;
    }

    public ImageEngin imageEngin() {
        return picPreview.getImageEngin();
    }

    public ViewGroup titleBar() {
        return picPreview.getTitleBar();
    }

    public ViewGroup bottomBar() {
        return picPreview.getBottomBar();
    }

    public PreviewAbsHolder previewHolder(ViewGroup container,int type) {
        if (picPreview.getPreviewHolderListener() == null) {
            return null;
        }
        return picPreview.getPreviewHolderListener().newHolder(container,type);
    }

    public void onViewTap(){
        if (previewListener==null){
            return;
        }
        previewListener.onViewTap();
    }


    public void setPreviewListener(PreviewListener previewListener) {
        this.previewListener = previewListener;
    }

    public void setPreviewActivity(PreviewActivity previewActivity) {
        this.previewActivity = previewActivity;
        if (previewActivity == null) {
            picPreview = null;
            previewListener = null;
        }
    }

    public void pageFinish() {
        if (previewActivity == null) {
            return;
        }
        previewActivity.finish();
    }

    public void setTitle(String title) {
        picPreview.getTitleBar().setTitle(title);
    }


    public LocalMedia getCurData() {
        return curData;
    }

    public void setCurData(LocalMedia curData) {
        this.curData = curData;
    }
}
