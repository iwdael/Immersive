package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.view.Gravity
import com.iwdael.immersive.ext.getSystemComponentDimen

/**
 * @author 段泽全
 * @since 2025/4/2
 * @desc this is Phone
 */

interface PhoneRom {
    /**
     * 根据屏幕旋转角度判断当前状态所在的位置，绝大多数不用修改
     */
    fun gravityOfStatusBar(angle: Angle) = Gravity.TOP

    /**
     * 根据屏幕旋转角度判断当前导航所在的位置，绝大多数不用修改
     */
    fun gravityOfNavigationBar(angle: Angle) = when (angle) {
        Angle.Angle0 -> Gravity.BOTTOM
        Angle.Angle90 -> Gravity.END
        Angle.Angle180 -> Gravity.TOP
        Angle.Angle270 -> Gravity.START
    }

    /**
     * 判断当前rom是否为定义ROM
     */
    fun isCurrentPhoneRom(brand: String, product: String, model: String): Boolean

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

    /**
     * 状态栏改变
     */
    fun navigationStateUri() = arrayOf<Uri>()
}