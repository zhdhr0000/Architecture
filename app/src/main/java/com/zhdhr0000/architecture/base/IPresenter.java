package com.zhdhr0000.architecture.base;

/**
 * Created by zhangyh on 2017/2/28.
 * Presenter的基础抽象协议
 */

public interface IPresenter<T extends IView> {
    void attachView(T view);
    void detachView();
}
