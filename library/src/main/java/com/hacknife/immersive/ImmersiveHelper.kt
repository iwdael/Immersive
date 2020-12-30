package com.hacknife.immersive

import android.app.Activity
import android.content.Context
import android.content.pm.ActivityInfo
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import android.view.Surface
import android.view.WindowManager
import androidx.annotation.RequiresApi

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */
object ImmersiveHelper {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    fun hasNavigationBarShow(activity: Activity): Boolean {
        val wm = activity.windowManager
        val display = wm.defaultDisplay
        var outMetrics = DisplayMetrics()
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics)
        val heightPixels = outMetrics.heightPixels
        val widthPixels = outMetrics.widthPixels
        //获取内容展示部分的高度
        outMetrics = DisplayMetrics()
        display.getMetrics(outMetrics)
        val heightPixels2 = outMetrics.heightPixels
        val widthPixels2 = outMetrics.widthPixels
        val w = widthPixels - widthPixels2
        val h = heightPixels - heightPixels2
        return !(w == 0 && h == 0) //竖屏和横屏两种情况。
    }

    fun getScreenPix(activity: Activity): DisplayMetrics {
        val displaysMetrics = DisplayMetrics()
        activity.windowManager.defaultDisplay.getMetrics(displaysMetrics)
        return displaysMetrics
    }

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
            if (!hasNavigationBarShow(activity)) return 0
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

    /**
     * The preferred screen orientation this activity would like to run in.
     * From the [android.R.attr.screenOrientation] attribute, one of
     *
     */
    fun getActivityOrientationForActivity(context: Activity): Orientation {
        val screenOrientation = context.window.attributes.screenOrientation
        if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED) {
            return Orientation._0
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE) {
            return Orientation._90
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_PORTRAIT) {
            return Orientation._90
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_USER) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_BEHIND) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_NOSENSOR) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_FULL_USER) {
        } else if (screenOrientation == ActivityInfo.SCREEN_ORIENTATION_LOCKED) {
        }
        return Orientation._0
    }
}