package com.cndest.picpreview;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentTransaction;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import com.cndest.picpreview.bean.LocalMedia;

import java.util.ArrayList;
import java.util.List;

public class PreviewActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.pp_activity_preview);

        PreviewFragment previewFragment = new PreviewFragment();
        previewFragment.setArguments(getIntent().getExtras());
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        fragmentTransaction.replace(R.id.flParent, previewFragment);
        fragmentTransaction.commit();
    }

    public static void start(Context context, List<LocalMedia> localMediaList) {
        Intent starter = new Intent(context, PreviewActivity.class);
        starter.putParcelableArrayListExtra("data", (ArrayList<? extends Parcelable>) localMediaList);
        context.startActivity(starter);
    }
}