package com.iwdael.immersive

import android.app.Activity
import android.content.Context.DISPLAY_SERVICE
import android.database.ContentObserver
import android.hardware.display.DisplayManager
import android.os.Handler
import android.os.Looper
import android.util.Log
import java.lang.ref.WeakReference

/**
 * author : Iwdael
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */

private val displayListener = object :
    DisplayManager.DisplayListener {
    override fun onDisplayAdded(displayId: Int) {
    }

    override fun onDisplayRemoved(displayId: Int) {
    }

    override fun onDisplayChanged(displayId: Int) {
        val states = immersiveStates.values.toList()
        for (index in states.indices) {
            val state = states[index]
            val activity = state.immersive.get() ?: continue
            if (state.orientation == Orientation.ORIENTATION_90 || state.orientation == Orientation.ORIENTATION_270) {
                val targetOrientation = activity.getActivityOrientationForContext()
                if (targetOrientation == Orientation.ORIENTATION_90 || targetOrientation == Orientation.ORIENTATION_270) {
                    if (state.orientation != targetOrientation) {
                        state.orientation = targetOrientation
                        activity.refreshContentLayoutParams()
                    }
                }
            }

        }
    }
}

private val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
    override fun onChange(selfChange: Boolean) {
        val states = immersiveStates.values.toList()
        for (index in states.indices) {
            val state = states[index]
            val activity = state.immersive.get() ?: continue
            activity.refreshContentLayoutParams()
        }
    }
}

fun Activity.registerImmersiveDisplayListener() {
    immersiveStates[this.hashCode().toString()] = ImmersiveState(
        WeakReference(this),
        statusBarContentIsDark = true,
        navigationBarContentIsDark = true,
        this.getActivityOrientationForContext()
    )
    val dm = applicationContext.getSystemService(DISPLAY_SERVICE) as DisplayManager
    dm.registerDisplayListener(displayListener, Handler(Looper.getMainLooper()))

    currentPhoneRom.navigationStateUri().forEach {
        applicationContext.contentResolver.registerContentObserver(it, true, contentObserver)
    }

}
