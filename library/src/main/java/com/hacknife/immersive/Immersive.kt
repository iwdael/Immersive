package com.hacknife.immersive

import android.app.Activity
import android.graphics.Color
import android.os.Build
import android.os.Build.VERSION
import android.util.Log
import android.view.*
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.annotation.LayoutRes
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.hacknife.immersive.ImmersiveHelper.hasNavigationBarShow

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */
object Immersive {
    private val TAG = Immersive::class.java.name

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

    @JvmStatic
    fun setContentView(
        activity: FragmentActivity,
        @LayoutRes layoutRes: Int,
        statusRes: Int,
        navigationRes: Int,
        statusEmbed: Boolean,
        navigationEmbed: Boolean
    ) {
        compatible19(activity)
        compatible21(activity)
        val orientation = ImmersiveHelper.getActivityOrientationForContext(activity)
        if (orientation == Orientation._0 || orientation == Orientation._180) {
            activity.setContentView(R.layout.activity_immersive_vertical)
        } else if (orientation == Orientation._90) {
            activity.setContentView(R.layout.activity_immersive_horizontal_90)
        } else {
            activity.setContentView(R.layout.activity_immersive_horizontal_270)
        }

        val statusView: StatusView = activity.findViewById(R.id.immersive_status)
        val content = activity.findViewById<FrameLayout>(R.id.immersive_content)
        val navigationView: NavigationView = activity.findViewById(R.id.immersive_navigation)
        val rootView = activity.findViewById<LinearLayout>(R.id.immersive)
        rootView.addOnLayoutChangeListener { _, _, _, _, _, _, _, _, _ ->
            if (!hasNavigationBarShow(activity, rootView.width, rootView.height)) {
                navigationView.hide()
                navigationView.requestLayout()
            }
        }
        statusView.setBackgroundResource(statusRes)
        navigationView.setBackgroundResource(navigationRes)
        if (statusEmbed) statusView.visibility = View.GONE
        if (navigationEmbed) navigationView.visibility = View.GONE
        content.addView(LayoutInflater.from(activity).inflate(layoutRes, null))
    }

    @JvmStatic
    fun hideStatus(activity: Activity) {
        val statusView: StatusView = activity.findViewById(R.id.immersive_status)
        if (statusView != null) statusView.visibility = View.GONE
    }

    @JvmStatic
    fun isShowOfStatus(activity: Activity): Boolean {
        val statusView: StatusView = activity.findViewById(R.id.immersive_status)
        return if (statusView != null) statusView.visibility == View.VISIBLE else false
    }

    @JvmStatic
    fun isShowOfNavigation(activity: Activity): Boolean {
        val navigationView: NavigationView = activity.findViewById(R.id.immersive_navigation)
        return if (navigationView != null) navigationView.visibility == View.VISIBLE else false
    }

    @JvmStatic
    fun showStatus(activity: Activity) {
        val statusView: StatusView = activity.findViewById(R.id.immersive_status)
        if (statusView != null) statusView.visibility = View.VISIBLE
    }

    @JvmStatic
    fun hideNavigation(activity: Activity) {
        val navigationView: NavigationView = activity.findViewById(R.id.immersive_navigation)
        if (navigationView != null) navigationView.visibility = View.GONE
    }

    @JvmStatic
    fun showNavigation(activity: Activity) {
        val navigationView: NavigationView = activity.findViewById(R.id.immersive_navigation)
        if (navigationView != null) navigationView.visibility = View.VISIBLE
    }

    fun setContentBackgroundColor(activity: Activity, color: Int) {
        val content =
            activity.findViewById<View>(R.id.immersive_content)
        content?.setBackgroundColor(color)
    }

    fun setContentBackgroundColorResource(activity: Activity, colorRes: Int) {
        val content =
            activity.findViewById<View>(R.id.immersive_content)
        content?.setBackgroundResource(colorRes)
    }

    @JvmStatic
    fun setStatusBarColor(activity: Activity, color: Int) {
        val status =
            activity.findViewById<View>(R.id.immersive_status)
        status?.setBackgroundColor(color)
    }

    fun setStatusBarColorResource(activity: Activity, colorRes: Int) {
        val status =
            activity.findViewById<View>(R.id.immersive_status)
        status?.setBackgroundResource(colorRes)
    }

    @JvmStatic
    fun setNavigationBarColor(activity: Activity, color: Int) {
        val navigation =
            activity.findViewById<View>(R.id.immersive_navigation)
        navigation?.setBackgroundColor(color)
    }

    fun setNavigationBarColorResource(activity: Activity, colorRes: Int) {
        val navigation =
            activity.findViewById<View>(R.id.immersive_navigation)
        navigation?.setBackgroundResource(colorRes)
    }

    @JvmStatic
    fun setStatusContentColor(activity: Activity, mode: MODE): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val navigationBar = stateMap[activity.hashCode().toString()]?.navigationBar ?: true
            if (mode == MODE.WHITE) {
                activity.window.decorView.systemUiVisibility = systemUiFlag(false, navigationBar)
                stateMap[activity.hashCode().toString()]?.stateBar = false
            } else {
                activity.window.decorView.systemUiVisibility = systemUiFlag(true, navigationBar)
                stateMap[activity.hashCode().toString()]?.stateBar = true
            }
            true
        } else {
            false
        }
    }

    @JvmStatic
    fun setNavigationContentColor(activity: Activity, mode: MODE): Boolean {
        return if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            val stateBar = stateMap[activity.hashCode().toString()]?.stateBar ?: true
            if (mode == MODE.WHITE) {
                activity.window.decorView.systemUiVisibility = systemUiFlag(stateBar, false)
                stateMap[activity.hashCode().toString()]?.navigationBar = false
            } else {
                activity.window.decorView.systemUiVisibility = systemUiFlag(stateBar, true)
                stateMap[activity.hashCode().toString()]?.navigationBar = true
            }
            true
        } else {
            false
        }
    }

    fun layoutView(activity: Activity): View? {
        val group = activity.findViewById<ViewGroup>(R.id.immersive_content)
        return group?.getChildAt(0)
    }

    enum class MODE {
        WHITE, BLACK
    }
}