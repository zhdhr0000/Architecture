package com.zhdhr0000.architecture.weex.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.taobao.weex.IWXRenderListener;
import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.common.WXRenderStrategy;
import com.taobao.weex.utils.WXFileUtils;
import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;
import com.zhdhr0000.architecture.weex.presenter.WeexPresenter;
import com.zhdhr0000.architecture.weex.protocol.Weex;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/5/11.
 */

public class WeexFragment extends BaseFragment<Weex.Presenter> implements Weex.View, IWXRenderListener {
    @BindView(R.id.weex_container)
    FrameLayout weexContainer;

    WXSDKInstance weexInstance;

    @Override
    public void showError(int type, String msg) {
    }

    @Override
    protected void initDataAndEvent() {
        weexInstance = new WXSDKInstance(mActivity);
        weexInstance.registerRenderListener(this);
        weexInstance.render("vue sample", WXFileUtils.loadAsset("index.js", mActivity), null, null, WXRenderStrategy.APPEND_ASYNC);
    }

    @Override
    protected Weex.Presenter initPresenter() {
        return new WeexPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_weex;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (weexInstance != null) {
            weexInstance.onActivityResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (weexInstance != null) {
            weexInstance.onActivityPause();
        }
    }

    @Override
    public void onStop() {
        super.onStop();
        if (weexInstance != null) {
            weexInstance.onActivityStop();
        }
    }

    @Override
    public void onStart() {
        super.onStart();
        if (weexInstance != null) {
            weexInstance.onActivityStart();
        }
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (weexInstance != null) {
            weexInstance.onActivityDestroy();
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = super.onCreateView(inflater, container, savedInstanceState);
        if (weexInstance != null) {
            weexInstance.onActivityCreate();
        }
        return view;
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (weexInstance != null) {
            weexInstance.onActivityResult(requestCode, resultCode, data);
        }
    }

    @Override
    public boolean onBackPressedSupport() {
        if (weexInstance != null) {
            weexInstance.onBackPressed();
        }
        return super.onBackPressedSupport();
    }

    //weex render callback
    @Override
    public void onViewCreated(WXSDKInstance instance, View view) {
        if (null != weexContainer) {
            weexContainer.removeAllViews();
            weexContainer.addView(view);
        }
    }

    //weex render callback
    @Override
    public void onRenderSuccess(WXSDKInstance instance, int width, int height) {

    }

    //weex render callback
    @Override
    public void onRefreshSuccess(WXSDKInstance instance, int width, int height) {

    }

    //weex render callback
    @Override
    public void onException(WXSDKInstance instance, String errCode, String msg) {
        showToast("errCode " + errCode + ",msg " + msg);
    }
}
