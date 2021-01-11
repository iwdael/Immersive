package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.hacknife.immersive.NavigationBarRom

/**
 * author : hacknife
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : 小米
 * version: 1.0
 */
class SmartisanNavigationBarRom : NavigationBarRom {
    companion object {
        private const val CONTENT_KEY = "navigationbar_trigger_mode"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return Settings.Global.getInt(activity.contentResolver, CONTENT_KEY, 0) == 0
    }

}