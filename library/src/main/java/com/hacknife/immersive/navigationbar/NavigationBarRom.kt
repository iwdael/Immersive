package com.hacknife.immersive.navigationbar

import android.app.Activity
import android.text.TextUtils
import androidx.fragment.app.FragmentActivity
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
    companion object {
        const val KEY_VERSION_MIUI = "ro.miui.ui.version.name"
        const val KEY_VERSION_EMUI = "ro.build.version.emui"
        const val KEY_VERSION_OPPO = "ro.build.version.opporom"
        const val KEY_VERSION_SMARTISAN = "ro.smartisan.version"
        const val KEY_VERSION_VIVO = "ro.vivo.os.version"
    }

    fun navigationBarExist(activity: Activity): Boolean
}

val navigationHelper by lazy { getNavigationBarRom() }

fun getNavigationBarRom(): NavigationBarRom {
    if (!TextUtils.isEmpty(getProp(NavigationBarRom.KEY_VERSION_MIUI)))
        return MiuiNavigationBarRom()
    if (!TextUtils.isEmpty(getProp(NavigationBarRom.KEY_VERSION_EMUI)))
        return EmuiNavigationBarRom()
    if (!TextUtils.isEmpty(getProp(NavigationBarRom.KEY_VERSION_VIVO)))
        return FuntouchNavigationBarRom()
    return OtherNavigationBarRom()
}

private fun getProp(name: String): String? {
    val line: String?
    var input: BufferedReader? = null
    try {
        val p = Runtime.getRuntime().exec("getprop $name")
        input = BufferedReader(InputStreamReader(p.inputStream), 1024)
        line = input.readLine()
        input.close()
    } catch (ex: IOException) {
        ex.printStackTrace()
        return null
    } finally {
        if (input != null) {
            try {
                input.close()
            } catch (e: IOException) {
                e.printStackTrace()
            }
        }
    }
    return line
}