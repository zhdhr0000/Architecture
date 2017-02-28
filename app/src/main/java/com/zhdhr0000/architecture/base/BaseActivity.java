package com.zhdhr0000.architecture.base;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;

import com.zhdhr0000.architecture.protocol.mvp.IView;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by win7 on 2017/2/28.
 */

public abstract class BaseActivity<T extends RxPresenter> extends AppCompatActivity implements IView {

    protected T mPresenter;
    protected BaseActivity mActivity;

    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(getLayoutID());
        mActivity = this;
        ButterKnife.bind(this);
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
    }

    protected abstract void initPresenter();

    protected abstract void init();

    protected abstract int getLayoutID();
}
