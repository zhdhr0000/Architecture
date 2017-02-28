package com.zhdhr0000.architecture.protocol.mvp;

/**
 * Created by win7 on 2017/2/28.
 */

public interface IPresenter<T extends IView> {
    void attachView(T view);
    void detachView();
}
