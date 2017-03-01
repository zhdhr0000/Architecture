package com.zhdhr0000.architecture.base;

import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

/**
 * Created by win7 on 2017/2/28.
 */

public class RxPresenter<T extends IView> extends BasePresenter<T> {

    protected CompositeDisposable mDisposables;

    protected void addDisposables(Disposable disposable) {
        if (mDisposables == null) {
            mDisposables = new CompositeDisposable();
        }
        mDisposables.add(disposable);
    }

    protected void disposeAll() {
        if (mDisposables != null) {
            mDisposables.dispose();
        }
    }

    @Override
    public void detachView() {
        disposeAll();
        super.detachView();
    }
}
