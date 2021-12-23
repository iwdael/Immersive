package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.net.Uri
import android.os.Build
import android.view.Gravity


/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
interface PhoneRom {


    /**
     * 根据屏幕旋转角度判断当前状态所在的位置，绝大多数不用修改
     */
    fun gravityOfStatusBar(orientation: Orientation) = Gravity.TOP

    /**
     * 根据屏幕旋转角度判断当前导航所在的位置，绝大多数不用修改
     */
    fun gravityOfNavigationBar(orientation: Orientation) = when (orientation) {
        Orientation.ORIENTATION_0 -> Gravity.BOTTOM
        Orientation.ORIENTATION_90 -> Gravity.END
        Orientation.ORIENTATION_180 -> Gravity.BOTTOM
        Orientation.ORIENTATION_270 -> Gravity.START
    }

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

    /**
     * 状态栏改变
     */
    fun navigationStateUri() = arrayOf<Uri>()
}
