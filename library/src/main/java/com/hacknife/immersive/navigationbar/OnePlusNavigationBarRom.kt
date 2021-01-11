package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.content.ContentResolver
import android.database.Cursor
import android.net.Uri
import android.os.Build
import android.provider.Settings
import android.util.Log
import android.view.WindowManager
import androidx.annotation.RequiresApi
import com.hacknife.immersive.NavigationBarRom
import com.hacknife.immersive.TAG
import com.hacknife.immersive.getSystemProperty
import java.lang.StringBuilder


/**
 * author : hacknife
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : one plus / 6T
 * version: 1.0
 */
class OnePlusNavigationBarRom : NavigationBarRom {
    init {
        Log.v(TAG, "OnePlusNavigationBarRom")
    }

    companion object {
        const val CONTENT_KEY = "op_navigation_bar_type"
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return Settings.System.getInt(activity.contentResolver, CONTENT_KEY, 3) != 3
    }


}