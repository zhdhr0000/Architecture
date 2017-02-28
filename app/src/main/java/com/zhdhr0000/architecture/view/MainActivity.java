package com.zhdhr0000.architecture.view;

import android.widget.TextView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.base.RxPresenter;

import butterknife.BindView;

public class MainActivity extends BaseActivity<RxPresenter> {

    @BindView(R.id.textview)
    TextView textView;

    @Override
    protected void initPresenter() {
        mPresenter = new RxPresenter();
    }

    @Override
    protected void init() {
        
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void showError() {
        textView.setText("Error!");
    }
}
