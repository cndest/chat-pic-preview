package com.cndest.picpreview;

import android.view.ViewGroup;

import com.cndest.picpreview.bean.LocalMedia;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 16:36
 */
public class PicpCore {

    private PicpShow picpShow;
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

    public void init(PicpShow picpShow) {
        this.picpShow = picpShow;
    }

    public ImageEngin imageEngin() {
        return picpShow.getImageEngin();
    }

    public ViewGroup titleBar() {
        return picpShow.getTitleBar();
    }

    public ViewGroup bottomBar() {
        return picpShow.getBottomBar();
    }

    public PreviewListener getPreviewListener() {
        return previewListener;
    }

    public void setPreviewListener(PreviewListener previewListener) {
        this.previewListener = previewListener;
    }

    public void setPreviewActivity(PreviewActivity previewActivity) {
        this.previewActivity = previewActivity;
        if (previewActivity == null) {
            picpShow = null;
            previewListener = null;
        }
    }

    public void pageFinish() {
        if (previewActivity == null) {
            return;
        }
        previewActivity.finish();
    }

    public void setTitle(String title){
        picpShow.getTitleBar().setTitle(title);
    }


    public LocalMedia getCurData() {
        return curData;
    }

    public void setCurData(LocalMedia curData) {
        this.curData = curData;
    }
}
