package com.iwdael.immersive

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */


fun createStatusBarLayoutParam(orientation: Orientation): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        gravity = orientation.gravityOfStatusBar()
    }
}


fun createNavigationBarLayoutParam(orientation: Orientation): FrameLayout.LayoutParams {
    return (
            if (orientation == Orientation.ORIENTATION_0 || orientation == Orientation.ORIENTATION_180) {
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.MATCH_PARENT,
                    FrameLayout.LayoutParams.WRAP_CONTENT
                )
            } else {
                FrameLayout.LayoutParams(
                    FrameLayout.LayoutParams.WRAP_CONTENT,
                    FrameLayout.LayoutParams.MATCH_PARENT
                )
            }
            )
        .apply {
            gravity = orientation.gravityOfNavigationBar()
        }
}


fun Context.createContentLayoutParam(
    orientation: Orientation,
    hideStateBar: Boolean,
    hideNavigationBar: Boolean
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).let {
        if (!hideStateBar)
            it.topMargin = getStatusBarHeight()
        if (!hideNavigationBar) {
            when (orientation.gravityOfNavigationBar()) {
                Gravity.BOTTOM -> {
                    it.bottomMargin = getNavigationBarHeight()
                }
                Gravity.START -> {
                    it.leftMargin = getNavigationBarHeight()
                }
                Gravity.END -> {
                    it.rightMargin = getNavigationBarHeight()
                }
            }
        }
        it
    }
}

