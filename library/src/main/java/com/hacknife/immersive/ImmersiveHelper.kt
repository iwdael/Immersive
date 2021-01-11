package com.hacknife.immersive

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Resources
import android.util.Log
import android.view.Surface
import android.view.WindowManager

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */
object ImmersiveHelper {


    private fun getSystemComponentDimen(
        context: Context,
        dimenName: String
    ): Int {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        var statusHeight = 0
        try {
            val clazz = Class.forName("com.android.internal.R\$dimen")
            val `object` = clazz.newInstance()
            val heightStr = clazz.getField(dimenName)[`object`].toString()
            val height = heightStr.toInt()
            //dp--->px
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
        // 反射手机运行的类：android.R.dimen.status_bar_height.
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
