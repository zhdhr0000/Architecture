package com.zhdhr0000.architecture.tinder.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by win7 on 2017/3/21.
 */

public interface Tinder {
    interface Presenter extends IPresenter<View> {
    }

    interface View extends IView {
    }
}
