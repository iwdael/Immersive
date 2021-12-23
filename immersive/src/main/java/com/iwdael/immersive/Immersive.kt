package com.iwdael.immersive

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION
import android.view.*
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/iwdael
 * project : Immersive
 */


val immersiveStates = hashMapOf<String, ImmersiveState>()

private fun compatible19(activity: FragmentActivity) {
    if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
        activity.window.requestFeature(Window.FEATURE_NO_TITLE)
        activity.window
            .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        activity.window
            .addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
    }

}

private fun compatible21(activity: FragmentActivity) {
    if (VERSION.SDK_INT >= 21) {
        activity.window.statusBarColor = Color.TRANSPARENT
        activity.window.navigationBarColor = Color.TRANSPARENT
        activity.window?.decorView?.systemUiVisibility =
            systemUiFlag(
                immersiveStates[activity.hashCode().toString()]?.statusBarContentIsDark ?: true,
                immersiveStates[activity.hashCode().toString()]?.navigationBarContentIsDark ?: true
            )
    }
}

private fun systemUiFlag(
    statusBarContentIsDark: Boolean,
    navigationBarContentIsDark: Boolean
): Int {
    var flag = View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION or
            View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or
            View.SYSTEM_UI_FLAG_LAYOUT_STABLE
    when {
        VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            if (statusBarContentIsDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (navigationBarContentIsDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            if (statusBarContentIsDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    return flag
}


fun Activity.attachContentView(
    @LayoutRes layoutRes: Int,
    statusEmbed: Boolean,
    navigationEmbed: Boolean
) {

    val orientation = immersiveStates[this.hashCode().toString()]!!.orientation
    this.setContentView(
        LayoutInflater.from(this).inflate(layoutRes, null)
            .apply { id = R.id.immersive_content },
        createContentLayoutParam(orientation, statusEmbed, navigationEmbed)
    )
    this.addContentView(
        StatusView(this, null, 0, true)
            .apply { id = R.id.immersive_status },
        createStatusBarLayoutParam(orientation)
    )
    this.addContentView(
        NavigationView(this, null, 0, true)
            .apply { id = R.id.immersive_navigation },
        createNavigationBarLayoutParam(orientation)
    )


}

fun AppCompatActivity.setContentView(
    @LayoutRes layoutRes: Int,
    @ColorRes statusRes: Int,
    @ColorRes navigationRes: Int,
    hideStatusBar: Boolean,
    hideNavigationBar: Boolean
) {
    registerImmersiveDisplayListener()
    compatible19(this)
    compatible21(this)
    attachContentView(layoutRes, hideStatusBar, hideNavigationBar)
    val statusView: StatusView = this.findViewById(R.id.immersive_status)
    val navigationView: NavigationView = this.findViewById(R.id.immersive_navigation)
    statusView.setBackgroundResource(statusRes)
    navigationView.setBackgroundResource(navigationRes)
    if (hideStatusBar) statusView.visibility = View.GONE
    if (hideNavigationBar) navigationView.visibility = View.GONE
}

fun <T : View> Activity.contentView(): T {
    return this.findViewById<T>(R.id.immersive_content)
}

fun Activity.isShowOfStatusBar(): Boolean {
    return findViewById<View>(R.id.immersive_status)?.visibility == View.VISIBLE
}


fun Activity.isShowOfNavigationBar(): Boolean {
    return findViewById<View>(R.id.immersive_navigation)?.visibility == View.VISIBLE
}


fun Activity.showStatusBar() {
    refreshContentLayoutParams(
        false,
        findViewById<View>(R.id.immersive_navigation)?.visibility == View.GONE
    )
}

fun Activity.hideStatusBar() {
    refreshContentLayoutParams(
        true,
        findViewById<View>(R.id.immersive_navigation)?.visibility == View.GONE
    )
}

fun Activity.showNavigationBar() {
    refreshContentLayoutParams(
        findViewById<View>(R.id.immersive_status)?.visibility == View.GONE,
        false
    )
}

fun Activity.hideNavigationBar() {
    refreshContentLayoutParams(
        findViewById<View>(R.id.immersive_status)?.visibility == View.GONE,
        true
    )
}

fun Activity.setContentBackgroundColor(@ColorInt color: Int) {
    val content = findViewById<View>(R.id.immersive_content)
    content?.setBackgroundColor(color)
}

fun Activity.setContentBackgroundColorResource(@ColorRes colorRes: Int) {
    val content = findViewById<View>(R.id.immersive_content)
    content?.setBackgroundResource(colorRes)
}


fun Activity.setStatusBarColor(@ColorInt color: Int) {
    val status = findViewById<View>(R.id.immersive_status)
    status?.setBackgroundColor(color)
}

fun Activity.setStatusBarColorResource(@ColorRes colorRes: Int) {
    val status = findViewById<View>(R.id.immersive_status)
    status?.setBackgroundResource(colorRes)
}


fun Activity.setNavigationBarColor(@ColorInt color: Int) {
    val navigation = findViewById<View>(R.id.immersive_navigation)
    navigation?.setBackgroundColor(color)
}

fun Activity.setNavigationBarColorResource(@ColorRes colorRes: Int) {
    val navigation = findViewById<View>(R.id.immersive_navigation)
    navigation?.setBackgroundResource(colorRes)
}


fun Activity.setStatusContentColor(mode: MODE): Boolean {
    return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val navigationBar =
            immersiveStates[this.hashCode().toString()]?.navigationBarContentIsDark ?: true
        if (mode == MODE.WHITE) {
            this.window.decorView.systemUiVisibility = systemUiFlag(false, navigationBar)
            immersiveStates[this.hashCode().toString()]?.statusBarContentIsDark = false
        } else {
            this.window.decorView.systemUiVisibility = systemUiFlag(true, navigationBar)
            immersiveStates[this.hashCode().toString()]?.statusBarContentIsDark = true
        }
        true
    } else {
        false
    }
}


fun Activity.setNavigationContentColor(mode: MODE): Boolean {
    return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val stateBar = immersiveStates[this.hashCode().toString()]?.statusBarContentIsDark ?: true
        if (mode == MODE.WHITE) {
            this.window.decorView.systemUiVisibility = systemUiFlag(stateBar, false)
            immersiveStates[this.hashCode().toString()]?.navigationBarContentIsDark = false
        } else {
            this.window.decorView.systemUiVisibility = systemUiFlag(stateBar, true)
            immersiveStates[this.hashCode().toString()]?.navigationBarContentIsDark = true
        }
        true
    } else {
        false
    }
}

fun Activity.setOnSoftKeyBoardChangeListener(
    onSoftKeyBoardChangeListener: SoftKeyBoardKit.OnSoftKeyBoardChangeListener
) {
    val softKeyBoardListener = SoftKeyBoardKit(this)
    softKeyBoardListener.setOnSoftKeyBoardChangeListener(onSoftKeyBoardChangeListener)
}

enum class MODE {
    WHITE, BLACK
}
