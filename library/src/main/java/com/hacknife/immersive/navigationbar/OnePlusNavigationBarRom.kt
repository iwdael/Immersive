package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.hacknife.immersive.NavigationBarRom
import java.lang.Exception


/**
 * author : hacknife
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : one plus / 6T
 * version: 1.0
 */
class OnePlusNavigationBarRom : NavigationBarRom {
    companion object {
        private const val CONTENT_KEY = "op_navigation_bar_type"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.System.getInt(activity.contentResolver, CONTENT_KEY) != 3
        } catch (e: Exception) {
            otherNavigationBarRom.navigationBarExist(activity)
        }
    }


}