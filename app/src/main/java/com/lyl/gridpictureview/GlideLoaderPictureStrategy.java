package com.lyl.gridpictureview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lyl.gridPictureViewLib.LoaderPictureStrategy;

/**
 * Created by hupei on 2018/12/7 15:25.
 */
public class GlideLoaderPictureStrategy implements LoaderPictureStrategy {
    private RequestOptions requestOptions = new RequestOptions();

    public GlideLoaderPictureStrategy() {
        //设置加载 参数
//        requestOptions.centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用硬盘缓存
//                .placeholder(0)
//                .override(Target.SIZE_ORIGINAL)            //显示原始图片大小
//                .error(0);//图片加载失败后，显示的图片
    }

    @Override
    public void request(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url).into(imageView);
    }
}
