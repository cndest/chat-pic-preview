package com.cndest.picpreview;

import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PreviewAdapter;
import com.cndest.picpreview.utils.FileDirMap;

import java.util.ArrayList;
import java.util.List;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 9:48
 */
public class PreviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private PreviewAdapter previewAdapter;
    private ViewGroup picpTitleBar;
    private ViewGroup picpBottomBar;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pp_fragment_preview, container, false);
        FileDirMap.init(view.getContext());
        initView(view);
        FrameLayout flParent = view.findViewById(R.id.flParent);
        //add title
        addTitleView(flParent);
        //add bottom
        addBottomView(flParent);
        return view;
    }


    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);//创建线性布局管理器
        recyclerView.setLayoutManager(mLayoutManager);

        ArrayList<LocalMedia> data = getArguments().getParcelableArrayList("data");

        previewAdapter = newAdapter(data);
        recyclerView.setAdapter(previewAdapter);


        // 将SnapHelper attach 到RecyclerView
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);

        int position = getArguments().getInt("position");
        mLayoutManager.scrollToPosition(position);
        PicpCore.get().setPreviewListener(new PreviewListener() {
            @Override
            public void onViewTap() {
                //判断显示还是隐藏bottom
                int visibility = picpBottomBar.getVisibility();
                picpBottomBar.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);
                picpTitleBar.setVisibility(visibility == View.VISIBLE ? View.GONE : View.VISIBLE);

            }
        });
    }

    protected void addTitleView(ViewGroup flParent) {

        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.TOP;

        picpTitleBar = PicpCore.get().titleBar();

        flParent.addView(picpTitleBar, layoutParams);
    }

    protected void addBottomView(FrameLayout flParent) {
        FrameLayout.LayoutParams layoutParams = new FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT);
        layoutParams.gravity = Gravity.BOTTOM;

        picpBottomBar = PicpCore.get().bottomBar();

        flParent.addView(picpBottomBar, layoutParams);
    }

    protected PreviewAdapter newAdapter(List<LocalMedia> data) {
        return new PreviewAdapter(data);
    }


    @Override
    public void onStop() {
        super.onStop();
        previewAdapter.onStop();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        previewAdapter.onDestroy();
    }


}
