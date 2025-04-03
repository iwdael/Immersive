package com.iwdael.immersive.ext

import android.app.Activity
import android.content.Context.DISPLAY_SERVICE
import android.database.ContentObserver
import android.graphics.Color
import android.hardware.display.DisplayManager
import android.os.Build
import android.os.Build.VERSION
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.FragmentActivity
import com.iwdael.immersive.Angle
import com.iwdael.immersive.BarState
import com.iwdael.immersive.NavigationView
import com.iwdael.immersive.R
import com.iwdael.immersive.State.Companion.angle
import com.iwdael.immersive.State.Companion.immersiveState
import com.iwdael.immersive.State.Companion.immersiveStateMap
import com.iwdael.immersive.StatusView
import com.iwdael.immersive.currentPhoneRom

/**
 * @author 段泽全
 * @since 2025/4/2
 * @desc this is Util
 */

internal fun Activity.compatible19() {
    if (VERSION.SDK_INT !in 19..20) return
    val state = immersiveState()
    if (state.stateStatusBar == BarState.DISABLE && state.stateNavBar == BarState.DISABLE) return
    if (state.stateStatusBar != BarState.DISABLE) {
        window.requestFeature(Window.FEATURE_NO_TITLE)
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
    }
    if (state.stateNavBar != BarState.DISABLE) {
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }

}

internal fun Activity.compatible21() {
    if (VERSION.SDK_INT < 21) return
    val state = immersiveState()
    if (state.stateStatusBar != BarState.DISABLE) {
        window.statusBarColor = Color.TRANSPARENT
    }
    if (state.stateNavBar != BarState.DISABLE) {
        window.navigationBarColor = Color.TRANSPARENT
    }
    window.decorView.systemUiVisibility = systemUiFlag()
}

internal fun Activity.systemUiFlag(): Int {
    val state = immersiveState()
    var flag = 0
    if (state.stateStatusBar != BarState.DISABLE) {
        flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_STABLE
        flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
    }
    if (state.stateNavBar != BarState.DISABLE) {
        flag = flag or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
    }
    if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
        if (state.isLightStatusBar) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        if (state.isLightNavBar) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
    }
    if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        if (state.isLightStatusBar) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
    }
    return flag
}


internal fun Activity.attachView(layoutRes: Int) {
    val state = immersiveState()
    this.setContentView(
        LayoutInflater.from(this).inflate(layoutRes, null).apply { id = R.id.immersive_content }, state.contentLayoutParam(this)
    )
    if (state.stateStatusBar != BarState.DISABLE) {
        this.addContentView(StatusView(this, null, 0, true).apply { id = R.id.immersive_status }.apply { setBackgroundColor(state.colorStatusBar) }
            .apply { visibility = if (state.stateStatusBar == BarState.SHOW) View.VISIBLE else View.GONE }, state.statusLayoutParam(this)
        )
    }
    if (state.stateNavBar != BarState.DISABLE) {
        this.addContentView(NavigationView(this, null, 0, true).apply { id = R.id.immersive_navigation }.apply { setBackgroundColor(state.colorNavBar) }
            .apply { visibility = if (state.stateNavBar == BarState.SHOW) View.VISIBLE else View.GONE }, state.navLayoutParam(this)
        )
    }
}

internal fun Activity.notifyLayoutParam() {
    val state = immersiveState()
    if (state.stateStatusBar == BarState.DISABLE && state.stateNavBar == BarState.DISABLE) return
    val contentView = findViewById<View>(R.id.immersive_content)
    contentView.layoutParams = state.contentLayoutParam(this)
    if (state.stateStatusBar != BarState.DISABLE) {
        val statusView = findViewById<View>(R.id.immersive_status)
        statusView.layoutParams = state.statusLayoutParam(this)
    }
    if (state.stateNavBar != BarState.DISABLE) {
        val navView = findViewById<View>(R.id.immersive_navigation)
        navView.layoutParams = state.navLayoutParam(this)
    }
}

private var isListenForScreenRotation = false
internal fun Activity.listenForScreenRotation() {
    if (isListenForScreenRotation) return
    val displayListener = object : DisplayManager.DisplayListener {
        override fun onDisplayAdded(displayId: Int) {
        }

        override fun onDisplayRemoved(displayId: Int) {
        }

        override fun onDisplayChanged(displayId: Int) {
            val angle = angle()
            for (value in immersiveStateMap()) {
                val host = value.host.get() ?: continue
                if (value.angle == angle) continue
                value.angle = angle
                host.notifyLayoutParam()
            }

        }
    }
    val dm = applicationContext.getSystemService(DISPLAY_SERVICE) as DisplayManager
    dm.registerDisplayListener(displayListener, Handler(Looper.getMainLooper()))

    val contentObserver = object : ContentObserver(Handler(Looper.getMainLooper())) {
        override fun onChange(selfChange: Boolean) {
            for (value in immersiveStateMap()) {
                val host = value.host.get() ?: continue
                host.notifyLayoutParam()
            }
        }
    }
    currentPhoneRom.navigationStateUri().forEach {
        applicationContext.contentResolver.registerContentObserver(it, true, contentObserver)
    }
    isListenForScreenRotation = true
}