package com.iwdael.immersive

import android.app.Activity
import android.view.Gravity
import android.widget.FrameLayout
import androidx.appcompat.widget.ContentFrameLayout

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */

fun Activity.createStatusViewLayoutParamsVertical(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
}

fun Activity.createNavigationViewLayoutParamsVertical(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    ).apply {
        gravity = Gravity.BOTTOM
    }
}

fun Activity.createContentViewLayoutParamsVertical(
    hideStateBar: Boolean,
    hideNavigationBar: Boolean
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).let {
        if (!hideStateBar)
            it.topMargin = getStatusBarHeight()
        if (!hideNavigationBar)
            it.bottomMargin = getNavigationBarHeight()
        it
    }
}

fun Activity.createStatusViewLayoutParamsAngle90(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
}

fun Activity.createNavigationViewLayoutParamsAngle90(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).apply { gravity = Gravity.END }
}


fun Activity.createContentViewLayoutParamsAngle90(
    hideStateBar: Boolean,
    hideNavigationBar: Boolean
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).let {
        if (!hideStateBar)
            it.topMargin = getStatusBarHeight()
        if (!hideNavigationBar)
            it.rightMargin = getNavigationBarHeight()
        it
    }
}


fun Activity.createStatusViewLayoutParamsAngle270(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.WRAP_CONTENT
    )
}

fun Activity.createNavigationViewLayoutParamsAngle270(): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.WRAP_CONTENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).apply { gravity = Gravity.START }
}

fun Activity.createContentViewLayoutParamsAngle270(
    hideStateBar: Boolean,
    hideNavigationBar: Boolean
): FrameLayout.LayoutParams {
    return FrameLayout.LayoutParams(
        FrameLayout.LayoutParams.MATCH_PARENT,
        FrameLayout.LayoutParams.MATCH_PARENT
    ).let {
        if (!hideStateBar)
            it.topMargin = getStatusBarHeight()
        if (!hideNavigationBar)
            it.leftMargin = getNavigationBarHeight()
        it
    }
}
