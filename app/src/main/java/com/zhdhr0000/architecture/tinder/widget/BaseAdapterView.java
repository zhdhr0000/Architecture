package com.zhdhr0000.architecture.tinder.widget;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.AttributeSet;
import android.widget.AdapterView;

/**
 * Created by win7 on 2017/3/21.
 */

public abstract class BaseAdapterView extends AdapterView {
    protected int widthMeasureSpec;
    protected int heightMeasureSpec;
    public BaseAdapterView(Context context) {
        super(context);
    }

    public BaseAdapterView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public BaseAdapterView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public BaseAdapterView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.widthMeasureSpec = widthMeasureSpec;
        this.heightMeasureSpec = heightMeasureSpec;
    }
}
