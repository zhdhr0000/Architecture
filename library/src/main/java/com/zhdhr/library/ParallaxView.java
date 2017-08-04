package com.zhdhr.library;

import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by zhangyinghao on 2017/8/3.
 */

public class ParallaxView extends FrameLayout {
    public static int ALIGNMENT_SCREEN = 0;
    public static int ALIGNMENT_PARENT = 1;
    public static int DIRECTION_VERTICAL = -1;
    public static int DIRECTION_HORIZONTAL = 1;
    public static int DIRECTION_BOTH = 0;

    private int direction = DIRECTION_VERTICAL;//default direction is vertical.
    private int alignment = ALIGNMENT_SCREEN;//default alignment is screen.

    protected float maxOffsetX = 0.5f;//default max offset x is 0.5f.
    protected float maxOffsetY = 0.5f;//default max offset y is 0.5f.

    private boolean autoCachingChildDrawable = true;
    protected ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    protected ViewTreeObserver.OnDrawListener mOnDrawListener;
    protected ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;

    public ParallaxView(Context context) {
        super(context);
        init();
    }

    public ParallaxView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ParallaxView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
    }

    private void init() {
        //TODO
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (autoCachingChildDrawable) {
            setChildrenDrawnWithCacheEnabled(true);
        }
        mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                setupParallax();
            }
        };

        getViewTreeObserver().addOnGlobalLayoutListener(mOnGlobalLayoutListener);
        getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().addOnDrawListener(mOnDrawListener);
        }
    }

    private void setupParallax() {
        //TODO
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (autoCachingChildDrawable) {
            setChildrenDrawingCacheEnabled(false);
        }
        getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
            getViewTreeObserver().removeOnDrawListener(mOnDrawListener);
        } else {
            getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
        }
    }


}
