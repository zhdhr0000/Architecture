package com.zhdhr0000.architecture.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;
import me.yokeyword.fragmentation.SupportActivity;

/**
 * Created by zhangyh on 2017/2/28.
 * Activity基类
 */

public abstract class BaseActivity<T extends IPresenter> extends SupportActivity implements IView {

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
        mPresenter = initPresenter();
        if (mPresenter != null) {
            mPresenter.attachView(this);
        }
        initDataAndEvent();
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

    protected abstract T initPresenter();

    protected abstract void initDataAndEvent();

    protected abstract int getLayoutID();

}
