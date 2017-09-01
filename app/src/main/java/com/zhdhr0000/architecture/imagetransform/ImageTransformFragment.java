package com.zhdhr0000.architecture.imagetransform;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
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
    @BindView(R.id.mask_view)
    View maskView;

    BitmapDrawable wrap = null;
    boolean isChanging = false;
    BitmapDrawable white;

    @Override
    protected void initDataAndEvent() {
        wrap = (BitmapDrawable) mActivity.getResources().getDrawable(R.drawable.test2);
        white = (BitmapDrawable) mActivity.getResources().getDrawable(R.drawable.test2);
        seekbar.setMax(255);
        seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                changeBitmap(progress);
                maskView.setAlpha((255-progress)/255f);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
            }
        });

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mActivity, ImageTransformActivity.class));
            }
        });

        image1.setImageDrawable(wrap);
        if (white != null) {
            image2.setImageDrawable(white);
        } else {
            image2.setImageResource(R.mipmap.dog);
        }
        changeBitmap(255);
    }

    private void changeBitmap(int progress) {
        if (isChanging) {
            return;
        }
        isChanging = true;
        Bitmap copyBitmap = Bitmap.createBitmap(white.getBitmap().getWidth(), white.getBitmap().getHeight(), Bitmap.Config.ARGB_8888);
        Rect rect = new Rect(0, 0, white.getBitmap().getWidth(), white.getBitmap().getHeight());
        Canvas canvas = new Canvas(copyBitmap);
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        canvas.drawRGB(progress, progress, progress);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
        canvas.drawBitmap(white.getBitmap(), rect, rect, paint);
        paint.setXfermode(null);
        image2.setImageBitmap(copyBitmap);
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
