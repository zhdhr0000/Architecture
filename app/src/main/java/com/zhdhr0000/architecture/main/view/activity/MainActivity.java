package com.zhdhr0000.architecture.main.view.activity;

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
import com.zhdhr0000.architecture.editwithrx.view.fragment.EditWithRxFragment;
import com.zhdhr0000.architecture.imagetransform.ImageTransformFragment;
import com.zhdhr0000.architecture.juliaset.view.fragment.JuliaSetFragment;
import com.zhdhr0000.architecture.main.presenter.MainPresenter;
import com.zhdhr0000.architecture.main.protocol.Main;
import com.zhdhr0000.architecture.main.view.adapter.MainDrawerAdapter;
import com.zhdhr0000.architecture.parallax.view.fragment.ParallaxFragment;
import com.zhdhr0000.architecture.processsheild.view.fragment.ProcessSheildFragment;
import com.zhdhr0000.architecture.tinder.view.fragment.TinderFragment;
import com.zhdhr0000.architecture.video.view.fragment.VideoFragment;
import com.zhdhr0000.architecture.weex.view.WeexFragment;

import java.util.ArrayList;

import butterknife.BindView;

/**
 * Created by zhangyh on 2017/3/1.
 * App主页
 */

public class MainActivity extends BaseActivity<Main.Presenter> implements Main.View {

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
    WeexFragment mWeexFragment;
    ParallaxFragment mParallaxFragment;
    VideoFragment mVideoFragment;
    ImageTransformFragment mImageTransformFragment;

    @Override
    protected Main.Presenter initPresenter() {
        return new MainPresenter();
    }

    @Override
    protected void initDataAndEvent() {
        mEditWithRxFragment = new EditWithRxFragment();
        mProcessSheildFragment = new ProcessSheildFragment();
        mJuliaSetFragment = new JuliaSetFragment();
        mTinderFragment = new TinderFragment();
        mWeexFragment = new WeexFragment();
        mParallaxFragment = new ParallaxFragment();
        mVideoFragment = new VideoFragment();
        mImageTransformFragment = new ImageTransformFragment();
        loadRootFragment(R.id.place_holder, mImageTransformFragment);
        mDrawerAdapter = new MainDrawerAdapter(this);
        mLvDrawer.setAdapter(mDrawerAdapter);
        ArrayList list = new ArrayList();
        list.add(Constants.TYPE_EDITWITHRX);
        list.add(Constants.TYPE_PROCCESSHEILD);
        list.add(Constants.TYPE_JULIASET);
        list.add(Constants.TYPE_TINDER);
        list.add(Constants.TYPE_WEEX);
        list.add(Constants.TYPE_PARALLAX);
        list.add(Constants.TYPE_VIDEO);
        list.add(Constants.TYPE_IMAGETRANSFORM);
        mDrawerAdapter.setData(list);
        mLvDrawer.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (currentFragment != mDrawerAdapter.getData().get(position)) {
                    replaceLoadRootFragment(R.id.place_holder, getFragmentByType(mDrawerAdapter.getData().get(position)), true);
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
            case Constants.TYPE_WEEX:
                return mWeexFragment;
            case Constants.TYPE_PARALLAX:
                return mParallaxFragment;
            case Constants.TYPE_VIDEO:
                return mVideoFragment;
            case Constants.TYPE_IMAGETRANSFORM:
                return mImageTransformFragment;
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
