package com.iwdael.immersive

import android.app.Activity
import com.iwdael.immersive.navigationbar.*


/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
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
        isOppo() -> OppoNavigationBarRom()
        isSamsung() -> SamsungNavigationBarRom()
//        isSmartisan() -> SmartisanNavigationBarRom()
        isVivo() -> VivoNavigationBarRom()
        isXiaomi() -> XiaoMiNavigationBarRom()
        isBlackShark() -> BlackSharkNavigationBarRom()
        else -> OtherNavigationBarRom()
    }
}
