package com.zhdhr0000.architecture.view.activity;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.app.Constants;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.presenter.MainPresenter;
import com.zhdhr0000.architecture.protocol.Main;
import com.zhdhr0000.architecture.view.adapter.MainDrawerAdapter;
import com.zhdhr0000.architecture.view.fragment.EditWithRxFragment;
import com.zhdhr0000.architecture.view.fragment.ProcessSheildFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhangyh on 2017/3/1.
 * App主页
 */

public class MainActivity extends BaseActivity<MainPresenter> implements Main.View {

    @BindView(R.id.dl_left_menu)
    DrawerLayout mDlLeftMenu;
    @BindView(R.id.place_holder)
    FrameLayout mPlaceHolder;
    @BindView(R.id.rl_left_menu)
    RelativeLayout mRlLeftMenu;
    @BindView(R.id.lv_drawer)
    ListView mLvDrawer;

    MainDrawerAdapter mDrawerAdapter;
    int currentFragment = Constants.TYPE_EDITWITHRX;

    EditWithRxFragment mEditWithRxFragment;
    ProcessSheildFragment mProcessSheildFragment;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initDataAndEvent() {
        mEditWithRxFragment = new EditWithRxFragment();
        mProcessSheildFragment = new ProcessSheildFragment();
        loadMultipleRootFragment(R.id.place_holder, 0, mEditWithRxFragment, mProcessSheildFragment);
        mDrawerAdapter = new MainDrawerAdapter(this);
        mLvDrawer.setAdapter(mDrawerAdapter);
        ArrayList list = new ArrayList();
        list.add(Constants.TYPE_EDITWITHRX);
        list.add(Constants.TYPE_PROCCESSHEILD);
        mDrawerAdapter.setData(list);
        mLvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                if (currentFragment != mDrawerAdapter.getData().get(position)) {
                    showHideFragment(getFragmentByType(mDrawerAdapter.getData().get(position)), getFragmentByType(currentFragment));
                    currentFragment = mDrawerAdapter.getData().get(position);
                    mDlLeftMenu.closeDrawers();
                }
            }
        });
    }

    private BaseFragment getFragmentByType(int type) {
        switch (type) {
            case Constants.TYPE_EDITWITHRX:
                return mEditWithRxFragment;
            case Constants.TYPE_PROCCESSHEILD:
                return mProcessSheildFragment;
            default:
                return mEditWithRxFragment;
        }
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
