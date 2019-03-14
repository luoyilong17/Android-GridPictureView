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

    /**
     * 图片加载
     * @param strategy
     */
    void setLoaderStrategy(LoaderPictureStrategy strategy);

}
