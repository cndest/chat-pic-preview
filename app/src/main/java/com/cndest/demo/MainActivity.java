package com.cndest.demo;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.Glide;
import com.cndest.picpreview.ImageEngin;
import com.cndest.picpreview.PicpConstant;
import com.cndest.picpreview.PicpShow;
import com.cndest.picpreview.PreviewActivity;
import com.cndest.picpreview.PreviewHolderListener;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PreviewAbsHolder;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btnPreview).setOnClickListener(v->{
            startPreview();
        });

        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                    }

                    @Override
                    public void onDenied() {

                    }
                })
                .request();


    }

    private void startPreview() {
        String dcimPath = Environment.getExternalStorageDirectory().getAbsolutePath() + "/DCIM/Camera/";
        Log.d(TAG, "onCreate: dcimPath:" + dcimPath);
        File file = new File(dcimPath);
        File[] listFiles = file.listFiles();
        Log.d(TAG, "onCreate: listFiles:" + listFiles);

        List<LocalMedia> localMediaList = Arrays.stream(listFiles).map(file1 -> LocalMedia.fromFile(file1)).collect(Collectors.toList());

        Log.d(TAG, "onCreate: localMediaList:" + localMediaList);


        PicpShow builder = new PicpShow.PicpBuilder()
                .setData(localMediaList)
                .setImageEngin(new ImageEngin() {
                    @Override
                    public void load(Context context, ImageView imageView, Object url) {
                        Glide.with(context)
                                .load(url)
                                .into(imageView);
                    }
                }).setPreviewHolderListener(new PreviewHolderListener() {
                    @Override
                    public PreviewAbsHolder newHolder(ViewGroup container, int viewType) {
                        if (viewType== PicpConstant.MimeType_Video){
                            View inflate = LayoutInflater.from(container.getContext()).inflate(R.layout.custom_item_preview_video, container,false);
                            return new CustomVideoHolder(inflate);
                        }
                        return null;
                    }
                }).builder();
        builder.start(this);
    }
}