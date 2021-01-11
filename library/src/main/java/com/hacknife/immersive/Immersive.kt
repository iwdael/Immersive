package com.hacknife.immersive

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION
import android.view.*
import android.widget.FrameLayout
import androidx.annotation.ColorInt
import androidx.annotation.ColorRes
import androidx.annotation.LayoutRes
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentActivity

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */


private val stateMap = hashMapOf<String, State>()

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
            systemUiFlag(true, true)
        stateMap.put(activity.hashCode().toString(), State(true, true))
    }
}

private fun systemUiFlag(statusBarDark: Boolean, navigationBarDark: Boolean): Int {
    var flag = (View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
            or View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
            or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
    when {
        VERSION.SDK_INT >= Build.VERSION_CODES.O -> {
            if (statusBarDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
            if (navigationBarDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_NAVIGATION_BAR
        }
        VERSION.SDK_INT >= Build.VERSION_CODES.M -> {
            if (statusBarDark) flag = flag or View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
        }
    }
    return flag
}


fun AppCompatActivity.setContentView(
    @LayoutRes layoutRes: Int,
    @ColorRes statusRes: Int,
    @ColorRes navigationRes: Int,
    statusEmbed: Boolean,
    navigationEmbed: Boolean
) {
    compatible19(this)
    val orientation =
        ImmersiveHelper.getActivityOrientationForContext(this)
    if (orientation == Orientation._0 || orientation == Orientation._180) {
        this.setContentView(R.layout.activity_immersive_vertical)
    } else if (orientation == Orientation._90) {
        this.setContentView(R.layout.activity_immersive_horizontal_90)
    } else {
        this.setContentView(R.layout.activity_immersive_horizontal_270)
    }
    compatible21(this)
    val statusView: StatusView = this.findViewById(R.id.immersive_status)
    val content = this.findViewById<FrameLayout>(R.id.immersive_content)
    val navigationView: NavigationView = this.findViewById(R.id.immersive_navigation)
    statusView.setBackgroundResource(statusRes)
    navigationView.setBackgroundResource(navigationRes)
    if (statusEmbed) statusView.visibility = View.GONE
    if (navigationEmbed) navigationView.visibility = View.GONE
    content.addView(LayoutInflater.from(this).inflate(layoutRes, null))
}


fun Activity.hideStatus() {
    val statusView: StatusView? = findViewById(R.id.immersive_status)
    if (statusView != null) statusView.visibility = View.GONE
}


fun Activity.isShowOfStatus(): Boolean {
    val statusView: StatusView? = findViewById(R.id.immersive_status)
    return if (statusView != null) statusView.visibility == View.VISIBLE else false
}


fun Activity.isShowOfNavigation(): Boolean {
    val navigationView: NavigationView? = findViewById(R.id.immersive_navigation)
    return if (navigationView != null) navigationView.visibility == View.VISIBLE else false
}


fun Activity.showStatus() {
    val statusView: StatusView? = findViewById(R.id.immersive_status)
    if (statusView != null) statusView.visibility = View.VISIBLE
}


fun Activity.hideNavigation() {
    val navigationView: NavigationView? = findViewById(R.id.immersive_navigation)
    if (navigationView != null) navigationView.visibility = View.GONE
}


fun Activity.showNavigation() {
    val navigationView: NavigationView? = findViewById(R.id.immersive_navigation)
    if (navigationView != null) navigationView.visibility = View.VISIBLE
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
        val navigationBar = stateMap[this.hashCode().toString()]?.navigationBar ?: true
        if (mode == MODE.WHITE) {
            this.window.decorView.systemUiVisibility = systemUiFlag(false, navigationBar)
            stateMap[this.hashCode().toString()]?.stateBar = false
        } else {
            this.window.decorView.systemUiVisibility = systemUiFlag(true, navigationBar)
            stateMap[this.hashCode().toString()]?.stateBar = true
        }
        true
    } else {
        false
    }
}


fun Activity.setNavigationContentColor(mode: MODE): Boolean {
    return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
        val stateBar = stateMap[this.hashCode().toString()]?.stateBar ?: true
        if (mode == MODE.WHITE) {
            this.window.decorView.systemUiVisibility = systemUiFlag(stateBar, false)
            stateMap[this.hashCode().toString()]?.navigationBar = false
        } else {
            this.window.decorView.systemUiVisibility = systemUiFlag(stateBar, true)
            stateMap[this.hashCode().toString()]?.navigationBar = true
        }
        true
    } else {
        false
    }
}

fun Activity.rootView(): View? {
    val group = findViewById<ViewGroup>(R.id.immersive_content)
    return group?.getChildAt(0)
}

enum class MODE {
    WHITE, BLACK
}
