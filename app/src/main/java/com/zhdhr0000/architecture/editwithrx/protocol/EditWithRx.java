package com.zhdhr0000.architecture.editwithrx.protocol;

import com.zhdhr0000.architecture.base.IPresenter;
import com.zhdhr0000.architecture.base.IView;

/**
 * Created by zhangyh on 2017/3/1.
 */

public interface EditWithRx {
    interface Presenter extends IPresenter<View> {
        void getContent1(String content);
        void getContent2(String content);

        void commit(String content);
    }

    interface View extends IView {
        void showContent1(String content);

        void showContent2(String content);

        void showToast(String content);
    }
}
