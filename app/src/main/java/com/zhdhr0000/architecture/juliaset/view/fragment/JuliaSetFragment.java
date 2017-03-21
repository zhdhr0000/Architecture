package com.zhdhr0000.architecture.juliaset.view.fragment;

import android.widget.Toast;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.juliaset.presenter.JuliaPresenter;

/**
 * Created by win7 on 2017/3/20.
 */

public class JuliaSetFragment extends BaseFragment {
    @Override
    public void showError(int type, String msg) {
        Toast.makeText(mActivity,msg+"",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void initDataAndEvent() {

    }

    @Override
    protected void initPresenter() {
        mPresenter = new JuliaPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_julia_set;
    }
}
