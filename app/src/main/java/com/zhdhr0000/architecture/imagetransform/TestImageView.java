package com.zhdhr0000.architecture.imagetransform;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.DrawFilter;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.zhdhr0000.architecture.R;

import jp.co.cyberagent.android.gpuimage.GPUImage;
import jp.co.cyberagent.android.gpuimage.GPUImageMultiplyBlendFilter;

/**
 * Created by zhangyinghao on 2017/8/31.
 */

public class TestImageView extends ImageView {


    private BitmapDrawable drawable1;
    private BitmapDrawable drawable2;
    Paint paint2;
    PorterDuff.Mode mode = PorterDuff.Mode.SRC;
    PorterDuffXfermode xfermode;
    Rect rect1;
    Rect rect2;
    Paint paint1;
    GPUImage gpuImage;
    PorterDuffXfermode xfermodenormal;

    public TestImageView(Context context) {
        super(context);
        init();
    }

    public TestImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    public TestImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        drawable1 = (BitmapDrawable) getResources().getDrawable(R.drawable.test3);
        drawable2 = (BitmapDrawable) getResources().getDrawable(R.drawable.test2);
        paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint1.setFilterBitmap(true);
        paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint2.setFilterBitmap(true);
        xfermode = new PorterDuffXfermode(mode);
        xfermodenormal = new PorterDuffXfermode(PorterDuff.Mode.SRC);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        if (drawable1 != null && drawable2 != null) {
            setLayerType(LAYER_TYPE_HARDWARE, null);
            if (rect1 == null) rect1 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
//            canvas.drawBitmap(drawable1.getBitmap(), null, rect1, paint2);
            canvas.drawColor(Color.parseColor("#cccccc"));
            paint2.setXfermode(xfermode);
            canvas.drawBitmap(drawable2.getBitmap(), null, rect1, paint2);
            paint2.setXfermode(null);

        } else {
            super.onDraw(canvas);
        }
    }

    public PorterDuff.Mode getMode() {
        return mode;
    }

    public void setMode(PorterDuff.Mode mode) {
        this.mode = mode;
        xfermode = new PorterDuffXfermode(mode);
    }

    public void setDrawable1(BitmapDrawable drawable1) {
        this.drawable1 = drawable1;
    }

    public void setDrawable2(BitmapDrawable drawable2) {
        this.drawable2 = drawable2;
    }
}
