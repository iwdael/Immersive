package com.iwdael.immersive.navigationbar

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.iwdael.immersive.NavigationBarRom
import java.lang.Exception

/**
 * author : iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : vivo
 * version: 1.0
 */
class VivoNavigationBarRom : NavigationBarRom {
    companion object {
        private const val CONTENT_KEY = "navigation_gesture_on"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            otherNavigationBarRom.navigationBarExist(activity)
        }
    }

}