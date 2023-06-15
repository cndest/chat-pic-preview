package com.cndest.picpreview.ui;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.cndest.picpreview.PicpCore;
import com.cndest.picpreview.bean.LocalMedia;
import com.cndest.picpreview.PicpConstant;
import com.cndest.picpreview.R;
import com.cndest.picpreview.config.PictureMimeType;

import java.util.List;
import java.util.Locale;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 11:55
 */
public class PreviewAdapter extends RecyclerView.Adapter<PreviewAbsHolder> {

    private static final String TAG = "PreviewAdapter";
    private List<LocalMedia> mData;
    private PreviewAbsHolder curHold;

    public PreviewAdapter(List<LocalMedia> mData) {
        this.mData = mData;
    }

    public List<LocalMedia> getData() {
        return mData;
    }

    @NonNull
    @Override
    public PreviewAbsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == PicpConstant.MimeType_Image) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pp_item_preview_image, parent, false);
            return new PreviewImageHolder(view);
        }
        if (viewType == PicpConstant.MimeType_Video) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.pp_item_preview_video, parent, false);
            return new PreviewVideoHolder(view);
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull PreviewAbsHolder holder, int position) {
        holder.initView(mData.get(position), position);
    }

    @Override
    public int getItemCount() {
        return mData.size();
    }

    @Override
    public int getItemViewType(int position) {
        LocalMedia localMedia = mData.get(position);
        int itemType = -1;
        if (PictureMimeType.isHasImage(localMedia.getMimeType())) {
            itemType = PicpConstant.MimeType_Image;
        }
        if (PictureMimeType.isHasVideo(localMedia.getMimeType())) {
            itemType = PicpConstant.MimeType_Video;
        }
        return itemType;
    }


    @Override
    public void onViewAttachedToWindow(@NonNull PreviewAbsHolder holder) {
        super.onViewAttachedToWindow(holder);
        holder.onViewAttachedToWindow();
        this.curHold = holder;
        Log.d(TAG, "onViewAttachedToWindow: position:" + holder.getLayoutPosition());
        //设置标题
        int position = holder.getLayoutPosition();
        String title = String.format(Locale.CHINA, "%d/%d", position + 1, mData.size());
        PicpCore.get().setTitle(title);
        //设置当前数据
        PicpCore.get().setCurData(mData.get(position));
    }

    @Override
    public void onViewDetachedFromWindow(@NonNull PreviewAbsHolder holder) {
        super.onViewDetachedFromWindow(holder);
        holder.onViewDetachedFromWindow();
    }

    public void onStop() {
        curHold.onStop();
    }

    public void onDestroy() {
        curHold.onDestroy();
    }
}
