package com.lyl.gridpictureview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lyl.gridPictureViewLib.LoaderPictureStrategy;

/**
 * Created by hupei on 2018/12/7 15:25.
 */
public class GlideLoaderPictureStrategy implements LoaderPictureStrategy {
    private RequestOptions requestOptions = new RequestOptions();

    public GlideLoaderPictureStrategy() {
        //设置加载 参数
        requestOptions.centerCrop()
                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用硬盘缓存
                .placeholder(R.mipmap.default_photo)      //占位图片
                .override(Target.SIZE_ORIGINAL)           //显示原始图片大小
                .error(R.mipmap.default_photo_failure);   //图片加载失败后，显示的图片
    }

    @Override
    public void request(Context context, String url, ImageView imageView) {
        Glide.with(context).load(url)
                .apply(requestOptions)
                .into(imageView);
    }
}
