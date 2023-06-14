package com.cndest.picpreview.bean;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import com.cndest.picpreview.config.PictureMimeType;

import java.io.File;

/**
 * - @description:
 * - @author:  chezi008/chezi008@qq.com
 * - @date:  2023/6/14 10:22
 */
public class LocalMedia implements Parcelable {
    /**
     * original path
     */
    private String path;

    /**
     * video thumbnail path
     */
    private String thumbPath;
    /**
     * video duration
     */
    private long duration;

    /**
     * If the selected
     * # Internal use
     */
    private boolean checked;
    /**
     * The media resource type
     */
    private String mimeType;
    /**
     * file name
     */
    private String fileName;
    /**
     * media create time
     */
    private long cTime;


    public LocalMedia(){

    }
    protected LocalMedia(Parcel in) {
        path = in.readString();
        thumbPath = in.readString();
        duration = in.readLong();
        checked = in.readByte() != 0;
        mimeType = in.readString();
        fileName = in.readString();
        cTime = in.readLong();
    }

    public static final Creator<LocalMedia> CREATOR = new Creator<LocalMedia>() {
        @Override
        public LocalMedia createFromParcel(Parcel in) {
            return new LocalMedia(in);
        }

        @Override
        public LocalMedia[] newArray(int size) {
            return new LocalMedia[size];
        }
    };

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getThumbPath() {
        return thumbPath;
    }

    public void setThumbPath(String thumbPath) {
        this.thumbPath = thumbPath;
    }

    public long getDuration() {
        return duration;
    }

    public void setDuration(long duration) {
        this.duration = duration;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

    public String getMimeType() {
        return mimeType;
    }

    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public long getcTime() {
        return cTime;
    }

    public void setcTime(long cTime) {
        this.cTime = cTime;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(path);
        parcel.writeString(thumbPath);
        parcel.writeLong(duration);
        parcel.writeByte((byte) (checked ? 1 : 0));
        parcel.writeString(mimeType);
        parcel.writeString(fileName);
        parcel.writeLong(cTime);
    }


    public static LocalMedia fromFile(File file){
        LocalMedia localMedia = new LocalMedia();
        localMedia.fileName  = file.getName();
        localMedia.path = file.getPath();
        localMedia.mimeType = file.getPath().toLowerCase().endsWith(".jpg")? PictureMimeType.ofJPEG():PictureMimeType.ofMP4();
        return localMedia;
    }

    @Override
    public String toString() {
        return "LocalMedia{" +
                "path='" + path + '\'' +
                ", thumbPath='" + thumbPath + '\'' +
                ", duration=" + duration +
                ", checked=" + checked +
                ", mimeType='" + mimeType + '\'' +
                ", fileName='" + fileName + '\'' +
                ", cTime=" + cTime +
                '}';
    }
}
