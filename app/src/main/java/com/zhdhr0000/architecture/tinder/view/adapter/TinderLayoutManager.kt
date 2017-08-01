package com.zhdhr0000.architecture.tinder.view.adapter

import android.app.Activity
import android.content.Context
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.View

/**
 * Created by win7 on 2017/3/21.
 */
open class TinderLayoutManager(context: Context?, orientation: Int, reverseLayout: Boolean, val itemWidth: Int) : LinearLayoutManager(context, orientation, reverseLayout) {

    override fun generateDefaultLayoutParams(): RecyclerView.LayoutParams {
        return RecyclerView.LayoutParams(width, height)
    }

    override fun onLayoutChildren(recycler: RecyclerView.Recycler?, state: RecyclerView.State?) {
        detachAndScrapAttachedViews(recycler)
        for (i in (0..itemCount - 1)) {
            val child: View? = recycler?.getViewForPosition(i)
            child?.layoutParams?.width = itemWidth
            child?.layoutParams?.height = child?.width
            measureChildWithMargins(child, 0, 0)
            addView(child)
            val width: Int = getDecoratedMeasuredWidth(child)
            val height: Int = getDecoratedMeasuredHeight(child)
            layoutDecorated(child, 0, 0, width, height)
            if (i < itemCount - 1) {
                child?.scaleX = 0.8f
                child?.scaleY = 0.8f
            }
        }
    }
}
