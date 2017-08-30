package com.zhdhr0000.architecture.imagetransform;

import android.graphics.PorterDuff;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.view.Choreographer;
import android.widget.ImageView;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/30.
 */

public class ImageTransformFragment extends BaseFragment<ImageTransform.Presenter> implements ImageTransform.View {

    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;

    @Override
    protected void initDataAndEvent() {
        Drawable drawable = mActivity.getResources().getDrawable(R.drawable.test3);
        image1.setImageDrawable(drawable);
        LayerDrawable wrap;
        wrap.get
//        Drawable wrap = DrawableCompat.wrap(drawable);
//        DrawableCompat.setTintMode(wrap, PorterDuff.Mode.SRC_OVER);
//        wrap;
//        DrawableCompat.setTint(wrap, getResources().getColor(R.color.black));
//        image2.setImageDrawable(wrap);
    }

    @Override
    public void showError(int type, String msg) {
        showToast(msg);
    }

    @Override
    protected ImageTransform.Presenter initPresenter() {
        return new ImageTransformPresenter();
    }

    @Override
    protected int getLayoutID() {
        return R.layout.fragment_image_transform;
    }
}
