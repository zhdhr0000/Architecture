package com.zhdhr0000.architecture.view.activity;

import android.support.v4.app.Fragment;
import android.support.v4.widget.DrawerLayout;
import android.widget.FrameLayout;
import android.widget.RelativeLayout;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.presenter.MainPresenter;
import com.zhdhr0000.architecture.protocol.Main;
import com.zhdhr0000.architecture.view.fragment.EditWithRxFragment;

import butterknife.BindView;

public class MainActivity extends BaseActivity<MainPresenter> implements Main.View {

    @BindView(R.id.dl_left_menu)
    DrawerLayout mDlLeftMenu;
    @BindView(R.id.place_holder)
    FrameLayout mPlaceHolder;
    @BindView(R.id.rl_left_menu)
    RelativeLayout mRlLeftMenu;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void init() {
        Fragment fragment = new EditWithRxFragment();
        getSupportFragmentManager().beginTransaction()
                .add(R.id.place_holder, fragment, EditWithRxFragment.class.getName())
                .show(fragment)
                .commit();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }
}
