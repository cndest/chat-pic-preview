package com.cndest.demo;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.ImageView;

import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.PermissionUtils;
import com.bumptech.glide.Glide;
import com.cndest.picpreview.ImageEngin;
import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.PreviewActivity;
import com.cndest.picpreview.bean.LocalMedia;

import java.io.File;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        PicpCore.get().init(new ImageEngin() {
            @Override
            public void load(Context context, ImageView imageView, Object url) {
                Glide.with(context)
                        .load(url)
                        .into(imageView);
            }
        });


        PermissionUtils.permission(PermissionConstants.STORAGE)
                .callback(new PermissionUtils.SimpleCallback() {
                    @Override
                    public void onGranted() {
                        startPreview();
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
        PreviewActivity.start(this, localMediaList);
    }
}