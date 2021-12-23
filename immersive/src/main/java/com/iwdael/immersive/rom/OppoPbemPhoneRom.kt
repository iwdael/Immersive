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
class OppoPbemPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "hide_navigationbar_enable"
        private const val CONTENT_KEY_2 = "manual_hide_navigationbar"
    }

    override fun isCurrentPhoneRom() =
        BRAND_LOWER_CASE.contains("oppo") && Build.MODEL.toLowerCase().contains("pbem00")

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            (Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY) == 0) ||
                    (Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY) == 1 &&
                            Settings.Secure.getInt(activity.contentResolver, CONTENT_KEY_2) == 0)
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri() = arrayOf(
        Settings.Secure.getUriFor(CONTENT_KEY),
        Settings.Secure.getUriFor(CONTENT_KEY_2)
    )
}