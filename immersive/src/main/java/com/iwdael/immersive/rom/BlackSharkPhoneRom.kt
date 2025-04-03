package com.iwdael.immersive.rom

import android.app.Activity
import android.os.Build
import android.provider.Settings
import androidx.annotation.RequiresApi
import com.iwdael.immersive.Immersive.defaultPhoneRom
import com.iwdael.immersive.PhoneRom

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : 騰訊黑紗
 * version: 1.0
 */
class BlackSharkPhoneRom : PhoneRom {
    companion object {
        private const val CONTENT_KEY = "force_fsg_nav_bar"
    }

    override fun isCurrentPhoneRom(brand: String, product: String, model: String): Boolean {
        return brand.contains("blackshark")
    }

    @RequiresApi(Build.VERSION_CODES.KITKAT)
    override fun navigationBarExist(activity: Activity): Boolean {
        return try {
            Settings.Global.getInt(activity.contentResolver, CONTENT_KEY) == 0
        } catch (e: Exception) {
            defaultPhoneRom.navigationBarExist(activity)
        }
    }

    override fun navigationStateUri() = arrayOf(Settings.Global.getUriFor(CONTENT_KEY))


}