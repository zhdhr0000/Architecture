package com.zhdhr0000.architecture.base;

import com.zhdhr0000.architecture.protocol.mvp.IPresenter;
import com.zhdhr0000.architecture.protocol.mvp.IView;

/**
 * Created by win7 on 2017/2/28.
 */

public class BasePresenter<T extends IView> implements IPresenter<T> {

    private T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
