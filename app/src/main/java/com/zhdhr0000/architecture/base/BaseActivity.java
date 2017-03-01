package com.zhdhr0000.architecture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by win7 on 2017/2/28.
 */

public abstract class BaseActivity<T extends RxPresenter> extends AppCompatActivity implements IView {

    protected T mPresenter;
    protected BaseActivity mActivity;
    protected Toast mToast;
    private Unbinder mUnbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        mUnbinder = ButterKnife.bind(this);
        mActivity = this;
        initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        init();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) {
            mPresenter.detachView();
        }
        mUnbinder.unbind();
    }

    @Override
    public void showToast(String toast) {
        if (mToast != null) {
            mToast.cancel();
        }
        mToast = Toast.makeText(this, toast + "", Toast.LENGTH_SHORT);
        mToast.show();
    }

    protected abstract void initPresenter();

    protected abstract void init();

    protected abstract int getLayoutID();

}
