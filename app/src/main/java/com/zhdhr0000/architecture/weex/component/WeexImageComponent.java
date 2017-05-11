package com.zhdhr0000.architecture.weex.component;

import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.ImageView;

import com.taobao.weex.WXSDKInstance;
import com.taobao.weex.dom.WXDomObject;
import com.taobao.weex.ui.component.WXImage;
import com.taobao.weex.ui.component.WXVContainer;

/**
 * Created by zhangyinghao on 2017/5/11.
 */

public class WeexImageComponent extends WXImage {

    public WeexImageComponent(WXSDKInstance instance, WXDomObject node, WXVContainer parent, boolean lazy) {
        super(instance, node, parent);
    }

    public WeexImageComponent(WXSDKInstance instance, WXDomObject node, WXVContainer parent) {
        super(instance, node, parent);
    }

    @Override
    protected ImageView initComponentHostView(@NonNull Context context) {
        WeexImageView view = new WeexImageView(context);
        view.setScaleType(ImageView.ScaleType.FIT_XY);
        return view;
    }
}
