package com.zhdhr0000.architecture.video.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by zhangyinghao on 2017/8/21.
 */

public interface Video {
    interface Presenter extends IPresenter<View> {
        void doSomething();
    }

    interface View extends IView {

    }
}
