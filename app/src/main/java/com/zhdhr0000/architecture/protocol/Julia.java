package com.zhdhr0000.architecture.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by win7 on 2017/3/20.
 */

public interface Julia {
    interface Presenter extends IPresenter<View> {
    }

    interface View extends IView {
    }
}
