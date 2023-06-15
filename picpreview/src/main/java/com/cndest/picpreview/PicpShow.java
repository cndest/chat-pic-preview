package com.cndest.picpreview;

import android.content.Context;
import android.view.ViewGroup;

import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PicpBottomBar;
import com.cndest.picpreview.ui.PicpTitle;

import java.util.List;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/15 14:59
 */
public class PicpShow {
    private List<LocalMedia> showData;
    private PicpTitle titleBar;
    private PicpBottomBar bottomBar;
    private ImageEngin imageEngin;
    private PicpShow() {

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

    public void start(Context context) {
        //默认值
        if (titleBar==null){
            titleBar = new PicpTitle(context);
        }
        if (bottomBar==null){
            bottomBar = new PicpBottomBar(context);
        }
        PicpCore.get().init(this);
        PreviewActivity.start(context, showData);
    }

    public static class PicpBuilder {
        private List<LocalMedia> showData;
        private PicpTitle titleBar;
        private PicpBottomBar bottomBar;
        private ImageEngin imageEngin;

        public PicpBuilder setData(List<LocalMedia> showData) {
            this.showData = showData;
            return this;
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

        public PicpShow builder() {
            PicpShow picpShow = new PicpShow();
            picpShow.showData = showData;
            picpShow.titleBar = titleBar;
            picpShow.bottomBar = bottomBar;
            picpShow.imageEngin = imageEngin;
            return picpShow;
        }
    }
}
