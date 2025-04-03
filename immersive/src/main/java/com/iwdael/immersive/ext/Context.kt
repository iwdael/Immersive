package com.iwdael.immersive.ext

import android.content.Context

/**
 * @author 段泽全
 * @since 2025/4/2
 * @desc this is Context
 */
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