package com.zhdhr.library;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;

/**
 * Created by zhangyinghao on 2017/8/3.
 */

public class ParallaxView extends FrameLayout {
    private boolean canParallax;
    private View mView;
    private ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener;
    private ViewTreeObserver.OnDrawListener mOnDrawListener;
    private ViewTreeObserver.OnScrollChangedListener mOnScrollChangedListener;

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

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public ParallaxView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init();
    }

    private void init() {
        if (getChildCount() == 1) {
            canParallax = true;
            mView = getChildAt(0);
        } else {
            canParallax = false;
        }
    }

    private boolean canParallaxChild() {
        return canParallax && mView != null;
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        if (canParallaxChild()) {
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
    }

    private void setupParallax() {
        //TODO
    }


    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        getViewTreeObserver().removeOnScrollChangedListener(mOnScrollChangedListener);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            getViewTreeObserver().removeOnGlobalLayoutListener(mOnGlobalLayoutListener);
            getViewTreeObserver().removeOnDrawListener(mOnDrawListener);
        } else {
            getViewTreeObserver().removeGlobalOnLayoutListener(mOnGlobalLayoutListener);
        }
    }


}
