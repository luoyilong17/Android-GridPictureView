package com.lyl.gridPictureViewLib.options;

import android.support.annotation.DrawableRes;

import com.lyl.gridPictureViewLib.R;
import com.lyl.gridPictureViewLib.OnAddClickListener;

/**
 * 新增图标 参数
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class GPAddPicture {

    /**
     * 是否显示新增按钮
     */
    private boolean isShowAdd=false;

    /**
     * 新增加按钮图标
     */
    private int mAddResource= R.mipmap.ic_photo_add;

    /**
     * 新增加事件
     */
    private OnAddClickListener mAddClickListener;

    public GPAddPicture() {
    }


    public boolean isShowAdd() {
        return isShowAdd;
    }

    public void setShowAdd(boolean showAdd) {
        isShowAdd = showAdd;
    }

    public int getAddResource() {
        return mAddResource;
    }

    public void setAddResource(@DrawableRes int mAddResource) {
        this.mAddResource = mAddResource;
    }

    public OnAddClickListener getOnAddClickListener() {
        return mAddClickListener;
    }

    public void setOnAddClickListener(OnAddClickListener onAddClickListener) {
        this.mAddClickListener = onAddClickListener;
    }
}
