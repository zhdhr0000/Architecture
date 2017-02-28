package com.zhdhr0000.architecture.base;

import android.support.v7.app.AppCompatActivity;

import com.zhdhr0000.architecture.protocol.mvp.IView;

/**
 * Created by win7 on 2017/2/28.
 */

public class BaseActivity<T extends BasePresenter> extends AppCompatActivity implements IView {
    @Override
    public void showError() {

    }
}
