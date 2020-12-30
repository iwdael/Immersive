package com.hacknife.immersive

import android.content.Context
import android.util.AttributeSet
import android.view.View
import com.hacknife.immersive.ImmersiveHelper.getActivityOrientationForContext
import com.hacknife.immersive.ImmersiveHelper.getNavigationBarHeight

class NavigationView @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    private val orientation: Orientation
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width: Int
        val height: Int
        if (orientation == Orientation._0 || orientation == Orientation._180) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = getNavigationBarHeight(context)
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec)
            width = getNavigationBarHeight(context)
        }
        setMeasuredDimension(width, height)
    }

    init {
        orientation = getActivityOrientationForContext(context!!)
    }
}