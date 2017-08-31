package com.zhdhr0000.architecture.imagetransform;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.drawable.BitmapDrawable;
import android.support.annotation.Nullable;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by zhangyinghao on 2017/8/31.
 */

public class TestImageView extends View {


    private BitmapDrawable drawable1;
    private BitmapDrawable drawable2;
    Paint paint2;
    PorterDuffXfermode xfermode;
    Rect rect1;
    Rect rect2;
    Paint paint1;

    public TestImageView(Context context) {
        super(context);
    }

    public TestImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public TestImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        if (drawable1 != null && drawable2 != null) {
            setLayerType(LAYER_TYPE_SOFTWARE, null);
            if (paint1 == null) {
                paint1 = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint1.setFilterBitmap(true);
            }
            if (paint2 == null) {
                paint2 = new Paint(Paint.ANTI_ALIAS_FLAG);
                paint2.setFilterBitmap(true);
            }
            if (xfermode == null) {
                xfermode = new PorterDuffXfermode(PorterDuff.Mode.SRC_OUT);
            }
            if (rect1 == null) rect1 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
//            if (rect2 == null) rect2 = new Rect(0, 0, getMeasuredWidth(), getMeasuredHeight());
            canvas.drawBitmap(drawable1.getBitmap(), null, rect1, paint1);
//            paint.setXfermode(xfermode);
            paint2.setXfermode(xfermode);
            canvas.drawBitmap(drawable2.getBitmap(), null, rect1, paint2);
            paint2.setXfermode(null);
        } else {
            super.onDraw(canvas);
        }
    }

    public void setDrawable1(BitmapDrawable drawable1) {
        this.drawable1 = drawable1;
    }

    public void setDrawable2(BitmapDrawable drawable2) {
        this.drawable2 = drawable2;
    }
}
