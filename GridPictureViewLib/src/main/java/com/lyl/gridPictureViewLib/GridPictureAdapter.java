package com.lyl.gridPictureViewLib;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.lyl.gridPictureViewLib.options.GPAddPicture;
import com.lyl.gridPictureViewLib.options.GPDeletePicture;
import com.lyl.gridPictureViewLib.options.GPFrame;
import com.lyl.gridPictureViewLib.options.GPOptions;

import java.util.ArrayList;
import java.util.List;

/**
 * 图片展示适配器
 *
 * @ Date 2018-12-04
 */
class GridPictureAdapter extends RecyclerView.Adapter<GridPictureAdapter.ViewHolder> {

    private Context mContext;
    private List<PictureEntity> data;
    private GPOptions mGPOptions;
    private LoaderPictureStrategy mGridPictureStrategy;//图片加载回调

    public GridPictureAdapter(Context context, List<PictureEntity> entityList) {
        this.mContext = context;
        if (entityList == null)
            entityList = new ArrayList<>();
        this.data = entityList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_grid_picture_adapter, parent, false);
        return new ViewHolder(view);

    }


    @Override
    public void onBindViewHolder(@NonNull GridPictureAdapter.ViewHolder holder, int index) {

        final int position = index;
        //设置图片尺寸
        setPictureViewWH(holder.mImg_content);

        //设置删除按钮尺寸
        setDeleteViewWH(holder.mImg_clear);

        final PictureEntity pictureEntity = data.get(position);
        final GPDeletePicture gpDeletePicture = mGPOptions.getGPDeletePicture();
        final GPAddPicture gpAddPicture = mGPOptions.getGPAddPicture();
        final GPFrame gpFrame = mGPOptions.getGPFrame();

        //是否显示删除图标
        if (gpDeletePicture == null || !gpDeletePicture.isShowDelete() || pictureEntity.isAdd()) {
            holder.mImg_clear.setVisibility(View.GONE);
        } else {
            holder.mImg_clear.setVisibility(View.VISIBLE);
            holder.mImg_clear.setImageResource(gpDeletePicture.getDeleteResource());
        }


        //获取图片类型
        String pictureType = pictureEntity.getPictureType();
        if (TextUtils.isEmpty(pictureType))
            return;

        if (PictureEntity.PICTURE_TYPE_PATH.equalsIgnoreCase(pictureType)) {//图片路径
            String picturePath = pictureEntity.getPicturePath();

            if (mGridPictureStrategy != null)//内部回调优先使用
                mGridPictureStrategy.request(mContext, picturePath, holder.mImg_content);
            else {//全局回调
                LoaderPictureStrategy loaderPictureStrategy = GridPictureView.getLoaderPictureStrategy();
                if (loaderPictureStrategy != null)
                    loaderPictureStrategy.request(mContext, picturePath, holder.mImg_content);
            }

        } else if (PictureEntity.PICTURE_TYPE_RESOURCE.equalsIgnoreCase(pictureType)) {//本地资源图片
            holder.mImg_content.setImageResource(pictureEntity.getPictureResource());
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
                        gpFrame.getOnPictureClickListener().onPictureClick(view, position
                                , pictureEntity.getPicturePath());
                    }
                }
            }
        });

        //删除事件
        holder.mImg_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gpDeletePicture != null && gpDeletePicture.getDeleteClickListener() != null) {
                    gpDeletePicture.getDeleteClickListener().onDeleteClick(view, position
                            , pictureEntity.getPicturePath());
                }
            }
        });


    }

    /**
     * 设置图片 宽度、高度
     *
     * @param imageView
     */
    private void setPictureViewWH(ImageView imageView) {
        GPFrame gpFrame = mGPOptions.getGPFrame();
        if (gpFrame == null)
            gpFrame = new GPFrame();

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = dip2px(gpFrame.getPictureHeight());
        params.width = dip2px(gpFrame.getPictureWidth());

        //根据删除按钮 设置imageView Padding
        final GPDeletePicture gpDeletePicture = mGPOptions.getGPDeletePicture();
        int pictureWidth = dip2px(gpDeletePicture.getPictureWidth());
        int pictureHeight = dip2px(gpDeletePicture.getPictureHeight());
        imageView.setPadding(pictureWidth / 3, pictureHeight / 3, pictureWidth / 3, pictureHeight / 3);
    }


    /**
     * 设置删除按钮 宽度、高度
     *
     * @param imageView
     */
    private void setDeleteViewWH(ImageView imageView) {
        GPDeletePicture gpDeletePicture = mGPOptions.getGPDeletePicture();
        if (gpDeletePicture == null)
            gpDeletePicture = new GPDeletePicture();

        ViewGroup.LayoutParams params = imageView.getLayoutParams();
        params.height = dip2px(gpDeletePicture.getPictureHeight());
        params.width = dip2px(gpDeletePicture.getPictureWidth());
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public int dip2px(float dpValue) {
        float scale = mContext.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    @Override
    public int getItemCount() {
        if (data == null)
            data = new ArrayList<>();

        return data.size();
    }

    /**
     * 添加新增图标
     */
    public void addonAddPictureData() {

        if (mGPOptions == null)
            return;

        GPAddPicture gpAddPicture = mGPOptions.getGPAddPicture();
        if (gpAddPicture == null)
            gpAddPicture = new GPAddPicture();

        GPFrame gpFrame = mGPOptions.getGPFrame();
        if (gpFrame == null)
            gpFrame = new GPFrame();

        int maxCount = gpFrame.getMaxCount();
        int size = data.size();

        //新增图标实体
        PictureEntity t = new PictureEntity(gpAddPicture.getAddResource(), true);

        if (size == 0) {//Grid中无数据直接添加
            data.add(t);
            notifyDataSetChanged();
        } else if (size < maxCount) {//小于最大值
            PictureEntity pictureEntity = data.get(size - 1);
            if (!pictureEntity.isAdd()) {//最后一个 为 非新增 图标，则把数据设置进去
                data.add(t);
                notifyDataSetChanged();
            }
        }

    }


    /**
     * 添加显示图片
     *
     * @param resId
     */
    public void addPictureData(int resId) {
        //图片实体
        PictureEntity t = new PictureEntity(resId, false);

        //添加实体数据
        addPictureData(t);
    }


    /**
     * 添加显示图片
     *
     * @param path
     */
    public void addPictureData(String path) {
        if (TextUtils.isEmpty(path))
            return;

        //图片实体
        PictureEntity t = new PictureEntity(path, false);

        //添加实体数据
        addPictureData(t);
    }

    /**
     * 添加显示图片
     *
     * @param t
     */
    public void addPictureData(PictureEntity t) {
        if (mGPOptions == null || t == null)
            return;

        GPFrame gpFrame = mGPOptions.getGPFrame();
        int maxCount = gpFrame.getMaxCount();
        int size = data.size();
        if (maxCount == 0) //最大值为0
            return;


        //增加 图片
        if (size == 0) {//添加 第1个图片
            data.add(t);
            notifyDataSetChanged();
        } else if (size < maxCount) {//小于最大值
            PictureEntity pictureEntity = data.get(size - 1);
            if (pictureEntity.isAdd()) {//最后一个 为 新增 图标
                data.add(size - 1, t);
                notifyDataSetChanged();
            } else {//最后一个为非新增图片
                data.add(t);
                notifyDataSetChanged();
            }
        } else if (size == maxCount) {//Grid 添加最后一个图片
            PictureEntity pictureEntity = data.get(size - 1);
            if (pictureEntity.isAdd()) {//去掉最后一个 为新增 图标，再数据设置进去
                data.remove(size - 1);
                data.add(t);
                notifyDataSetChanged();
            }

        }
    }


    public List<PictureEntity> getData() {
        return data;
    }

    public void addAllData(List<String> paths) {
        if (paths == null || paths.size() == 0)
            return;

        for (String path : paths) {
            addPictureData(path);
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
            addonAddPictureData();
        }

    }

    public void setGPOptions(GPOptions options) {
        mGPOptions = options;
    }

    public void setGridPictureStrategy(LoaderPictureStrategy gridPictureStrategy) {
        this.mGridPictureStrategy = gridPictureStrategy;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImg_content;
        private ImageView mImg_clear;

        public ViewHolder(View itemView) {
            super(itemView);
            mImg_content = itemView.findViewById(R.id.item_gridPictureAdapter_img_content);
            mImg_clear = itemView.findViewById(R.id.item_gridPictureAdapter_img_clear);
        }
    }


}
