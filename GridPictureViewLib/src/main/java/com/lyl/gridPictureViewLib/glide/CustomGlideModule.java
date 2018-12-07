package com.lyl.gridPictureViewLib.glide;

import android.content.Context;
import android.os.Environment;

import com.bumptech.glide.GlideBuilder;
import com.bumptech.glide.annotation.GlideModule;
import com.bumptech.glide.load.DecodeFormat;
import com.bumptech.glide.load.engine.cache.DiskLruCacheFactory;
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.request.target.ViewTarget;
import com.lyl.gridPictureViewLib.R;

import java.io.File;


/**
 * Created by luoyilong on 2017/5/4.
 */
@GlideModule
public class CustomGlideModule extends AppGlideModule {
    public static final int DISK_CACHE_SIZE = 500 * 1024 * 1024;

    @Override
    public boolean isManifestParsingEnabled() {
        return false;
    }

    @Override
    public void applyOptions(Context context, GlideBuilder builder) {
        ViewTarget.setTagId(R.string.app_name);

        //设置图片的显示格式ARGB_8888(指图片大小为32bit)
        builder.setDecodeFormat(DecodeFormat.PREFER_ARGB_8888);
        //设置磁盘缓存目录（和创建的缓存目录相同）
        File result = new File(Environment.getExternalStorageDirectory(),
                "Android/data/" + context.getPackageName() + "/cache/GlideCache");

        String diskCacheFolder = result.getPath();
        //默认硬盘缓存大小都是250M,这里改为500
        builder.setDiskCache(new DiskLruCacheFactory(diskCacheFolder, DISK_CACHE_SIZE));
    }
}
