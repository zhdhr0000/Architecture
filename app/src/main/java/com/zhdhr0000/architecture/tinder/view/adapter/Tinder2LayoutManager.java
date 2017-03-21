package com.zhdhr0000.architecture.tinder.view.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by win7 on 2017/3/21.
 */

public class Tinder2LayoutManager extends RecyclerView.LayoutManager {

    private int itemWidth;
    private Context mContext;

    public Tinder2LayoutManager(Context context, int width) {
        mContext = context;
        itemWidth = width;
    }

    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(itemWidth, itemWidth);
    }

    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        detachAndScrapAttachedViews(recycler);
        for (int i = 0; i < getItemCount(); i++) {
            View view = recycler.getViewForPosition(i);
            measureChildWithMargins(view, 0, 0);
            addView(view, 0);
            layoutDecorated(view, 0, 0, 0, 0);
            if (i != 0) {
                view.setScaleX(0.8f);
                view.setScaleY(0.8f);
            }
        }
    }

    @Override
    public boolean canScrollVertically() {
        return true;
    }


}
