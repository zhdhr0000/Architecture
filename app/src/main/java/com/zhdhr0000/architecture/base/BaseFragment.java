package com.zhdhr0000.architecture.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zhdhr0000.architecture.protocol.mvp.IView;

/**
 * Created by win7 on 2017/2/28.
 */

public abstract class BaseFragment<T extends RxPresenter> extends Fragment implements IView {

    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        init();
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutID(), container);
        initPresenter();
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    protected abstract void init();

    protected abstract void initPresenter();

    protected abstract int getLayoutID();

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
    }

}
