package com.lyl.gridPictureViewLib.options;

import android.support.annotation.CheckResult;
import android.support.annotation.NonNull;

/**
 * 所有参数设置
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class GPOptions {

    //总体参数
    private GPFrame mGPFrame;

    //新增 参数
    private GPAddPicture mGPAddPicture;

    //删除 参数
    private GPDeletePicture mGPDeletePicture;

    //加载 参数
    private GPLoadPicture mGPLoadPicture;


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

    public GPLoadPicture getGPLoadPicture() {
        return mGPLoadPicture;
    }

    @NonNull
    @CheckResult
    public GPOptions setGPLoadPicture(GPLoadPicture loadPicture) {
        this.mGPLoadPicture = loadPicture;
        return this;
    }
}
