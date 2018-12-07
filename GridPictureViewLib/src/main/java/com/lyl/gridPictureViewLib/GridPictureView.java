//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : GridPictureView.java
//  @ Date : 2018/12/4
//  @ Author : hupei
//
//

package com.lyl.gridPictureViewLib;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.lyl.gridPictureViewLib.options.GPAddPicture;
import com.lyl.gridPictureViewLib.options.GPFrame;
import com.lyl.gridPictureViewLib.options.GPOptions;

import java.io.File;
import java.util.ArrayList;

/** */
public class GridPictureView extends RecyclerView implements GridPicture {

    /** */
    private Context mContext;
    private GPOptions mGPOptions;
    private GridPictureAdapter mGridPictureAdapter;

    public GridPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    public void addPicture(File file) {
        if (mGridPictureAdapter == null)
            return;

//        this.mGridPictureAdapter.addData(file);
    }

    @Override
    public void addPicture(int resId) {
        if (mGridPictureAdapter == null)
            return;
        PictureEntity pictureEntity = new PictureEntity(resId, false);
        mGridPictureAdapter.addData(pictureEntity);

    }

    @Override
    public void addPicture(String path) {
        if (mGridPictureAdapter == null)
            return;
        PictureEntity pictureEntity = new PictureEntity(path, false);
        mGridPictureAdapter.addData(pictureEntity);
    }

    @Override
    public void removePicture(int position) {
        if (this.mGridPictureAdapter == null)
            return;

        mGridPictureAdapter.removeData(position);
    }

    public GPOptions getGPOptions() {
        return mGPOptions;
    }

    public void setGPOptions(@NonNull GPOptions options) {
        if (options == null)
            return;
        mGPOptions = options;
        //检查Options初始化值
        mGPOptions.checkInitOptions();

        //设置视图列数及下划线
        setViewSpanCountOrItemDecoration(mGPOptions);

        //初始化适配器
        initAdapter(mGPOptions);

        //检查是否显示新增图标
        checkAddPicture(mGPOptions);
    }


    /**
     * 设置行显示最大列数
     */
    private void setViewSpanCountOrItemDecoration(GPOptions gPOptions) {
        //设置FRAME 默认参数
        GPFrame gpFrame = gPOptions.getGPFrame();
        if (gpFrame == null)
            gpFrame = new GPFrame();

        if (gPOptions.getGPFrame().getItemDecoration() != null)
            super.addItemDecoration(gpFrame.getItemDecoration());

        super.setLayoutManager(new GridLayoutManager(mContext, gpFrame.getRowCount()));
    }


    /**
     * 初始化适配器
     *
     * @param options 参数
     */
    private void initAdapter(GPOptions options) {
        //适配器
        if (mGridPictureAdapter == null) {
            mGridPictureAdapter = new GridPictureAdapter(mContext, new ArrayList<PictureEntity>());
            super.setAdapter(mGridPictureAdapter);
        }
        mGridPictureAdapter.setGPOptions(options);
    }


    /**
     * 根据options 判断是否显新增图标
     *
     * @param options
     */
    private void checkAddPicture(GPOptions options) {

        //设置FRAME 默认参数
        GPFrame gpFrame = options.getGPFrame();
        if (gpFrame == null)
            gpFrame = new GPFrame();

        int maxCount = gpFrame.getMaxCount();
        int dataSize = 0;
        if (mGridPictureAdapter != null)
            dataSize = mGridPictureAdapter.getData().size();

        GPAddPicture gpAddPicture = options.getGPAddPicture();
        if (gpAddPicture == null)
            gpAddPicture = new GPAddPicture();

        if (gpAddPicture.isShowAdd()) {//显示新增按钮
            PictureEntity pictureEntity = new PictureEntity(gpAddPicture.getAddResource(), true);
            if (dataSize < maxCount) {
                if (dataSize > 0) {
                    PictureEntity lasPictureEntity = mGridPictureAdapter.getData().get(dataSize - 1);
                    if (!lasPictureEntity.isAdd())//最后一个添加为 新增
                        mGridPictureAdapter.addData(dataSize, pictureEntity);
                } else {//等于0,第一个添加为 新增
                    mGridPictureAdapter.addData(0, pictureEntity);
                }
            }
        } else {//不显示新增加按钮
            if (dataSize > 0) {
                PictureEntity lasPictureEntity = mGridPictureAdapter.getData().get(dataSize - 1);
                if (lasPictureEntity.isAdd())//最后一个添加为 新增
                    removePicture(dataSize - 1);
            }
        }

        //为了使设置参数 生效需要刷新适配器
        mGridPictureAdapter.notifyDataSetChanged();
    }


    @Override
    public void setAdapter(Adapter adapter) {

    }

    @Override
    public void setLayoutManager(LayoutManager layout) {

    }

    @Override
    public void addItemDecoration(ItemDecoration decor) {

    }

    private static LoaderPictureStrategy mStrategy;
    public static void setLoaderStrategy(LoaderPictureStrategy strategy) {
        mStrategy = strategy;
    }

    public static LoaderPictureStrategy getLoaderPictureStrategy() {
        return mStrategy;
    }
}
