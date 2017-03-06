package com.zhdhr0000.architecture.app;

import android.app.Application;

/**
 * Created by win7 on 2017/2/28.
 */

public class App extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //initDataAndEvent ImageLoader
        initFresco();

        //initDataAndEvent http
        initHttp();
    }

    private void initHttp() {
        //placeholder
    }

    private void initFresco() {
        //placeholder
    }
}
