package com.iwdael.immersive

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import com.iwdael.immersive.ImmersiveHelper.getStatusBarHeight

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/iwdael
 * project : Immersive
 */
class StatusView @JvmOverloads constructor(
    context: Context?,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {
    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = getStatusBarHeight(context)
        setMeasuredDimension(width, height)
    }
}