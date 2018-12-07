package com.lyl.gridPictureViewLib;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;

import java.io.File;

/**
 * 图片实体
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
class PictureEntity {
    public static final String PICTURE_TYPE_PATH = "picturePath";
    public static final String PICTURE_TYPE_RESOURCE = "pictureResource";

    private String pictureType;//图片类型 pictureFile\picturePath\pictureResource
    private String picturePath;//路径
    private int pictureResource;//资源
    private boolean isAdd = false;//标记是否为新增图标


    /**
     * 创建PictureEntity 实体
     *
     * @param picturePath
     * @param isAdd       标记是否为新增按钮图片
     */
    public PictureEntity(String picturePath, boolean isAdd) {
        this.picturePath = picturePath;
        this.pictureType = PICTURE_TYPE_PATH;
        this.isAdd = isAdd;
    }

    /**
     * 创建PictureEntity 实体
     *
     * @param pictureResource
     * @param isAdd           标记是否为新增按钮图片
     */
    public PictureEntity(int pictureResource, boolean isAdd) {
        this.pictureResource = pictureResource;
        this.pictureType = PICTURE_TYPE_RESOURCE;
        this.isAdd = isAdd;
    }


    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }

    public int getPictureResource() {
        return pictureResource;
    }

    public void setPictureResource(int pictureResource) {
        this.pictureResource = pictureResource;
    }

    public boolean isAdd() {
        return isAdd;
    }

    public void setAdd(boolean add) {
        isAdd = add;
    }
}
