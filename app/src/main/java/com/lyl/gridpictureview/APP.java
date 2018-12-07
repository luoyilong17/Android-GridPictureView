package com.lyl.gridpictureview;

import android.app.Application;

import com.lyl.gridPictureViewLib.GridPictureView;

/**
 * Created by hupei on 2018/12/7 16:07.
 */
public class APP extends Application {
    static {
        //设置图片加载代理
        GridPictureView.setGlobalLoaderStrategy(new GlideLoaderPictureStrategy());
    }
}
