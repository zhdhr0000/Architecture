package com.zhdhr0000.architecture.main.view.activity;

import android.support.v4.widget.DrawerLayout;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.app.Constants;
import com.zhdhr0000.architecture.base.BaseActivity;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.editwithrx.view.fragment.EditWithRxFragment;
import com.zhdhr0000.architecture.juliaset.view.fragment.JuliaSetFragment;
import com.zhdhr0000.architecture.main.presenter.MainPresenter;
import com.zhdhr0000.architecture.main.protocol.Main;
import com.zhdhr0000.architecture.main.view.adapter.MainDrawerAdapter;
import com.zhdhr0000.architecture.processsheild.view.fragment.ProcessSheildFragment;
import com.zhdhr0000.architecture.tinder.view.fragment.TinderFragment;
import com.zhdhr0000.architecture.utils.DimenUtil;

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
    JuliaSetFragment mJuliaSetFragment;
    TinderFragment mTinderFragment;

    @Override
    protected void initPresenter() {
        mPresenter = new MainPresenter();
    }

    @Override
    protected void initDataAndEvent() {
        mEditWithRxFragment = new EditWithRxFragment();
        mProcessSheildFragment = new ProcessSheildFragment();
        mJuliaSetFragment = new JuliaSetFragment();
        mTinderFragment = new TinderFragment();
        loadMultipleRootFragment(R.id.place_holder, 0,
                mEditWithRxFragment,
                mProcessSheildFragment,
                mJuliaSetFragment,
                mTinderFragment
        );
        mDrawerAdapter = new MainDrawerAdapter(this);
        mLvDrawer.setAdapter(mDrawerAdapter);
        ArrayList list = new ArrayList();
        list.add(Constants.TYPE_EDITWITHRX);
        list.add(Constants.TYPE_PROCCESSHEILD);
        list.add(Constants.TYPE_JULIASET);
        list.add(Constants.TYPE_TINDER);
//        list.add(Constants.TYPE_WEEX);
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
            case Constants.TYPE_JULIASET:
                return mJuliaSetFragment;
            case Constants.TYPE_TINDER:
                return mTinderFragment;
//            case Constants.TYPE_WEEX:
//                return mLithoAndYogaFragment;
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
