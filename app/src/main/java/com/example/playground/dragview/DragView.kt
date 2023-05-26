package com.example.playground.dragview

import android.animation.Animator
import android.animation.ObjectAnimator
import android.content.Context
import android.util.AttributeSet
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.ViewGroup
import android.view.animation.DecelerateInterpolator
import android.widget.LinearLayout

import com.zj.tools.mylibrary.ZJLog


class DragView @kotlin.jvm.JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null
) : LinearLayout(context, attrs) {

    // 当拖拽结束时是否需要自动移动到父视图的边缘
    private val autoAnimToSide: Boolean = true
    private var downY: Float = 0f
    private var downX: Float = 0f
    private var isLongPressThisTime = false
    private var gesture: GestureDetector
    private val simpleOnGestureListener = object : GestureDetector.SimpleOnGestureListener() {
        override fun onLongPress(e: MotionEvent) {
            super.onLongPress(e)
            isLongPressThisTime = true
        }
    }

    init {
        gesture = GestureDetector(context, simpleOnGestureListener)
        // onInterceptTouchEvent 返回true时, parent 能触发本视图的dispatchTouchEvent方法
        isClickable = true
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        // 监听长按的触发
        ev?.let { gesture.onTouchEvent(it) }
        return super.dispatchTouchEvent(ev)
    }

    override fun onInterceptTouchEvent(event: MotionEvent?): Boolean {
        // 记录起点坐标
        when (event?.action) {
            MotionEvent.ACTION_DOWN -> {
                downX = event.x
                downY = event.y
            }
        }
        return isLongPressThisTime
    }

    override fun onTouchEvent(event: MotionEvent?): Boolean {
        // 触发移动的动画
        event?.let {
            when (event.action) {
                MotionEvent.ACTION_MOVE -> {
                    if (isLongPressThisTime) {
                        translationX = x + (event.x - downX)
                        translationY = y + (event.y - downY)
                        ZJLog.d("translationX=${translationX}, translationY=${translationY}")
                        ZJLog.d("x=${x}, y=${y}  downX=${downX}, downY=${downY}    event.x=${event.x}, event.y=${event.y}")
                    }
                }
                MotionEvent.ACTION_UP -> {
                    isLongPressThisTime = false
                    animToParentSideIfNeed()
                }
                MotionEvent.ACTION_CANCEL -> {
                    isLongPressThisTime = false
                    animToParentSideIfNeed()
                }
            }
        }
        return isLongPressThisTime
    }


    private fun animToParentSideIfNeed() {
        if (!autoAnimToSide) {
            return
        }
        isClickable = false
        if (parent is ViewGroup) {
            val offsetCenterX = x + (width / 2) - ((parent as ViewGroup).width / 2)
            val targetTranslationX =
                if (offsetCenterX <= 0) 0 else ((parent as ViewGroup).width - width)
            ZJLog.d("offsetCenterX=${offsetCenterX}")

            val animator = ObjectAnimator.ofFloat(
                this,
                "translationX",
                translationX,
                targetTranslationX.toFloat()
            )
            animator.duration = 300
            animator.interpolator = DecelerateInterpolator()
            animator.addListener(object : Animator.AnimatorListener{
                override fun onAnimationStart(animation: Animator) {
                }

                override fun onAnimationEnd(animation: Animator) {
                    isClickable=true
                }

                override fun onAnimationCancel(animation: Animator) {
                    isClickable = true
                }

                override fun onAnimationRepeat(animation: Animator) {
                }

            })
            animator.start()
        }
    }
}