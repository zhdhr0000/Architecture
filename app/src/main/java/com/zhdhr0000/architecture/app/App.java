package com.zhdhr0000.architecture.app;

import android.app.Application;

import com.facebook.drawee.backends.pipeline.Fresco;
import com.taobao.weex.InitConfig;
import com.taobao.weex.WXSDKEngine;
import com.zhdhr0000.architecture.weex.component.WeexImageLoader;


/**
 * Created by zhangyh on 2017/2/28.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initImageLoader
        initFresco();

        //initWeex
        initWeex();
    }

    private void initWeex() {
        InitConfig config = new InitConfig.Builder()
                .setImgAdapter(new WeexImageLoader())
                .build();
        WXSDKEngine.initialize(this, config);
    }

    private void initFresco() {
        //placeholder
        Fresco.initialize(this);
    }
}
