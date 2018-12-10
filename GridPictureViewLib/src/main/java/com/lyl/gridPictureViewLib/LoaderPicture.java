package com.lyl.gridPictureViewLib;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by hupei on 2018/12/7 15:33.
 * 图片加载回调
 */
class LoaderPicture implements LoaderPictureStrategy {

    private LoaderPictureStrategy mStrategy;

    public void setLoaderStrategy(LoaderPictureStrategy strategy) {
        mStrategy = strategy;
    }

    @Override
    public void request(Context context, String url, ImageView imageView) {
        mStrategy.request(context, url, imageView);
    }
}
