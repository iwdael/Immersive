package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.os.Build
import android.provider.Settings
import android.util.Log
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
class MiuiNavigationBarRom : NavigationBarRom {
    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return Settings.Global.getInt(activity.contentResolver, "force_fsg_nav_bar", 0) == 0
    }

}