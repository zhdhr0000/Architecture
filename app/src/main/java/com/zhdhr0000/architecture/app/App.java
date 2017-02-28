package com.zhdhr0000.architecture.app;

import android.app.Application;

/**
 * Created by win7 on 2017/2/28.
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

    }

    private void initFresco() {

    }
}
