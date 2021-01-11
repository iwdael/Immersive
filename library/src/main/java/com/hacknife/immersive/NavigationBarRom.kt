package com.hacknife.immersive

import android.app.Activity
import android.text.TextUtils
import com.hacknife.immersive.navigationbar.*
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader


/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */
interface NavigationBarRom {

    fun navigationBarExist(activity: Activity): Boolean
}

val navigationHelper by lazy { getNavigationBarRom() }

fun getNavigationBarRom(): NavigationBarRom {
    return when {
//        isGoogle() -> GoogleNavigationBarRom()
        isHuawei() -> HuaweiNavigationBarRom()
//        isNokia() -> NokiaNavigationBarRom()
        isOneplus() -> OnePlusNavigationBarRom()
//        isOppo() -> OppoNavigationBarRom()
        isSamsung() -> SamsungNavigationBarRom()
//        isSmartisan() -> SmartisanNavigationBarRom()
        isVivo() -> VivoNavigationBarRom()
        isXiaomi() -> XiaoMiNavigationBarRom()
        else -> OtherNavigationBarRom()
    }
}
