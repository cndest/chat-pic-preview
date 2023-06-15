package com.cndest.picpreview.ui;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.photoview.OnPhotoTapListener;
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

        photoView.setOnPhotoTapListener(new OnPhotoTapListener() {
            @Override
            public void onPhotoTap(ImageView view, float x, float y) {
                PicpCore.get().getPreviewListener().onViewTap();
            }
        });
    }

    @Override
    public void initView(LocalMedia localMedia,int position) {
//        Bitmap bitmap = BitmapFactory.decodeFile(localMedia.getPath());
//        photoView.setImageBitmap(bitmap);
        PicpCore.get().imageEngin().load(photoView.getContext(),photoView,localMedia.getPath());

        tvTest.setText(position+"");
    }

    @Override
    public void onViewAttachedToWindow() {

    }

    @Override
    public void onViewDetachedFromWindow() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void onStop() {

    }
}
