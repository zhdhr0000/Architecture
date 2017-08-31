package com.zhdhr0000.architecture.imagetransform;

import android.graphics.Color;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.SeekBar;

import com.zhdhr0000.architecture.R;
import com.zhdhr0000.architecture.base.BaseFragment;

import butterknife.BindView;

/**
 * Created by zhangyinghao on 2017/8/30.
 */

public class ImageTransformFragment extends BaseFragment<ImageTransform.Presenter> implements ImageTransform.View {

    public static final String TAG = ImageTransformFragment.class.getSimpleName();

    @BindView(R.id.image1)
    ImageView image1;
    @BindView(R.id.image2)
    ImageView image2;
    @BindView(R.id.seekbar)
    SeekBar seekbar;

    BitmapDrawable wrap = null;
    boolean isChanging = false;
    BitmapDrawable white;

    @Override
    protected void initDataAndEvent() {
        wrap = (BitmapDrawable) mActivity.getResources().getDrawable(R.drawable.test3);
        white = (BitmapDrawable) mActivity.getResources().getDrawable(R.drawable.test2);
//        seekbar.setMax(18);
//        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
//            @Override
//            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
//                changeBitmap(progress);
//            }
//
//            @Override
//            public void onStartTrackingTouch(SeekBar seekBar) {
//            }
//
//            @Override
//            public void onStopTrackingTouch(SeekBar seekBar) {
//            }
//        });

        image1.setImageDrawable(wrap);
        white.getPaint().setFilterBitmap(true);
        white.getPaint().setColorFilter(new PorterDuffColorFilter(Color.parseColor("#eeFFFFFF"), PorterDuff.Mode.DST_OUT));
        image2.setImageDrawable(white);
//        if (wrap != null) {
//            image2.setImageDrawable(wrap);
//        } else {
//            image2.setImageResource(R.mipmap.dog);
//        }
//        changeBitmap(0);
    }

    private void changeBitmap(int progress) {
        if (isChanging) {
            return;
        }
        isChanging = true;
//        image2.setDrawable1(wrap);
//        image2.setDrawable2(white);

//        Bitmap copyBitmap = Bitmap.createBitmap(wrap.getBitmap().getWidth(), wrap.getBitmap().getHeight(), Bitmap.Config.ARGB_8888);
//        Rect rect = new Rect(0, 0, wrap.getBitmap().getWidth(), wrap.getBitmap().getHeight());
//        Canvas canvas = new Canvas(copyBitmap);
//        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
//        canvas.drawColor(Color.parseColor("#FFd400"));
//        int layer = canvas.saveLayer(0, 0, wrap.getBitmap().getWidth(), wrap.getBitmap().getHeight(), paint, Canvas.ALL_SAVE_FLAG);
//        canvas.drawBitmap(wrap.getBitmap(), rect, rect, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
//        canvas.drawBitmap(white.getBitmap(), rect, rect, paint);
//        canvas.restore();
//        paint.setXfermode(null);
//        canvas.restoreToCount(layer);
//        image2.setImageBitmap(copyBitmap);
        isChanging = false;
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
