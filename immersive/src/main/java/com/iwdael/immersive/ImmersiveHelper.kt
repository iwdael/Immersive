package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.os.Build
import android.util.Log
import android.view.Gravity
import android.view.Surface
import android.view.View
import android.view.WindowManager
import android.widget.FrameLayout

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/iwdael
 * project : Immersive
 */


val BRAND_LOWER_CASE = Build.BRAND.toLowerCase()

fun Context.getSystemComponentDimen(
    dimenName: String
): Int {
    var statusHeight = 0
    try {
        val height = resources.getIdentifier(dimenName, "dimen", "android")
        statusHeight = resources.getDimensionPixelSize(height)
    } catch (e: Exception) {
        e.printStackTrace()
    }
    return statusHeight
}


fun Context.getNavigationBarHeight(): Int {
    val activity = getActivity(this)
    if (activity != null) {
        if (!currentPhoneRom.navigationBarExist(activity)) return 0
    }
    return currentPhoneRom.navigationBarHeight(this)
}


fun Orientation.gravityOfNavigationBar(): Int {
    return currentPhoneRom.gravityOfNavigationBar(this)
}

fun Orientation.gravityOfStatusBar(): Int {
    return currentPhoneRom.gravityOfStatusBar(this)
}


fun Context.getStatusBarHeight(): Int {
    return currentPhoneRom.statusBarHeight(this)
}

fun getActivity(context: Context?): Activity? {
    return if (context is Activity) context else null
}


fun Context.getActivityOrientationForContext(): Orientation {
    val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager?
        ?: return Orientation.ORIENTATION_0
    return when (wm.defaultDisplay.rotation) {
        Surface.ROTATION_90 -> {
            Orientation.ORIENTATION_90
        }
        Surface.ROTATION_180 -> {
            Orientation.ORIENTATION_180
        }
        Surface.ROTATION_270 -> {
            Orientation.ORIENTATION_270
        }
        else -> {
            Orientation.ORIENTATION_0
        }
    }
}

fun Activity.refreshContentLayoutParams() {
    refreshContentLayoutParams(
        findViewById<View>(R.id.immersive_status)?.visibility == View.GONE,
        findViewById<View>(R.id.immersive_navigation)?.visibility == View.GONE
    )
}


fun Activity.refreshContentLayoutParams(hideStatusBar: Boolean, hideNavigationBar: Boolean) {
    val status = findViewById<View>(R.id.immersive_status) ?: return
    val navigation = findViewById<View>(R.id.immersive_navigation) ?: return
    val content = findViewById<View>(R.id.immersive_content) ?: return
    if (hideStatusBar) status.visibility = View.GONE
    else status.visibility = View.VISIBLE

    if (hideNavigationBar) navigation.visibility = View.GONE
    else navigation.visibility = View.VISIBLE

    val contentParams = content.layoutParams as FrameLayout.LayoutParams
    val navigationParams = navigation.layoutParams as FrameLayout.LayoutParams
    val state = immersiveStates[this.hashCode().toString()] ?: return
    when (state.orientation) {
        Orientation.ORIENTATION_0, Orientation.ORIENTATION_180 -> {
            if (hideStatusBar)
                contentParams.topMargin = 0
            else
                contentParams.topMargin = getStatusBarHeight()

            if (hideNavigationBar)
                contentParams.bottomMargin = 0
            else
                contentParams.bottomMargin = getNavigationBarHeight()

            contentParams.leftMargin = 0
            contentParams.rightMargin = 0
        }
        Orientation.ORIENTATION_90 -> {
            if (hideStatusBar)
                contentParams.topMargin = 0
            else
                contentParams.topMargin = getStatusBarHeight()

            if (hideNavigationBar)
                contentParams.rightMargin = 0
            else
                contentParams.rightMargin = getNavigationBarHeight()
            navigationParams.gravity = Gravity.END
            contentParams.bottomMargin = 0
            contentParams.leftMargin = 0
        }
        Orientation.ORIENTATION_270 -> {
            if (hideStatusBar)
                contentParams.topMargin = 0
            else
                contentParams.topMargin = getStatusBarHeight()

            if (hideNavigationBar)
                contentParams.leftMargin = 0
            else
                contentParams.leftMargin = getNavigationBarHeight()
            navigationParams.gravity = Gravity.START
            contentParams.bottomMargin = 0
            contentParams.rightMargin = 0
        }
    }
}