package com.zhdhr.library;

import android.content.Context;
import android.graphics.Point;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Display;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.FrameLayout;

/**
 * Created by zhangyinghao on 2017/8/3.
 */

public class ParallaxView extends FrameLayout {

    public static final String TAG = ParallaxView.class.getSimpleName();

    public static int DIRECTION_VERTICAL = -1;
    public static int DIRECTION_HORIZONTAL = 1;
    public static int DIRECTION_BOTH = 0;

    private int direction = DIRECTION_VERTICAL;//default direction is vertical.

//    protected float maxOffsetX = 0.5f;//default max offset x is 0.5f.
//    protected float maxOffsetY = 0.5f;//default max offset y is 0.5f.

    private boolean autoCachingChildDrawable = true;
    protected ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    protected ViewTreeObserver.OnDrawListener mOnDrawListener;
    protected ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    protected View mView;
    private boolean canParallax;
    private int screenHeight;
    private int screenWidth;

    private int width;
    private int height;

    private float offsetParam;
    private float offsetRatio;
    private boolean animating = false;

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
        width = MeasureSpec.getSize(widthMeasureSpec);
        height = MeasureSpec.getSize(heightMeasureSpec);
    }

    private void init() {
        if (getChildCount() == 1) {
            canParallax = true;
            mView = getChildAt(0);
        } else {
            Log.e(this.getClass().getSimpleName(), "parallaxview can have only one child view. getChildCount = " + getChildCount());
            canParallax = false;
        }

        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        wm = null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (mView == null) {
            if (getChildCount() == 1) {
                canParallax = true;
                mView = getChildAt(0);
            } else {
                mView = null;
                canParallax = false;
            }
        }
        Log.e(TAG, "onattach");
        if (canParallax) {
            Log.e(TAG, "onattach canparallax");
            if (autoCachingChildDrawable) {
                setChildrenDrawnWithCacheEnabled(true);
            }

//        mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                setupParallax();
//            }
//        };

            mOnScrollChangedListener = new ViewTreeObserver.OnScrollChangedListener() {
                @Override
                public void onScrollChanged() {
                    parallaxChildView();
                }
            };

            getViewTreeObserver().addOnScrollChangedListener(mOnScrollChangedListener);
        }
    }

    private void parallaxChildView() {
        Log.e(TAG, "parallax function");
        if (canParallax && !animating) {
//            offsetRatio = (screenHeight + mView.getMeasuredHeight()) / (screenHeight + height);
//            offsetParam = screenHeight - screenHeight * offsetRatio;
            int[] location = new int[2];
            getLocationOnScreen(location);
            float top = location[1] * (mView.getMeasuredHeight() - screenHeight) / (height + screenHeight);
            mView.setTop((int) top);
            Log.e(TAG, "do parallax");
        }
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Log.e(TAG, "ondetach");
        if (canParallax) {
            Log.e(TAG, "ondetach canparallax");
            if (autoCachingChildDrawable) {
                setChildrenDrawingCacheEnabled(false);
            }
            getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
//            getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
//            getViewTreeObserver().removeOnDrawListener(mOnDrawListener);
//        } else {
//            getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
//        }
        }
    }
}
