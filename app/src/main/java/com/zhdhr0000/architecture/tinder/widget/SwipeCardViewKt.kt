package com.zhdhr0000.architecture.tinder.widget

import android.content.Context
import android.database.DataSetObserver
import android.graphics.PointF
import android.util.AttributeSet
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.Adapter
import android.widget.AdapterView
import android.widget.BaseAdapter
import android.widget.FrameLayout


/**
 * Created by zhangyinghao on 2017/4/24.
 */
open class SwipeCardViewKt(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int, defStyleRes: Int) : AdapterView<Adapter>(context, attributeSet, defStyleAttr, defStyleRes) {

    var mOnFlingListener: OnFlingListener? = null

    val ROTATION_DEGREES = 15f
    var MAX_VISIBLE = 3
    var widthMeasureSpec: Int = 0
    var heightMeasureSpec: Int = 0
    var mAdapter: Adapter? = null
    var mDataSetObserver: DataSetObserver? = null
    var mInLayout: Boolean = false
    var LAST_OBJECT_IN_STACK = 0
    var mActiveView: View? = null
    var mOnItemClickListener: OnItemClickListener? = null
    var flingCardListener: FlingCardListenerKt? = null
    var mLastTouchPoint: PointF? = null
    var MIN_ADAPTER_STACK = 5

    constructor(context: Context, attributeSet: AttributeSet?) : this(context, attributeSet, 0)
    constructor(context: Context, attributeSet: AttributeSet?, defStyleAttr: Int) : this(context, attributeSet, defStyleAttr, 0)
    constructor(context: Context) : this(context, null)

    override fun getSelectedView(): View? {
        return this.selectedView
    }

    override fun getAdapter(): Adapter? {
        return mAdapter
    }

    override fun setSelection(position: Int) {}

    override fun setAdapter(adapter: Adapter?) {
        mAdapter?.unregisterDataSetObserver(mDataSetObserver)
        mDataSetObserver = null
        mAdapter = adapter
        mDataSetObserver = object : DataSetObserver() {
            override fun onChanged() {
                requestLayout()
            }

            override fun onInvalidated() {
                requestLayout()
            }
        }
        mAdapter?.registerDataSetObserver(mDataSetObserver)
    }

    override fun requestLayout() {
        if (!mInLayout) {
            super.requestLayout()
        }
    }

    override fun onLayout(changed: Boolean, left: Int, top: Int, right: Int, bottom: Int) {
        super.onLayout(changed, left, top, right, bottom)
        if (mAdapter == null) return
        mInLayout = true
        if (mAdapter?.count == 0) {
            removeAllViewsInLayout()
        } else {
            val topCard = getChildAt(LAST_OBJECT_IN_STACK)
            if (mActiveView != null && topCard != null && topCard === mActiveView) {
                if (mLastTouchPoint == null || mLastTouchPoint != flingCardListener?.getLastPoint()) {
                    mLastTouchPoint = flingCardListener?.getLastPoint()
                    removeViewsInLayout(0, LAST_OBJECT_IN_STACK)
                    layoutChildren(1, count)
                } else {
                    removeAllViewsInLayout()
                    layoutChildren(0, count)
                    setTopView()
                }
            }
        }
        mInLayout = false
        if (count <= MIN_ADAPTER_STACK) mOnFlingListener?.onAdapterAboutToEmpty(count)
    }

    private fun layoutChildren(startingIndex: Int, adapterCount: Int) {
        var startIndex = startingIndex
        while (startIndex < Math.min(adapterCount, MAX_VISIBLE)) {
            val newUnderChild = mAdapter?.getView(startingIndex, null, this)
            if (newUnderChild?.visibility != View.GONE) {
                makeAndAddView(newUnderChild)
                LAST_OBJECT_IN_STACK = startingIndex
            }
            startIndex++
        }
    }

    private fun setTopView() {
        if (childCount > 0) {

            mActiveView = getChildAt(LAST_OBJECT_IN_STACK)
            if (mActiveView != null) {

                flingCardListener = FlingCardListenerKt(mActiveView!!, mAdapter?.getItem(0)!!,
                        ROTATION_DEGREES.toDouble(), object : FlingCardListenerKt.FlingListener {

                    override fun onCardExited() {
                        mActiveView = null
                        mOnFlingListener?.removeFirstObjectInAdapter()
                    }

                    override fun leftExit(dataObject: Any) {
                        mOnFlingListener?.onLeftCardExit(dataObject)
                    }

                    override fun rightExit(dataObject: Any) {
                        mOnFlingListener?.onRightCardExit(dataObject)
                    }

                    override fun onClick(dataObject: Any) {
                        mOnItemClickListener?.onItemClicked(0, dataObject)
                    }

                    override fun onScroll(scrollProgressPercent: Float) {
                        mOnFlingListener?.onScroll(scrollProgressPercent)
                    }
                })

                mActiveView?.setOnTouchListener(flingCardListener)
            }
        }
    }


    private fun makeAndAddView(child: View?) {
        val lp = child?.layoutParams as FrameLayout.LayoutParams
        addViewInLayout(child, 0, lp, true)

        val needToMeasure = child.isLayoutRequested
        if (needToMeasure) {
            val childWidthSpec = ViewGroup.getChildMeasureSpec(widthMeasureSpec,
                    paddingLeft + paddingRight + lp.leftMargin + lp.rightMargin,
                    lp.width)
            val childHeightSpec = ViewGroup.getChildMeasureSpec(heightMeasureSpec,
                    paddingTop + paddingBottom + lp.topMargin + lp.bottomMargin,
                    lp.height)
            child.measure(childWidthSpec, childHeightSpec)
        } else {
            cleanupLayoutState(child)
        }
        for (i in 0..10 step 2){

        }
        val w = child.measuredWidth
        val h = child.measuredHeight

        var gravity = lp.gravity
        if (gravity == -1) {
            gravity = Gravity.TOP or Gravity.START
        }

        var layoutDirection = 0
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.JELLY_BEAN_MR1) {
            layoutDirection = getLayoutDirection()
        }
        val absoluteGravity: Int
        if (layoutDirection == 0) {
            absoluteGravity = gravity
        } else {
            absoluteGravity = Gravity.getAbsoluteGravity(gravity, layoutDirection)
        }
        val verticalGravity = gravity and Gravity.VERTICAL_GRAVITY_MASK

        val childLeft: Int
        val childTop: Int
        when (absoluteGravity and Gravity.HORIZONTAL_GRAVITY_MASK) {
            Gravity.CENTER_HORIZONTAL -> childLeft = (width + paddingLeft - paddingRight - w) / 2 + lp.leftMargin - lp.rightMargin
            Gravity.END -> childLeft = width + paddingRight - w - lp.rightMargin
            Gravity.START -> childLeft = paddingLeft + lp.leftMargin
            else -> childLeft = paddingLeft + lp.leftMargin
        }
        when (verticalGravity) {
            Gravity.CENTER_VERTICAL -> childTop = (height + paddingTop - paddingBottom - h) / 2 + lp.topMargin - lp.bottomMargin
            Gravity.BOTTOM -> childTop = height - paddingBottom - h - lp.bottomMargin
            Gravity.TOP -> childTop = paddingTop + lp.topMargin
            else -> childTop = paddingTop + lp.topMargin
        }
        child.layout(childLeft, childTop, childLeft + w, childTop + h)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        this.widthMeasureSpec = widthMeasureSpec
        this.heightMeasureSpec = heightMeasureSpec
    }

    override fun generateLayoutParams(attrs: AttributeSet?): LayoutParams {
        return FrameLayout.LayoutParams(context, attrs)
    }

    interface OnItemClickListener {
        fun onItemClicked(itemPostion: Int, obj: Any)
    }

    open interface OnFlingListener {
        fun removeFirstObjectInAdapter()

        fun onLeftCardExit(dataObject: Any)

        fun onRightCardExit(dataObject: Any)

        fun onAdapterAboutToEmpty(itemsInAdapter: Int)

        fun onScroll(scrollProgressPercent: Float)
    }

    fun getTopCardListener(): FlingCardListenerKt {
        return flingCardListener!!
    }

    fun setOnFlingListener(mOnFlingListener: OnFlingListener) {
        this.mOnFlingListener = mOnFlingListener
    }

    fun setOnItemClickListener(mOnItemClickListener: OnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener
    }

    fun setMaxVisible(MAX_VISIBLE: Int) {
        this.MAX_VISIBLE = MAX_VISIBLE
    }

    fun setMinStackInAdapter(MIN_ADAPTER_STACK: Int) {
        this.MIN_ADAPTER_STACK = MIN_ADAPTER_STACK
    }
}
