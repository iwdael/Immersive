package com.hacknife.immersive

import android.content.Context
import android.util.AttributeSet
import android.util.Log
import android.view.View
import com.hacknife.immersive.ImmersiveHelper.getActivityOrientationForContext
import com.hacknife.immersive.ImmersiveHelper.getNavigationBarHeight

class NavigationView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val orientation: Orientation by lazy { getActivityOrientationForContext(context!!) }
    private var hide = false
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width: Int
        val height: Int
        if (orientation == Orientation._0 || orientation == Orientation._180) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = if (hide) 0 else getNavigationBarHeight(context)
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec)
            width = if (hide) 0 else getNavigationBarHeight(context)
        }
        setMeasuredDimension(width, height)
    }

    fun hide() {
        hide = true
    }

}