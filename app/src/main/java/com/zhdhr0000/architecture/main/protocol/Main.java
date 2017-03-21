package com.zhdhr0000.architecture.main.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by zhangyh on 2017/3/1.
 */

public interface Main {
    interface Presenter extends IPresenter<View> {
        void doSomething();
    }

    interface View extends IView {

    }
}
