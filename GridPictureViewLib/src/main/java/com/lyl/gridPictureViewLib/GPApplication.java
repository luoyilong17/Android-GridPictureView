package com.lyl.gridPictureViewLib;

import android.app.Application;

/**
 * Created by luoyilong on 2018/4/20.
 */

public  class GPApplication extends Application {
    
    private static GPApplication instance = null;
    public static GPApplication app() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        //Application全局只有一个，它本身就已经是单例了，无需再用单例模式去为它做多重实例保护了
        instance = this;
        
    }
}
