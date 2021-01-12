package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.os.Build
import android.util.DisplayMetrics
import android.util.Log
import androidx.annotation.RequiresApi
import com.hacknife.immersive.NavigationBarRom

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : other
 * version: 1.0
 */
val otherNavigationBarRom by lazy { OtherNavigationBarRom() }
class OtherNavigationBarRom : NavigationBarRom {

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        val d = activity.windowManager.defaultDisplay
        val realDisplayMetrics = DisplayMetrics()

        d.getRealMetrics(realDisplayMetrics)

        val realHeight = realDisplayMetrics.heightPixels
        val realWidth = realDisplayMetrics.widthPixels

        val displayMetrics = DisplayMetrics()
        d.getMetrics(displayMetrics)

        val displayHeight = displayMetrics.heightPixels
        val displayWidth = displayMetrics.widthPixels

        return realWidth - displayWidth > 0 || realHeight - displayHeight > 0
    }
}