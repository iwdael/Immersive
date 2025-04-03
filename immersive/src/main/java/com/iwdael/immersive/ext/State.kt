package com.iwdael.immersive.ext

import android.content.Context
import android.view.Gravity
import android.widget.FrameLayout
import com.iwdael.immersive.Angle
import com.iwdael.immersive.BarState
import com.iwdael.immersive.State
import com.iwdael.immersive.currentPhoneRom

/**
 * @author 段泽全
 * @since 2025/4/2
 * @desc this is State
 */

fun State.contentLayoutParam(context: Context): FrameLayout.LayoutParams {
    val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.MATCH_PARENT)
    if (stateStatusBar == BarState.DISABLE && stateNavBar == BarState.DISABLE) {
        lp.leftMargin = 0
        lp.topMargin = 0
        lp.rightMargin = 0
        lp.bottomMargin = 0
        return lp
    }
    if (stateStatusBar == BarState.SHOW) {
        lp.topMargin = currentPhoneRom.statusBarHeight(context)
    }
    if (stateNavBar == BarState.SHOW) {
        when (angle) {
            Angle.Angle0 -> lp.bottomMargin = currentPhoneRom.navigationBarHeight(context)
            Angle.Angle90 -> lp.rightMargin = currentPhoneRom.navigationBarHeight(context)
            Angle.Angle180 -> lp.topMargin = currentPhoneRom.navigationBarHeight(context)
            Angle.Angle270 -> lp.leftMargin = currentPhoneRom.navigationBarHeight(context)
        }
    }
    return lp
}

fun State.statusLayoutParam(context: Context): FrameLayout.LayoutParams {
    val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    lp.gravity = currentPhoneRom.gravityOfStatusBar(angle)
    return lp
}

fun State.navLayoutParam(context: Context): FrameLayout.LayoutParams {
    val lp = FrameLayout.LayoutParams(FrameLayout.LayoutParams.MATCH_PARENT, FrameLayout.LayoutParams.WRAP_CONTENT)
    lp.gravity = currentPhoneRom.gravityOfNavigationBar(angle)
    return lp
}

