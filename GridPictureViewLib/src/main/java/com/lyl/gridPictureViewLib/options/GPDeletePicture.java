package com.lyl.gridPictureViewLib.options;

import android.support.annotation.DrawableRes;

import com.lyl.gridPictureViewLib.R;
import com.lyl.gridPictureViewLib.OnDeleteClickListener;

/**
 * 删除图标参数
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class GPDeletePicture {

    /**
     * 是否显示删除按钮
     * 默认不显示
     */
    private boolean isShowDelete = false;

    /**
     * 删除按钮图标
     */
    private int mDeleteResource= R.mipmap.ic_photo_del;

    /**
     * 删除事件
     */
    private OnDeleteClickListener mDeleteClickListener;


    public GPDeletePicture() {
    }

    public boolean isShowDelete() {
        return isShowDelete;
    }

    public void setShowDelete(boolean showDelete) {
        isShowDelete = showDelete;
    }

    public int getDeleteResource() {
        return mDeleteResource;
    }

    public void setDeleteResource(@DrawableRes int deleteResource) {
        this.mDeleteResource = deleteResource;
    }

    public OnDeleteClickListener getDeleteClickListener() {
        return mDeleteClickListener;
    }

    public void setDeleteClickListener(OnDeleteClickListener listener) {
        this.mDeleteClickListener = listener;
    }
}
