package com.cndest.picpreview;

import android.content.Context;

import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PicpBottomBar;
import com.cndest.picpreview.ui.PicpTitle;

import java.util.ArrayList;
import java.util.List;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/15 14:59
 */
public class PicPreview {
    private Context context;
    private List<LocalMedia> showData;
    private int curPosition;
    private PicpTitle titleBar;
    private PicpBottomBar bottomBar;
    private ImageEngin imageEngin;

    private PreviewHolder previewHolder;

    private PicPreview(Context context) {
        this.context = context;
    }

    public static PicPreview.PicpBuilder create(Context context) {
        return new PicpBuilder(context);
    }


    public List<LocalMedia> getShowData() {
        return showData;
    }

    public PicpTitle getTitleBar() {
        return titleBar;
    }

    public PicpBottomBar getBottomBar() {
        return bottomBar;
    }

    public ImageEngin getImageEngin() {
        return imageEngin;
    }

    public PreviewHolder getPreviewHolderListener() {
        return previewHolder;
    }

    public void start() {
        //默认值
        if (titleBar==null){
            titleBar = new PicpTitle(context);
        }
        if (bottomBar==null){
            bottomBar = new PicpBottomBar(context);
        }
        PicpCore.get().init(this);
        PreviewActivity.start(context, curPosition, showData);
    }

    public static class PicpBuilder {
        private Context context;
        private PicpTitle titleBar;
        private PicpBottomBar bottomBar;
        private ImageEngin imageEngin;
        private PreviewHolder previewHolder;

        public PicpBuilder(Context context) {
            this.context = context;
        }


        public PicpBuilder setImageEngin(ImageEngin imageEngin) {
            this.imageEngin = imageEngin;
            return this;
        }

        public PicpBuilder setTitleBar(PicpTitle titleBar) {
            this.titleBar = titleBar;
            return this;
        }

        public PicpBuilder setBottomBar(PicpBottomBar bottomBar) {
            this.bottomBar = bottomBar;
            return this;
        }

        public PicpBuilder setPreviewHolder(PreviewHolder previewHolder) {
            this.previewHolder = previewHolder;
            return this;
        }

        public void start(int currentPosition, List<LocalMedia> list) {
            PicPreview picPreview = new PicPreview(context);
            picPreview.showData = list;
            picPreview.curPosition = currentPosition;
            picPreview.titleBar = titleBar;
            picPreview.bottomBar = bottomBar;
            picPreview.imageEngin = imageEngin;
            picPreview.previewHolder = previewHolder;
            picPreview.start();
        }

    }
}
