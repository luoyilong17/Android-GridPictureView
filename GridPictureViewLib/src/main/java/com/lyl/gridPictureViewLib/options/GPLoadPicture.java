package com.lyl.gridPictureViewLib.options;

import android.support.annotation.DrawableRes;

import com.lyl.gridPictureViewLib.R;

/**
 * 加载
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class GPLoadPicture {

    private int mLoadErrorPicture = R.mipmap.default_photo_failure;//加载异常图片
    private int mDefaultPicture = R.mipmap.default_photo;//默认显示图片


    public int getLoadErrorPicture() {
        return mLoadErrorPicture;
    }

    public void setLoadErrorPicture(@DrawableRes int mLoadErrorPicture) {
        this.mLoadErrorPicture = mLoadErrorPicture;
    }

    public int getDefaultPicture() {
        return mDefaultPicture;
    }

    public void setDefaultPicture(@DrawableRes int mDefaultPicture) {
        this.mDefaultPicture = mDefaultPicture;
    }
}
