package com.hacknife.immersive

import android.os.Build

/**
 * author : 段泽全(hacknife)
 * e-mail : hacknife@outlook.com
 * time   : 2019/8/5
 * desc   : MVVM
 * version: 1.0
 */


private val BRAND = Build.BRAND.toLowerCase()

fun isXiaomi(): Boolean {
    return Build.MANUFACTURER.toLowerCase() == "xiaomi"
}

fun isVivo(): Boolean {
    return BRAND.contains("vivo")
}

fun isOppo(): Boolean {
    return BRAND.contains("oppo") || BRAND.contains("realme")
}

fun isHuawei(): Boolean {
    return BRAND.contains("huawei") || BRAND.contains("honor")
}

fun isOneplus(): Boolean {
    return BRAND.contains("oneplus")
}

fun isSamsung(): Boolean {
    return BRAND.contains("samsung")
}

fun isSmartisan(): Boolean {
    return BRAND.contains("smartisan")
}

fun isNokia(): Boolean {
    return BRAND.contains("nokia")
}

fun isGoogle(): Boolean {
    return BRAND.contains("google")
}