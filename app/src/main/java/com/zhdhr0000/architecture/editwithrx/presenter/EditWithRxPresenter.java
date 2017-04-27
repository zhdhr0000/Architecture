package com.zhdhr0000.architecture.editwithrx.presenter;

import com.zhdhr0000.architecture.base.RxPresenter;
import com.zhdhr0000.architecture.editwithrx.protocol.EditWithRx;
import com.zhdhr0000.architecture.utils.RxUtil;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * Created by zhangyh on 2017/3/1.
 */

public class EditWithRxPresenter extends RxPresenter<EditWithRx.View> implements EditWithRx.Presenter {
    @Override
    public void getContent1(String content) {
        addDisposables(Observable.just(content)
                .compose(RxUtil.<String>schedulerHelper())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.showContent1(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(0, throwable.toString());
                    }
                }));
    }

    @Override
    public void getContent2(String content) {
        addDisposables(Observable.just(content)
                .compose(RxUtil.<String>schedulerHelper())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.showContent2(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(0, throwable.toString());
                    }
                }));
    }

    @Override
    public void commit(String content) {
        addDisposables(Observable.just(content)
                .compose(RxUtil.<String>schedulerHelper())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mView.showToast(s);
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        mView.showError(0, throwable.toString());
                    }
                }));
    }
}
