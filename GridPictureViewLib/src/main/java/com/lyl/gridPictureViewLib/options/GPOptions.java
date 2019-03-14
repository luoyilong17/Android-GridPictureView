package com.lyl.gridPictureViewLib.options;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;
import android.support.v7.widget.GridLayoutManager;

/**
 * 所有参数设置
 *
 * @author luoyilong
 **/
public class GPOptions {

    //总体参数
    private GPFrame mGPFrame;

    //新增 参数
    private GPAddPicture mGPAddPicture;

    //删除 参数
    private GPDeletePicture mGPDeletePicture;

    public GPOptions() {

    }

    public GPFrame getGPFrame() {
        return mGPFrame;
    }

    @NonNull
    @CheckResult
    public GPOptions setGPFrame(GPFrame frame) {
        this.mGPFrame = frame;
        return this;
    }

    public GPAddPicture getGPAddPicture() {
        return mGPAddPicture;
    }

    @NonNull
    @CheckResult
    public GPOptions setGPAddPicture(GPAddPicture addPicture) {
        this.mGPAddPicture = addPicture;
        return this;
    }

    public GPDeletePicture getGPDeletePicture() {
        return mGPDeletePicture;
    }

    @NonNull
    @CheckResult
    public GPOptions setGPDeletePicture(GPDeletePicture deletePicture) {
        this.mGPDeletePicture = deletePicture;
        return this;
    }


    /**
     * 检查 Options 初始值
     */
    public void checkInitOptions() {
        //设置FRAME 默认参数
        if (mGPFrame == null)
            mGPFrame = new GPFrame();

        //设置AddPicture 默认参数
        if (mGPAddPicture == null)
            mGPAddPicture = new GPAddPicture();

        //设置DeletePicture 默认参数
        if (mGPDeletePicture == null)
            mGPDeletePicture = new GPDeletePicture();
    }
}
