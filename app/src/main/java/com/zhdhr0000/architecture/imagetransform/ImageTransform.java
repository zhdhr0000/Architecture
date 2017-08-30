package com.zhdhr0000.architecture.imagetransform;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by zhangyinghao on 2017/8/30.
 */

public interface ImageTransform {
    interface View extends IView {
    }

    interface Presenter extends IPresenter<View> {
    }
}
