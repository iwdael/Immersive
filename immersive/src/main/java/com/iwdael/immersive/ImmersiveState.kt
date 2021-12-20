package com.iwdael.immersive

import android.app.Activity
import java.lang.ref.WeakReference

/**
 * author : Iwdael(iwdael)
 * e-mail : iwdael@outlook.com
 * time   : 2019/8/5
 * version: 1.0
 */
class ImmersiveState(
    val immersive: WeakReference<Activity>,
    var statusBarContentIsDark: Boolean,
    var navigationBarContentIsDark: Boolean,
    var orientation: Orientation
)