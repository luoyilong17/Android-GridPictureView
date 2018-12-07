package com.lyl.gridPictureViewLib.options;


import android.support.v7.widget.RecyclerView;

import com.lyl.gridPictureViewLib.OnPictureClickListener;

/**
 * 显示布局参数
 *
 * @author luoyilong
 * @date on 2018/12/5
 **/
public class GPFrame {

    private int mRowCount = 5;//每行最多显示5列
    private int mMaxCount = 10;//最大显示数量
    //private int mHorizontalSpace = 0;//列间距px
    //private int mVerticalSpace = 100;//行间距px

    private int pictureWidth = 70;//图片宽度dp
    private int pictureHeight = 75;//图片高度dp
    private OnPictureClickListener mPictureClickListener;

    private RecyclerView.ItemDecoration mItemDecoration;


    public GPFrame() {
    }

    public int getMaxCount() {
        return mMaxCount;
    }

    public void setMaxCount(int mMaxCount) {
        this.mMaxCount = mMaxCount;
    }

    public void setItemDecoration(RecyclerView.ItemDecoration decor) {
        mItemDecoration = decor;
    }

    public RecyclerView.ItemDecoration getItemDecoration() {
        return mItemDecoration;
    }

    public OnPictureClickListener getOnPictureClickListener() {
        return mPictureClickListener;
    }

    public void setOnPictureClickListener(OnPictureClickListener pictureClickListener) {
        this.mPictureClickListener = pictureClickListener;
    }

    public int getRowCount() {
        return mRowCount;
    }

    public void setRowCount(int rowCount) {
        this.mRowCount = rowCount;
    }


    public int getPictureWidth() {
        return pictureWidth;
    }

    public void setPictureWidth(int pictureWidth) {
        this.pictureWidth = pictureWidth;
    }

    public int getPictureHeight() {
        return pictureHeight;
    }

    public void setPictureHeight(int pictureHeight) {
        this.pictureHeight = pictureHeight;
    }

}
