package com.lyl.gridpictureview;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.lyl.gridPictureViewLib.LoaderPictureStrategy;

/**
 * Created by hupei on 2018/12/7 15:25.
 */
public class GlideLoaderPictureStrategy implements LoaderPictureStrategy<RequestOptions> {

    @Override
    public void request(Context context, String url, ImageView imageView, RequestOptions options) {
        if (options == null) options = new RequestOptions();
        Glide.with(context).load(url).apply(options).into(imageView);
    }
}
