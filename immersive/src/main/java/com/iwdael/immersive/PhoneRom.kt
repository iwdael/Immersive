package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.os.Build


/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
interface PhoneRom {
    /**
     * 判断当前rom是否为定义ROM
     */
    fun isCurrentPhoneRom(): Boolean

    /**
     * 状态栏高度
     */
    fun statusBarHeight(context: Context): Int {
        return context.getSystemComponentDimen("status_bar_height")
    }

    /**
     * 导航栏高度
     */
    fun navigationBarHeight(context: Context): Int {
        return context.getSystemComponentDimen("navigation_bar_height")
    }

    /**
     * 部分手机可隐藏导航栏，是否存在
     */
    fun navigationBarExist(activity: Activity): Boolean

    companion object {

        val BRAND_LOWER_CASE = Build.BRAND.toLowerCase()

        fun Context.getSystemComponentDimen(
            dimenName: String
        ): Int {
            var statusHeight = 0
            try {
                val height = resources.getIdentifier(dimenName, "dimen", "android")
                statusHeight = resources.getDimensionPixelSize(height)
            } catch (e: Exception) {
                e.printStackTrace()
            }
            return statusHeight
        }
    }
}
