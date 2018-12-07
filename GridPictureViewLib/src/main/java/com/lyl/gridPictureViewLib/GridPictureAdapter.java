
package com.lyl.gridPictureViewLib;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.lyl.gridPictureViewLib.glide.GlideApp;
import com.lyl.gridPictureViewLib.options.GPAddPicture;
import com.lyl.gridPictureViewLib.options.GPDeletePicture;
import com.lyl.gridPictureViewLib.options.GPFrame;
import com.lyl.gridPictureViewLib.options.GPLoadPicture;
import com.lyl.gridPictureViewLib.options.GPOptions;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 图片展示适配器
 *
 * @ Date 2018-12-04
 */
 class GridPictureAdapter extends RecyclerView.Adapter<GridPictureAdapter.ViewHolder> {

    private Context context;
    private List<PictureEntity> data;
    private GPOptions mGPOptions;
    private RequestOptions requestOptions = new RequestOptions();

    public GridPictureAdapter(Context context, List<PictureEntity> entityList) {
        this.context = context;
        if (entityList == null)
            entityList = new ArrayList<>();
        this.data = entityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_grid_picture_adapter, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull GridPictureAdapter.ViewHolder holder, final int position) {
        final PictureEntity pictureEntity = data.get(position);
        final GPDeletePicture gpDeletePicture = mGPOptions.getGPDeletePicture();
        final GPAddPicture gpAddPicture = mGPOptions.getGPAddPicture();
        final GPFrame gpFrame = mGPOptions.getGPFrame();
        GPLoadPicture gpLoadPicture = mGPOptions.getGPLoadPicture();
        if (gpLoadPicture == null) {
            gpLoadPicture = new GPLoadPicture();
            mGPOptions.setGPLoadPicture(gpLoadPicture);
        }

        if (gpDeletePicture == null || !gpDeletePicture.isShowDelete() || pictureEntity.isAdd()) {
            holder.mImg_clear.setVisibility(View.GONE);
        } else {
            holder.mImg_clear.setVisibility(View.VISIBLE);
            holder.mImg_clear.setImageResource(gpDeletePicture.getDeleteResource());
        }


        String pictureType = pictureEntity.getPictureType();
        if (TextUtils.isEmpty(pictureType))
            return;

        if ("pictureFile".equalsIgnoreCase(pictureType)) {//图片文件

        } else if ("picturePath".equalsIgnoreCase(pictureType)) {//图片路径

            String picturePath = pictureEntity.getPicturePath();
            if (picturePath.startsWith("http://")) { //网络照片

                GlideApp.with(context).load(TextUtils.isEmpty(picturePath) ? gpLoadPicture.getDefaultPicture() : picturePath)
                        .apply(requestOptions)
                        .into(holder.mImg_content);
            } else { //本地照片
                GlideApp.with(context).load(TextUtils.isEmpty(picturePath) ? gpLoadPicture.getDefaultPicture() : Uri.fromFile(new File(picturePath)))
                        .apply(requestOptions)

                        .into(holder.mImg_content);
            }

        } else if ("pictureResource".equalsIgnoreCase(pictureType)) {//本地资源图片
            GlideApp.with(context).load(pictureEntity.getPictureResource())
                    .apply(requestOptions)
                    .into(holder.mImg_content);
        }

        //图片点击事件
        holder.mImg_content.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (pictureEntity.isAdd()) {//新增图标 点击事件
                    if (gpAddPicture != null && gpAddPicture.getOnAddClickListener() != null) {
                        gpAddPicture.getOnAddClickListener().onAddClick(view, position);
                    }
                } else {//非新增图标 点击事件
                    if (gpFrame != null && gpFrame.getOnPictureClickListener() != null) {
                        gpFrame.getOnPictureClickListener().onPictureClick(view, position);
                    }
                }
            }
        });

        //删除事件
        holder.mImg_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gpDeletePicture != null && gpDeletePicture.getDeleteClickListener() != null) {
                    gpDeletePicture.getDeleteClickListener().onDeleteClick(view, position);
                }
            }
        });


    }

    public void addData(PictureEntity entity) {
        addData(0, entity);
    }

    public List<PictureEntity> getData() {
        return data;
    }

    public void addData(int position, PictureEntity t) {
        if (mGPOptions == null)
            return;

        GPFrame gpFrame = mGPOptions.getGPFrame();
        int maxCount = gpFrame.getMaxCount();
        int size = data.size();
        if (maxCount == 0) {//最大值为0
            return;
        } else if (size < maxCount) {//小于最大值
            data.add(position, t);
            notifyDataSetChanged();
        } else {//等于最大值
            PictureEntity pictureEntity = data.get(size - 1);
            if (pictureEntity.isAdd()) {//去掉最后一个 为新增 图标，再数据设置进去
                data.remove(size - 1);
                data.add(position, t);
                notifyDataSetChanged();
            }

        }
    }


    /**
     * 指定索引删除数据
     *
     * @param position
     */
    public void removeData(int position) {
        if (mGPOptions == null)
            return;

        if (position < 0 || position >= data.size()) //判断要删除的索引是否正确
            return;

        //删除数据
        data.remove(position);
        notifyDataSetChanged();

        boolean showAdd = mGPOptions.getGPAddPicture().isShowAdd();
        if (!showAdd)//参数中 配置不显示新增图标
            return;


        //新数据集大小
        int newDataSize = data.size();

        boolean lasIsAddShow = false;//最后一个 图标是否为新增
        if (newDataSize > 0) {
            PictureEntity pictureEntity = data.get(newDataSize - 1);
            lasIsAddShow = pictureEntity.isAdd();
        }

        if (!lasIsAddShow) {//最后一个非新增图标 并且 需要显示新增图标
            PictureEntity pictureEntity = new PictureEntity();
            pictureEntity.setPictureResource(mGPOptions.getGPAddPicture().getAddResource());
            pictureEntity.setPictureType("pictureResource");
            pictureEntity.setAdd(true);

            addData(data.size(), pictureEntity);
        }

    }

//    public void addAllData(List<PictureEntity> list) {
//        data.addAll(list);
//    }
//
//    public void addAllData(int position, List<PictureEntity> list) {
//        data.addAll(position, list);
//    }

    @Override
    public int getItemCount() {
        if (data == null)
            data = new ArrayList<>();

        return data.size();
    }


    public void setGPOptions(GPOptions options) {
        this.mGPOptions = options;

        GPLoadPicture gpLoadPicture = mGPOptions.getGPLoadPicture();
        if (gpLoadPicture == null) {
            gpLoadPicture = new GPLoadPicture();
            mGPOptions.setGPLoadPicture(gpLoadPicture);
        }

        //设置加载 参数
        requestOptions.centerCrop()
//                .diskCacheStrategy(DiskCacheStrategy.NONE)//禁用硬盘缓存
                .placeholder(gpLoadPicture.getDefaultPicture())
                .override(Target.SIZE_ORIGINAL)            //显示原始图片大小
                .error(gpLoadPicture.getLoadErrorPicture());//图片加载失败后，显示的图片
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg_content;
        private ImageView mImg_clear;
        private FrameLayout mFrameLayout;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg_content = itemView.findViewById(R.id.item_gridPictureAdapter_img_content);
            mImg_clear = itemView.findViewById(R.id.item_gridPictureAdapter_img_clear);
            mFrameLayout = itemView.findViewById(R.id.item_gridPictureAdapter_frameLayout);

        }
    }
}