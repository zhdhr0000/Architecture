package com.zhdhr0000.architecture.processsheild.presenter;

import com.zhdhr0000.architecture.base.RxPresenter;
import com.zhdhr0000.architecture.processsheild.protocol.ProcessSheild;
import com.zhdhr0000.architecture.processsheild.protocol.ProcessSheild.Presenter;

/**
 * Created by win7 on 2017/3/6.
 */

public class ProcessSheildPresenter extends RxPresenter<ProcessSheild.View> implements Presenter {
    @Override
    public void makeJavaCrash() {
        String str = null;
        String a = str.toString();
    }

    @Override
    public void makeNativeCrash() {

    }
}
