package com.zhdhr0000.architecture.tinder.view.fragment;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.tinder.presenter.TinderPresenter;
import com.zhdhr0000.architecture.tinder.protocol.Tinder;

/**
 * Created by win7 on 2017/3/21.
 */

public class TinderFragment extends BaseFragment<TinderPresenter> implements Tinder.View {
    @Override
    public void showError(int type, String msg) {

    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    protected void initPresenter() {

    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_tinder;
    }
}
