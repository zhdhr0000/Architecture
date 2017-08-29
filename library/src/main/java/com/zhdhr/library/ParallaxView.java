package com.zhdhr.library;

import android.content.Context;
import android.graphics.Point;
import android.support.annotation.IntRange;
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

    private boolean autoCachingChildDrawable = true;
    protected ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;
    protected View mView;
    private boolean canParallax;
    private int screenHeight;
    private int screenWidth;

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
    public void onViewAdded(View child) {
        super.onViewAdded(child);
        Log.e(TAG, "onViewAdded");
        if (getChildCount() == 1) {
            canParallax = true;
            mView = getChildAt(0);
        } else {
            Log.e(this.getClass().getSimpleName(), "parallaxview can have only one child view. getChildCount = " + getChildCount());
            canParallax = false;
        }
    }

    private void init() {
        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = wm.getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        screenHeight = size.y;
        screenWidth = size.x;
        wm = null;
    }

    public void setParallaxDirection(@IntRange(from = -1, to = 1) int newdirection) {
        direction = newdirection;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        Log.e(TAG, "onattach");
        if (mView == null) {
            if (getChildCount() == 1) {
                canParallax = true;
                mView = getChildAt(0);
            }
        }
        if (canParallax) {
            Log.e(TAG, "onattach canparallax");
            if (autoCachingChildDrawable) {
                setChildrenDrawnWithCacheEnabled(true);
            }

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
        if (canParallax) {
            int[] location = new int[2];
            getLocationOnScreen(location);
            if (direction == DIRECTION_VERTICAL) {
                float top = location[1] * (mView.getMeasuredHeight() - getMeasuredHeight()) / (getMeasuredHeight() + screenHeight);
                mView.setScrollY((int) top);
                Log.e(TAG, "do parallax vertical");
            } else if (direction == DIRECTION_HORIZONTAL) {
                float left = location[0] * (mView.getMeasuredWidth() - getMeasuredWidth()) / (getMeasuredWidth() + screenWidth);
                mView.setScrollX((int) left);
                Log.e(TAG, "do parallax horizontal");
            } else if (direction == DIRECTION_BOTH) {
                float top = location[1] * (mView.getMeasuredHeight() - getMeasuredHeight()) / (getMeasuredHeight() + screenHeight);
                float left = location[0] * (mView.getMeasuredWidth() - getMeasuredWidth()) / (getMeasuredWidth() + screenWidth);
                mView.scrollTo((int) top, (int) left);
            }
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
        }
    }
}
