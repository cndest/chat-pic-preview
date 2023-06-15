package com.cndest.picpreview;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;

import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.ui.PreviewAdapter;

import java.util.ArrayList;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 9:48
 */
public class PreviewFragment extends Fragment {

    private RecyclerView recyclerView;
    private LinearLayoutManager mLayoutManager;
    private PreviewAdapter previewAdapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.pp_fragment_preview, null);
        initView(view);
        return view;
    }

    private void initView(View view) {
        recyclerView = view.findViewById(R.id.recyclerView);
        mLayoutManager = new LinearLayoutManager(getContext(),LinearLayoutManager.HORIZONTAL,false);//创建线性布局管理器
        recyclerView.setLayoutManager(mLayoutManager);

        ArrayList<LocalMedia> data = getArguments().getParcelableArrayList("data");

        previewAdapter = new PreviewAdapter(data);
        recyclerView.setAdapter(previewAdapter);

        // 将SnapHelper attach 到RecyclerView
        PagerSnapHelper snapHelper = new PagerSnapHelper();
        snapHelper.attachToRecyclerView(recyclerView);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        previewAdapter.onDestroy();
    }
}
