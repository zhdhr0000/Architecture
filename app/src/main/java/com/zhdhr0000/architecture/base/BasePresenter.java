package com.zhdhr0000.architecture.base;

/**
 * Created by zhangyh on 2017/2/28.
 * Presenter基类
 */

public class BasePresenter<T extends IView> implements IPresenter<T> {

    protected T mView;

    @Override
    public void attachView(T view) {
        this.mView = view;
    }

    @Override
    public void detachView() {
        this.mView = null;
    }
}
