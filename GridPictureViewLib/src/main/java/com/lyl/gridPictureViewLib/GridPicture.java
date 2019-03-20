package com.lyl.gridPictureViewLib;

import java.util.List;

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
     * 添加图片路径
     *
     * @param paths
     */
    void addPicture(List<String> paths);

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

    /**
     * 获取数据
     * @return
     */
    List<String> getData();
}
