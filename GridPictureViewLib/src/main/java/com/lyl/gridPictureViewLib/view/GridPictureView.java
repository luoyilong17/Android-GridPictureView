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

package com.lyl.gridPictureViewLib.view;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;

import com.lyl.gridPictureViewLib.R;
import com.lyl.gridPictureViewLib.adapter.GridPictureAdapter;
import com.lyl.gridPictureViewLib.bean.PictureEntity;
import com.lyl.gridPictureViewLib.options.GPAddPicture;
import com.lyl.gridPictureViewLib.options.GPDeletePicture;
import com.lyl.gridPictureViewLib.options.GPFrame;
import com.lyl.gridPictureViewLib.options.GPOptions;
import com.yqritc.recyclerviewflexibledivider.HorizontalDividerItemDecoration;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/** */
public class GridPictureView extends RecyclerView implements GridPicture {

    /** */
    private Context mContext;
    private GPOptions mGPOptions;
    private GridPictureAdapter mGridPictureAdapter;
//    private List<T> data=new ArrayList<>();

    public GridPictureView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        mContext = context;
    }


    @Override
    public void addPicture(File file) {
        if (this.mGridPictureAdapter == null)
            return;

//        this.mGridPictureAdapter.addData(file);
    }

    @Override
    public void addPicture(int resId) {
        if (this.mGridPictureAdapter == null)
            return;
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPictureType("pictureResource");
        pictureEntity.setPictureResource(resId);
        this.mGridPictureAdapter.addData(pictureEntity);

    }

    @Override
    public void addPicture(String path) {
        if (this.mGridPictureAdapter == null)
            return;
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPictureType("picturePath");
        pictureEntity.setPicturePath(path);
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
        this.mGPOptions = options;
        initOptions();
    }


    /**
     * 初始化Options
     */
    private void initOptions() {

        //设置FRAME 默认参数
        GPFrame gpFrame = mGPOptions.getGPFrame();
        if (gpFrame == null)
            gpFrame = new GPFrame();
        if (gpFrame.getLayoutManager() == null)
            gpFrame.setLayoutManager(new GridLayoutManager(mContext, 4));
        if (gpFrame.getItemDecoration() == null)
            gpFrame.setItemDecoration(new HorizontalDividerItemDecoration.Builder(mContext)
                    .showLastDivider().size(1).build());
        super.setLayoutManager(gpFrame.getLayoutManager());
//        super.addItemDecoration(gpFrame.getItemDecoration());

        mGPOptions.setGPFrame(gpFrame);

        //设置AddPicture 默认参数
        GPAddPicture gpAddPicture = mGPOptions.getGPAddPicture();
        if (gpAddPicture == null)
            gpAddPicture = new GPAddPicture();
        if (gpAddPicture.getAddResource() == 0)
            gpAddPicture.setAddResource(R.mipmap.ic_photo_add);
        mGPOptions.setGPAddPicture(gpAddPicture);

        //设置DeletePicture 默认参数
        GPDeletePicture gpDeletePicture = mGPOptions.getGPDeletePicture();
        if (gpDeletePicture == null)
            gpDeletePicture = new GPDeletePicture();
        if (gpDeletePicture.getDeleteResource() == 0)
            gpDeletePicture.setDeleteResource(R.mipmap.ic_photo_del);
        mGPOptions.setGPDeletePicture(gpDeletePicture);


        //适配器
        if (mGridPictureAdapter == null) {
            mGridPictureAdapter = new GridPictureAdapter(mContext, new ArrayList<PictureEntity>());
            super.setAdapter(mGridPictureAdapter);
        }
        mGridPictureAdapter.setGPOptions(this.mGPOptions);

        int maxCount = gpFrame.getMaxCount();
        int size = mGridPictureAdapter.getData().size();

        if (gpAddPicture.isShowAdd()) {//显示新增按钮
            PictureEntity pictureEntity = getAddPictureEntity(gpAddPicture.getAddResource());
            if (size < maxCount) {
                if (size > 0) {
                    PictureEntity lasPictureEntity = mGridPictureAdapter.getData().get(size - 1);
                    if (!lasPictureEntity.isAdd())//最后一个添加为 新增
                        mGridPictureAdapter.addData(size, pictureEntity);
                } else {//等于0,第一个添加为 新增
                    mGridPictureAdapter.addData(0, pictureEntity);
                }
            }

        } else {//不显示新增加按钮
            if (size > 0) {
                PictureEntity lasPictureEntity = mGridPictureAdapter.getData().get(size - 1);
                if (lasPictureEntity.isAdd())//最后一个添加为 新增
                    removePicture(size - 1);
            }

        }

        //为了使设置参数 生效需要刷新适配器
        mGridPictureAdapter.notifyDataSetChanged();
    }

    //实例一个 新增 图标实体
    private PictureEntity getAddPictureEntity(int resId) {
        PictureEntity pictureEntity = new PictureEntity();
        pictureEntity.setPictureResource(resId);
        pictureEntity.setPictureType("pictureResource");
        pictureEntity.setAdd(true);

        return pictureEntity;
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
}
