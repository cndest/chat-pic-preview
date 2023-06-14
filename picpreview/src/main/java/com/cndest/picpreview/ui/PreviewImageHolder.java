package com.cndest.picpreview.ui;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.photoview.PhotoView;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 11:58
 */
class PreviewImageHolder extends PreviewAbsHolder {

    private PhotoView photoView;
    private TextView tvTest;

    public PreviewImageHolder(@NonNull View itemView) {
        super(itemView);
        photoView = itemView.findViewById(R.id.preview_image);
        tvTest = itemView.findViewById(R.id.tvTest);
    }

    @Override
    public void initView(LocalMedia localMedia,int position) {
//        Bitmap bitmap = BitmapFactory.decodeFile(localMedia.getPath());
//        photoView.setImageBitmap(bitmap);
        PicpCore.get().getImageEngin().load(photoView.getContext(),photoView,localMedia.getPath());

        tvTest.setText(position+"");
    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }


}
