package com.iwdael.immersive.rom

import android.app.Activity
import android.net.Uri
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.iwdael.immersive.BRAND_LOWER_CASE
import com.iwdael.immersive.Immersive.defaultPhoneRom
import com.iwdael.immersive.PhoneRom
import java.lang.Exception

/**
 * author : iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : oppo
 * version: 1.0
 */
class OppoPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "hide_navigationbar_enable"
    }

    override fun isCurrentPhoneRom() =
        BRAND_LOWER_CASE.contains("oppo") || BRAND_LOWER_CASE.contains("realme")

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri(): Uri = Settings.Secure.getUriFor(CONTENT_KEY)
}