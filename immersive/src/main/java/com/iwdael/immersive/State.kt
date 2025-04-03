package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.view.Surface
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import java.lang.ref.WeakReference

class State private constructor(
    var host: WeakReference<Activity>,
    var colorStatusBar: Int,
    var colorNavBar: Int,
    var stateStatusBar: BarState,
    var stateNavBar: BarState,
    var isLightStatusBar: Boolean,
    var isLightNavBar: Boolean,
    var angle: Angle
) : ViewModel() {
    companion object {
        private val map = mutableMapOf<Int, State>()
        fun AppCompatActivity.stateInit(
            colorStatusBar: Int,
            colorNavBar: Int,
            stateStatusBar: BarState,
            stateNavBar: BarState,
            isLightStatusBar: Boolean,
            isLightNavBar: Boolean
        ): State {
            val factory = object : ViewModelProvider.Factory {
                override fun <T : ViewModel> create(modelClass: Class<T>): T {
                    return State(WeakReference(this@stateInit), colorStatusBar, colorNavBar, stateStatusBar, stateNavBar, isLightStatusBar, isLightNavBar, angle()) as T
                }
            }
            val state = ViewModelProvider(this, factory)[State::class.java]
            state.host = WeakReference(this)
            state.angle = angle()
            map[hashCode()] = state
            return state
        }

        fun Activity.immersiveState(): State {
            return map[hashCode()] ?: throw RuntimeException("请使用Immersive.setContentView初始化布局")
        }

        fun Activity.immersiveStateMap(): List<State> = map.values.toList()

        internal fun Activity.angle(): Angle {
            val wm = getSystemService(Context.WINDOW_SERVICE) as WindowManager? ?: return Angle.Angle0
            return when (wm.defaultDisplay.rotation) {
                Surface.ROTATION_90 -> {
                    Angle.Angle90
                }

                Surface.ROTATION_180 -> {
                    Angle.Angle180
                }

                Surface.ROTATION_270 -> {
                    Angle.Angle270
                }

                else -> {
                    Angle.Angle0
                }
            }
        }
    }
}