package com.iwdael.immersive.rom

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.iwdael.immersive.BRAND_LOWER_CASE
import com.iwdael.immersive.Immersive.defaultPhoneRom
import com.iwdael.immersive.PhoneRom
import java.lang.Exception

/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : 华为
 * version: 1.0
 */
class HuaweiPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "navigationbar_is_min"
    }

    override fun isCurrentPhoneRom(): Boolean {
        return BRAND_LOWER_CASE.contains("huawei") || BRAND_LOWER_CASE.contains("honor")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Global.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }
}