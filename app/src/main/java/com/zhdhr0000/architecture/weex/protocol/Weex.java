package com.zhdhr0000.architecture.weex.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by zhangyinghao on 2017/5/11.
 */

public interface Weex {
    interface Presenter extends IPresenter<View> {
    }

    interface View extends IView {
    }
}
