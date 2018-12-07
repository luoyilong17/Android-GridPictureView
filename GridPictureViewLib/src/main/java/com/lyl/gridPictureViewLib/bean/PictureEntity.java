package com.lyl.gridPictureViewLib.bean;

import java.io.File;

/**
 * 图片实体
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class PictureEntity {

    private String pictureType;//图片类型 pictureFile\picturePath\pictureResource
    private File pictureFile;//文件
    private String picturePath;//路径
    private int pictureResource;//资源
    private boolean isAdd=false;//是否为新增图标

    public String getPictureType() {
        return pictureType;
    }

    public void setPictureType(String pictureType) {
        this.pictureType = pictureType;
    }

    public File getPictureFile() {
        return pictureFile;
    }

    public void setPictureFile(File pictureFile) {
        this.pictureFile = pictureFile;
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
