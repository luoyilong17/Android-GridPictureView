package com.lyl.gridpictureview;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Toast;

import com.lyl.gridPictureViewLib.common.OnAddClickListener;
import com.lyl.gridPictureViewLib.common.OnDeleteClickListener;
import com.lyl.gridPictureViewLib.common.OnPictureClickListener;
import com.lyl.gridPictureViewLib.options.GPAddPicture;
import com.lyl.gridPictureViewLib.options.GPDeletePicture;
import com.lyl.gridPictureViewLib.options.GPFrame;
import com.lyl.gridPictureViewLib.options.GPLoadPicture;
import com.lyl.gridPictureViewLib.options.GPOptions;
import com.lyl.gridPictureViewLib.view.GridPictureView;
import com.tbruyelle.rxpermissions2.RxPermissions;

public class MainActivity extends AppCompatActivity {
    private static final int REQUEST_PERMISSIONS = 100;
    private GridPictureView mGridPictureView;
    private Button mButAdd;
    private CheckBox mCheckBoxAdd;
    private CheckBox mCheckBoxDelete;
    private AppCompatActivity mActivity;
    private boolean hadPermission = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mActivity = this;

        mGridPictureView = (GridPictureView) findViewById(R.id.grid_picture);
        mButAdd = (Button) findViewById(R.id.but_add);
        mCheckBoxAdd = (CheckBox) findViewById(R.id.checkBox_add);
        mCheckBoxDelete = (CheckBox) findViewById(R.id.checkBox_delete);

        initData();
        initGridPictureView();

    }

    private void initData() {


        RxPermissions rxPermission = new RxPermissions(this);
        rxPermission.requestEach(Manifest.permission.WRITE_EXTERNAL_STORAGE
                , Manifest.permission.READ_EXTERNAL_STORAGE
        ).subscribe(permission -> {
            if (permission.granted) {
                // 用户已经同意该权限
                hadPermission = true;
            } else if (permission.shouldShowRequestPermissionRationale) {
                // 用户拒绝了该权限，没有选中『不再询问』（Never ask again）,那么下次再次启动时，还会提示请求权限的对话框

            } else {
                // 用户拒绝了该权限，并且选中『不再询问』

            }
        });

        //增加图片
        mButAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String url = "http://cn.bing.com/az/hprichbg/rb/Dongdaemun_ZH-CN10736487148_1920x1080.jpg";
                int resId = R.mipmap.default_photo;
                mGridPictureView.addPicture(url);
            }
        });


        //显示新增图标
        mCheckBoxAdd.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                GPOptions gpOptions = mGridPictureView.getGPOptions();
                if (gpOptions == null)
                    gpOptions = new GPOptions();

                GPAddPicture gpAddPicture = gpOptions.getGPAddPicture();
                if (gpAddPicture == null)
                    gpAddPicture = new GPAddPicture();

                gpAddPicture.setShowAdd(b);
                gpOptions.setGPAddPicture(gpAddPicture);

                mGridPictureView.setGPOptions(gpOptions);
            }
        });

        //显示删除图标
        mCheckBoxDelete.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                GPOptions gpOptions = mGridPictureView.getGPOptions();
                if (gpOptions == null)
                    gpOptions = new GPOptions();

                GPDeletePicture gpDeletePicture = gpOptions.getGPDeletePicture();
                if (gpDeletePicture == null)
                    gpDeletePicture = new GPDeletePicture();

                gpDeletePicture.setShowDelete(b);
                gpOptions.setGPDeletePicture(gpDeletePicture);


                mGridPictureView.setGPOptions(gpOptions);

            }
        });
    }


    //初始化GridPictureView 参数设置
    private void initGridPictureView() {

        GPDeletePicture gpDeletePicture = new GPDeletePicture();
        gpDeletePicture.setDeleteClickListener(new OnDeleteClickListener() {
            @Override
            public void onDeleteClick(View view, int position) {
                //删除图标点击事件
                mGridPictureView.removePicture(position);
            }
        });


        GPAddPicture gpAddPicture = new GPAddPicture();
        gpAddPicture.setOnAddClickListener(new OnAddClickListener() {
            @Override
            public void onAddClick(View view, int position) {
                //新增图标点击事件
                mGridPictureView.addPicture(R.mipmap.ic_about_log);
            }
        });

//        GPFrame gpFrame = new GPFrame();
//        gpFrame.setOnPictureClickListener(new OnPictureClickListener() {
//            @Override
//            public void onPictureClick(View view, int position) {
//                //图标点击事件
//                Toast.makeText(mActivity, "点击图标:" + position, Toast.LENGTH_SHORT).show();
//            }
//        });
//
//        GPLoadPicture gpLoadPicture = new GPLoadPicture();

        GPOptions gpOptions = new GPOptions();
        gpOptions.setGPDeletePicture(gpDeletePicture)
                .setGPAddPicture(gpAddPicture);
//                .setGPFrame(gpFrame)
//                .setGPLoadPicture(gpLoadPicture);


        mGridPictureView.setGPOptions(gpOptions);
    }

    
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_PERMISSIONS) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (checkSelfPermission(Manifest.permission.CAMERA) == PackageManager
                        .PERMISSION_GRANTED) {
                    hadPermission = true;
                }
            }
        }
    }
}
