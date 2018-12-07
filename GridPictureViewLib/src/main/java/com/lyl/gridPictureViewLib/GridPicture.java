//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : GridPicture.java
//  @ Date : 2018/12/4
//  @ Author : hupei
//
//


package com.lyl.gridPictureViewLib;

/**
 * 图片实体
 */
public interface GridPicture {

    /**
     * 添加本地图片资源id
     *
     * @param resId
     */
    void addPicture(int resId);

    /**
     * 添加图片路径
     *
     * @param path
     */
    void addPicture(String path);

    /**
     * 删除指定position 图片
     *
     * @param position
     */
    void removePicture(int position);

}
