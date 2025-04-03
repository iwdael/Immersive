package com.iwdael.immersive.rom

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.iwdael.immersive.Immersive.defaultPhoneRom
import com.iwdael.immersive.PhoneRom


/**
 * author : iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : one plus / 6T
 * version: 1.0
 */
class OnePlusPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "op_navigation_bar_type"
    }

    override fun isCurrentPhoneRom(brand: String, product: String, model: String) = brand.contains("oneplus")

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.System.getInt(activity.contentResolver, CONTENT_KEY) != 3
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri() = arrayOf(Settings.System.getUriFor(CONTENT_KEY))
}