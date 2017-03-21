package com.zhdhr0000.architecture.processsheild.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by win7 on 2017/3/6.
 */

public interface ProcessSheild {
    interface View extends IView {

    }

    interface Presenter extends IPresenter<View> {
        void makeJavaCrash();

        void makeNativeCrash();
    }
}
