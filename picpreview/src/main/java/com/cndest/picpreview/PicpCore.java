package com.cndest.picpreview;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 16:36
 */
public class PicpCore {

    private ImageEngin imageEngin;

    private PicpCore() {

    }

    static class PicpCoreHolder {
        private static final PicpCore instance = new PicpCore();
    }

    public static PicpCore get() {
        return PicpCoreHolder.instance;
    }

    public void init(ImageEngin imageEngin){
        this.imageEngin = imageEngin;
    }

    public ImageEngin getImageEngin() {
        return imageEngin;
    }
}
