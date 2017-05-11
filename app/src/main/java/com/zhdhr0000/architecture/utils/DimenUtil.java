package com.zhdhr0000.architecture.utils;

import android.content.Context;
import android.util.DisplayMetrics;

/**
 * Created by zhangyinghao on 2017/5/11.
 */

public class DimenUtil {

    // 获取屏幕宽 px
    public static int getScreenWidth(Context context) {
        DisplayMetrics metric =
                context.getResources().getDisplayMetrics();
        int width = metric.widthPixels;     // 屏幕宽度（像素）
        return width;
    }

    // 获取屏幕高 px
    public static int getScreenHeight(Context context) {
        DisplayMetrics metric =
                context.getResources().getDisplayMetrics();
        int height = metric.heightPixels;   // 屏幕高度（像素）
        return height;
    }

    //获取手机dpi
    public static int getDensityDpi(Context context) {
        DisplayMetrics metric =
                context.getResources().getDisplayMetrics();
        int densityDpi = metric.densityDpi;  // 屏幕密度DPI（120 / 160 / 240）
        return densityDpi;
    }

    //获取屏幕系数
    public static float getDensity(Context context) {
        DisplayMetrics metric =
                context.getResources().getDisplayMetrics();
        float density = metric.density;      // 屏幕密度（0.75 / 1.0 / 1.5）
        return density;
    }

    // dp -> px
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    // px -> dp
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }


}
