package com.zhdhr0000.architecture.app;

import android.app.Application;

/**
 * Created by zhangyh on 2017/2/28.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //init ImageLoader
        initFresco();

        //init http
        initHttp();
    }

    private void initHttp() {
        //placeholder
    }

    private void initFresco() {
        //placeholder
    }
}
