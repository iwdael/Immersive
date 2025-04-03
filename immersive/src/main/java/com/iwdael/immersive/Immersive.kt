package com.iwdael.immersive

import android.graphics.Color
import android.os.Build
import android.view.View
import androidx.annotation.ColorInt
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import com.iwdael.immersive.State.Companion.immersiveState
import com.iwdael.immersive.State.Companion.stateInit
import com.iwdael.immersive.ext.attachView
import com.iwdael.immersive.ext.compatible19
import com.iwdael.immersive.ext.compatible21
import com.iwdael.immersive.ext.contentLayoutParam
import com.iwdael.immersive.ext.listenForScreenRotation
import com.iwdael.immersive.ext.systemUiFlag
import com.iwdael.immersive.rom.BlackSharkPhoneRom
import com.iwdael.immersive.rom.DefaultPhoneRom
import com.iwdael.immersive.rom.GooglePhoneRom
import com.iwdael.immersive.rom.HuaweiPhoneRom
import com.iwdael.immersive.rom.NokiaPhoneRom
import com.iwdael.immersive.rom.OnePlusPhoneRom
import com.iwdael.immersive.rom.OppoPbemPhoneRom
import com.iwdael.immersive.rom.OppoPhoneRom
import com.iwdael.immersive.rom.SamsungPhoneRom
import com.iwdael.immersive.rom.SmartisanPhoneRom
import com.iwdael.immersive.rom.VivoPhoneRom
import com.iwdael.immersive.rom.XiaoMiPhoneRom

/**
 * @author 段泽全
 * @since 2025/4/2
 * @desc this is Immersive
 */

private val registeredRom = arrayListOf(
    GooglePhoneRom(),
    HuaweiPhoneRom(),
    OnePlusPhoneRom(),
    OppoPbemPhoneRom(),
    OppoPhoneRom(),
    SamsungPhoneRom(),
    VivoPhoneRom(),
    XiaoMiPhoneRom(),
    BlackSharkPhoneRom(),
    SmartisanPhoneRom(),
    NokiaPhoneRom(),
)

object Immersive {
    @JvmStatic
    fun registerPhoneRom(rom: PhoneRom) {
        registeredRom.add(0, rom)
    }

    @JvmStatic
    internal fun getCurrentPhoneRom(): PhoneRom {
        registeredRom.forEach {
            if (it.isCurrentPhoneRom(Build.BRAND.lowercase(), Build.PRODUCT.lowercase(), Build.MODEL.lowercase())) return it
        }
        return defaultPhoneRom
    }

    internal val defaultPhoneRom by lazy { DefaultPhoneRom() }
}

internal val currentPhoneRom get() = Immersive.getCurrentPhoneRom()

fun AppCompatActivity.setContentView(
    @LayoutRes layoutRes: Int,
    @ColorInt colorStatusBar: Int = Color.WHITE,
    @ColorInt colorNavBar: Int = Color.WHITE,
    stateStatusBar: BarState = BarState.SHOW,
    stateNavBar: BarState = BarState.SHOW,
    isLightStatusBar: Boolean = true,
    isLightNavBar: Boolean = true
) {
    stateInit(colorStatusBar, colorNavBar, stateStatusBar, stateNavBar, isLightNavBar, isLightStatusBar)
    compatible19()
    compatible21()
    attachView(layoutRes)
    listenForScreenRotation()
}

fun AppCompatActivity.setStatusBarColor(color: Int) {
    val state = immersiveState()
    state.colorStatusBar = color
    if (state.stateStatusBar == BarState.DISABLE) return
    findViewById<View>(R.id.immersive_status).setBackgroundColor(color)
}

fun AppCompatActivity.setNavigationBarColor(color: Int) {
    val state = immersiveState()
    state.colorNavBar = color
    if (state.stateNavBar == BarState.DISABLE) return
    findViewById<View>(R.id.immersive_navigation).setBackgroundColor(color)
}

var AppCompatActivity.stateStatusBar: BarState
    get() {
        return immersiveState().stateStatusBar
    }
    set(value) {
        val state = immersiveState()
        if (state.stateStatusBar == BarState.DISABLE) return
        state.stateStatusBar = value
        findViewById<View>(R.id.immersive_status).visibility = if (value == BarState.SHOW) View.VISIBLE else View.GONE
        val contentView = findViewById<View>(R.id.immersive_content)
        contentView.layoutParams = state.contentLayoutParam(this)
    }

var AppCompatActivity.stateNavBar: BarState
    get() {
        return immersiveState().stateNavBar
    }
    set(value) {
        val state = immersiveState()
        if (state.stateNavBar == BarState.DISABLE) return
        state.stateNavBar = value
        findViewById<View>(R.id.immersive_navigation).visibility = if (value == BarState.SHOW) View.VISIBLE else View.GONE
        val contentView = findViewById<View>(R.id.immersive_content)
        contentView.layoutParams = state.contentLayoutParam(this)
    }

var AppCompatActivity.isLightStatusBar: Boolean
    get() {
        val state = immersiveState()
        return state.isLightStatusBar
    }
    set(value) {
        val state = immersiveState()
        state.isLightStatusBar = value
        window.decorView.systemUiVisibility = systemUiFlag()
    }

var AppCompatActivity.isLightNavBar: Boolean
    get() {
        val state = immersiveState()
        return state.isLightNavBar
    }
    set(value) {
        val state = immersiveState()
        state.isLightNavBar = value
        window.decorView.systemUiVisibility = systemUiFlag()
    }