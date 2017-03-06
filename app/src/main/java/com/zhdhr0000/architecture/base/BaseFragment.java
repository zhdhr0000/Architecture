package com.zhdhr0000.architecture.base;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportFragment;

/**
 * Created by zhangyh on 2017/2/28.
 * Fragment基类
 */

public abstract class BaseFragment<T extends IPresenter> extends SupportFragment implements IView {

    protected T mPresenter;
    protected View mView;
    protected Activity mActivity;
    protected Toast mToast;
    private Unbinder mUnbinder;

    @Override
    public void onAttach(Context context) {
        mActivity = (Activity) context;
        super.onAttach(context);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mView = inflater.inflate(getLayoutID(), container, false);
        initPresenter();
        return mView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (mPresenter != null) {
            mPresenter.attachView(this);
            mUnbinder = ButterKnife.bind(this, view);
        }
        initDataAndEvent();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        mUnbinder.unbind();
    }

    @Override
    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        super.onDestroy();
    }

    @Override
    public void showToast(String toast) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(mActivity, toast + "", Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected abstract void initDataAndEvent();

    protected abstract void initPresenter();

    protected abstract int getLayoutID();

}
