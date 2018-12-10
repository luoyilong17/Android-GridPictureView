package com.lyl.gridPictureViewLib;

import android.content.Context;
import android.widget.ImageView;

/**
 * Created by hupei on 2018/12/7 15:23.
 * 图片加载回调接口
 */
public interface LoaderPictureStrategy {

    void request(Context context, String url, ImageView imageView);

}
