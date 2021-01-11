package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import com.hacknife.immersive.NavigationBarRom

/**
 * author : hacknife
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : 小米
 * version: 1.0
 */
class NokiaNavigationBarRom : NavigationBarRom {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        val result = (Settings.Secure.getInt(
            activity.contentResolver,
            "swipe_up_to_switch_apps_enabled",
            0
        ) != 0
                || Settings.System.getInt(
            activity.contentResolver,
            "navigation_bar_can_hiden",
            0
        ) != 0)
        return !result
    }

}