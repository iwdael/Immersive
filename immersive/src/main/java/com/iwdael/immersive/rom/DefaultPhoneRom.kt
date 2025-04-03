package com.iwdael.immersive.rom

import android.app.Activity
import android.net.Uri
import android.os.Build
import android.util.DisplayMetrics
import androidx.annotation.RequiresApi
import com.iwdael.immersive.PhoneRom

/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : other
 * version: 1.0
 */

class DefaultPhoneRom : PhoneRom {
    override fun isCurrentPhoneRom(brand: String, product: String, model: String): Boolean {
        return true
    }

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

    override fun navigationStateUri() = arrayOf<Uri>()
}