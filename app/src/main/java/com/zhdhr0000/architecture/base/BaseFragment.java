package com.zhdhr0000.architecture.base;

import android.support.v4.app.Fragment;

import com.zhdhr0000.architecture.protocol.mvp.IView;

/**
 * Created by win7 on 2017/2/28.
 */

public class BaseFragment<T extends BasePresenter> extends Fragment implements IView {
    @Override
    public void showError() {

    }
}
