package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.view.Surface
import android.view.WindowManager

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/iwdael
 * project : Immersive
 */
object ImmersiveHelper {


    private fun getSystemComponentDimen(
        context: Context,
        dimenName: String
    ): Int {
        var statusHeight = 0
        try {
            val height = context.resources.getIdentifier(dimenName, "dimen", "android")
            statusHeight = context.resources.getDimensionPixelSize(height)
        } catch (e: Exception) {
            e.printStackTrace()
        }
        return statusHeight
    }


    @JvmStatic
    fun getNavigationBarHeight(context: Context): Int {
        val activity = getActivity(context)
        if (activity != null) {
            if (!navigationHelper.navigationBarExist(activity)) return 0
        }
        return getSystemComponentDimen(context, "navigation_bar_height")
    }

    @JvmStatic
    fun getStatusBarHeight(context: Context): Int {
        return getSystemComponentDimen(context, "status_bar_height")
    }

    fun getActivity(context: Context?): Activity? {
        return if (context is Activity) context else null
    }

    @JvmStatic
    fun getActivityOrientationForContext(context: Context): Orientation {
        val wm =
            context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
                ?: return Orientation._0
        return when (wm.defaultDisplay.rotation) {
            Surface.ROTATION_90 -> {
                Orientation._90
            }
            Surface.ROTATION_180 -> {
                Orientation._180
            }
            Surface.ROTATION_270 -> {
                Orientation._270
            }
            else -> {
                Orientation._0
            }
        }
    }


}
