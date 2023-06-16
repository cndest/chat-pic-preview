package com.cndest.picpreview.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.core.content.FileProvider;

import com.cndest.picpreview.OnCallbackListener;
import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.R;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.config.PictureMimeType;
import com.cndest.picpreview.utils.DownloadFileUtils;
import com.cndest.picpreview.utils.PictureMediaScannerConnection;
import com.cndest.picpreview.utils.SdkVersionUtils;

import java.io.File;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 17:24
 */
public class PicpBottomBar extends LinearLayout {

    protected ImageView ivDownload,ivShare;

    //动画
    protected TranslateAnimation mShowActionBottom;
    protected TranslateAnimation mHiddenActionBottom;

    public PicpBottomBar(Context context) {
        this(context, null);
    }

    public PicpBottomBar(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.pp_view_bottom, this, true);
        initAnimation();
        initView();
    }

    protected void initView() {
        ivDownload = findViewById(R.id.ivDownload);
        ivShare = findViewById(R.id.ivShare);



        ivDownload.setOnClickListener(v->{
            LocalMedia media = PicpCore.get().getCurData();
            DownloadFileUtils.saveLocalFile(getContext(), media.getPath(), media.getMimeType(), new OnCallbackListener<String>() {
                @Override
                public void onCall(String realPath) {
                    if (TextUtils.isEmpty(realPath)) {
                        String errorMsg;
                        if (PictureMimeType.isHasAudio(media.getMimeType())) {
                            errorMsg = "音频保存失败";
                        } else if (PictureMimeType.isHasVideo(media.getMimeType())) {
                            errorMsg = "视频保存时报";
                        } else {
                            errorMsg = "图片保存时报";
                        }
                        Toast.makeText(getContext(), errorMsg, Toast.LENGTH_SHORT).show();
                    } else {
                        new PictureMediaScannerConnection(getContext(), realPath);
                        Toast.makeText(getContext(), "保存成功", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        });

        ivShare.setOnClickListener(v -> {
            LocalMedia media = PicpCore.get().getCurData();
            Uri imageUri;
            if (SdkVersionUtils.isMaxN()){
                imageUri = FileProvider.getUriForFile(getContext(), getContext().getPackageName() + ".fileprovider", new File(media.getPath()));
            }else {
                imageUri = Uri.fromFile(new File(media.getPath()));
            }
            Intent intent = new Intent(Intent.ACTION_SEND);
            intent.setType(media.getMimeType());
            intent.putExtra(Intent.EXTRA_STREAM, imageUri);
            getContext().startActivity(Intent.createChooser(intent, "分享"));

        });
    }

    /**
     * 初始化动画
     */
    protected void initAnimation() {
        //显示
        mShowActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f, Animation.RELATIVE_TO_SELF, 0.0f);
        mShowActionBottom.setDuration(300);
        //隐藏
        mHiddenActionBottom = new TranslateAnimation(Animation.RELATIVE_TO_SELF,
                0.0f, Animation.RELATIVE_TO_SELF, 0.0f,
                Animation.RELATIVE_TO_SELF, 0.0f, Animation.RELATIVE_TO_SELF,
                1.0f);
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
