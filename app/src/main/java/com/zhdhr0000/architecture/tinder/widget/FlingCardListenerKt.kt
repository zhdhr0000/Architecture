package com.zhdhr0000.architecture.tinder.widget

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.util.Log
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.AccelerateInterpolator
import android.view.animation.OvershootInterpolator

/**
 * Created by zhangyh on 2017/3/22.
 */
class FlingCardListenerKt(var frame: View, itemAtPosition: Any, rotation_degrees: Double, var flingListener: FlingListener) : View.OnTouchListener {

    internal val TAG = FlingCardListenerKt::class.simpleName
    internal val INVALID_POINTER_ID = -1

    val TOUCH_ABOVE = 0
    val TOUCH_BELOW = 1
    val MAX_COS = Math.cos(Math.toRadians(45.0)).toFloat()

    var mActivePointerId = 0
    var aDownTouchX = 0f
    var aDownTouchY = 0f
    var aPosX = 0f
    var aPosY = 0f
    var touchPosition = 0
    var isAnimationRunning = false

    var halfWidth: Float
    var dataObject: Any
    var parentWidth: Int
    var BASE_ROTATION_DEGREES: Double

    constructor(frame: View, itemAtPosition: Any, flingListener: FlingListener) : this(frame, itemAtPosition, 15.0, flingListener)

    var objectX: Float = frame.x
    var objectY: Float = frame.y
    var objectH: Int = frame.height
    var objectW: Int = frame.width

    init {
        this.halfWidth = objectW / 2f
        this.dataObject = itemAtPosition
        this.parentWidth = (frame.parent as ViewGroup).width
        this.BASE_ROTATION_DEGREES = rotation_degrees
    }


    override fun onTouch(v: View?, event: MotionEvent?): Boolean {
        when (event?.action?.and(MotionEvent.ACTION_MASK)) {
            MotionEvent.ACTION_DOWN -> {
                mActivePointerId = event.getPointerId(0)
                var x: Float = 0f
                var y: Float = 0f
                var success = false
                try {
                    x = event.getX(mActivePointerId)
                    y = event.getY(mActivePointerId)
                    success = true
                } catch (e: IllegalArgumentException) {
                    Log.w(TAG, e)
                }
                if (success) {
                    aDownTouchX = x
                    aDownTouchY = y
                    if (aPosX == 0f) {
                        aPosX = frame.x
                    }
                    if (aPosY == 0f) {
                        aPosY = frame.y
                    }

                    if (y < objectH / 2) {
                        touchPosition = TOUCH_ABOVE
                    } else {
                        touchPosition = TOUCH_BELOW
                    }
                }
                v?.parent?.requestDisallowInterceptTouchEvent(true)
            }
            MotionEvent.ACTION_UP -> {
                mActivePointerId = INVALID_POINTER_ID
                resetCardViewOnStack()
                v?.parent?.requestDisallowInterceptTouchEvent(false)
            }
            MotionEvent.ACTION_POINTER_UP -> {
                val pointerIndex = event.action and MotionEvent.ACTION_POINTER_INDEX_MASK shr MotionEvent.ACTION_POINTER_INDEX_SHIFT
                val pointerId = event.getPointerId(pointerIndex)
                if (pointerId == mActivePointerId) {
                    val newPointerIndex = if (pointerIndex == 0) 1 else 0
                    mActivePointerId = event.getPointerId(newPointerIndex)
                }
            }
            MotionEvent.ACTION_MOVE -> {
                val pointerIndexMove = event.findPointerIndex(mActivePointerId)
                val xMove = event.getX(pointerIndexMove)
                val yMove = event.getY(pointerIndexMove)

                //from http://android-developers.blogspot.com/2010/06/making-sense-of-multitouch.html
                // Calculate the distance moved
                val dx = xMove - aDownTouchX
                val dy = yMove - aDownTouchY


                // Move the frame
                aPosX += dx
                aPosY += dy

                // calculate the rotation degrees
                val distobjectX = aPosX - objectX
                var rotation: Float = (BASE_ROTATION_DEGREES * 2f * distobjectX / parentWidth).toFloat()
                if (touchPosition == TOUCH_BELOW) {
                    rotation = -rotation
                }

                //in this area would be code for doing something with the view as the frame moves.
                frame.x = aPosX
                frame.y = aPosY
                frame.rotation = rotation
                flingListener.onScroll(getScrollProgressPercent())
            }
            MotionEvent.ACTION_CANCEL -> {
                mActivePointerId = INVALID_POINTER_ID
                v?.parent?.requestDisallowInterceptTouchEvent(false)
            }
            else -> {
            }
        }
        return true
    }

    fun resetCardViewOnStack(): Boolean {
        if (movedBeyondLeftBorder()) {
            onSelect(true, getExitPoint(-objectW), 100)
            flingListener.onScroll(-1f)
        } else if (movedBeyondRightBorder()) {
            onSelect(false, getExitPoint(parentWidth), 100)
            flingListener.onScroll(1f)
        } else {
            val abslMoveDistance = Math.abs(aPosX - objectX)
            aPosX = 0f
            aPosY = 0f
            aDownTouchX = 0f
            aDownTouchY = 0f
            frame.animate()
                    .setDuration(200)
                    .x(objectX)
                    .y(objectY)
                    .rotation(0f).interpolator = OvershootInterpolator(1.5F)
            flingListener.onScroll(0f)
            if (abslMoveDistance < 5) {
                flingListener.onClick(dataObject)
            }
        }
        return false
    }


    fun getScrollProgressPercent(): Float {
        if (movedBeyondLeftBorder()) {
            return -1f
        } else if (movedBeyondRightBorder()) {
            return 1f
        } else {
            val zeroToOneValue = (aPosX + halfWidth - leftBorder()) / (rightBorder() - leftBorder())
            return zeroToOneValue * 2f - 1f
        }
    }

    fun onSelect(isLeft: Boolean, exitY: Float, duration: Long) {
        isAnimationRunning = true
        var exitX: Float
        if (isLeft) {
            exitX = -objectW - getRotationWidthOffset()
        } else {
            exitX = parentWidth + getRotationWidthOffset()
        }
        frame.animate()
                .setDuration(duration)
                .setInterpolator(AccelerateInterpolator())
                .x(exitX)
                .y(exitY)
                .rotation(getExitRotation(isLeft))
                .setListener(object : AnimatorListenerAdapter() {
                    override fun onAnimationEnd(animation: Animator?) {
                        if (isLeft) {
                            flingListener.onCardExited()
                            flingListener.leftExit(dataObject)
                        } else {
                            flingListener.onCardExited()
                            flingListener.rightExit(dataObject)
                        }
                        isAnimationRunning = false
                    }
                })
    }

    fun getRotationWidthOffset(): Float = objectW / MAX_COS - objectW

    fun getExitRotation(isLeft: Boolean): Float {
        var rotation: Float = (BASE_ROTATION_DEGREES * 2f * (parentWidth - objectX) / parentWidth).toFloat()
        if (touchPosition == TOUCH_BELOW) {
            rotation = -rotation
        }
        if (isLeft) {
            rotation = -rotation
        }
        return rotation
    }

    fun getExitPoint(exitXPoint: Int): Float {
        val x = FloatArray(2)
        x[0] = objectX
        x[1] = aPosX
        val y = FloatArray(2)
        y[0] = objectY
        y[1] = aPosY
        val regression = LinearRegressionKt(x, y)
        return (regression.slope() * exitXPoint + regression.intercept()).toFloat()
    }

    fun movedBeyondLeftBorder(): Boolean = aPosX + halfWidth < leftBorder()

    fun movedBeyondRightBorder(): Boolean = aPosX + halfWidth > rightBorder()

    fun leftBorder(): Float = parentWidth / 4f

    fun rightBorder(): Float = 3 * parentWidth / 4f

    fun setRotateDegree(degree: Double) {
        BASE_ROTATION_DEGREES = degree
    }

    fun selectLeft() {
        if (!isAnimationRunning) onSelect(true, objectY, 200)
    }

    fun selectRight() {
        if (!isAnimationRunning) onSelect(false, objectY, 200)
    }

    interface FlingListener {
        fun onCardExited()
        fun leftExit(dataObject: Any)
        fun rightExit(dataObject: Any)
        fun onClick(dataObject: Any)
        fun onScroll(scrollProgressPercent: Float)
    }
}