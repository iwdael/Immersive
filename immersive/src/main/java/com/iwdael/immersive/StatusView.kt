package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.iwdael.immersive.ext.notifyLayoutParam

/**
 * author  : Iwdael
 * e-mail  : iwdael@qq.com
 * github  : http://github.com/iwdael
 * project : Immersive
 */
class StatusView(
    private val activity: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0,
    private val isFromImmersive: Boolean
) : View(activity, attrs, defStyleAttr) {

    constructor(context: Context, attrs: AttributeSet? = null, defStyleAttr: Int = 0)
            : this(context, attrs, defStyleAttr, false)

    constructor(context: Context, attrs: AttributeSet? = null)
            : this(context, attrs, 0, false)

    constructor(context: Context)
            : this(context, null, 0, false)

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        val width = MeasureSpec.getSize(widthMeasureSpec)
        val height = currentPhoneRom.statusBarHeight(context)
        setMeasuredDimension(width, height)
    }

    companion object {
        const val BUNDLE_SUPER = "STATUS_VIEW_BUNDLE_SUPER"
        const val BUNDLE_VISIBILITY = "STATUS_VIEW_VISIBILITY"

    }

    override fun onSaveInstanceState(): Parcelable? {
        if (!isFromImmersive) return super.onSaveInstanceState()
        val bundle = Bundle()
        bundle.putParcelable(BUNDLE_SUPER, super.onSaveInstanceState())
        bundle.putBoolean(BUNDLE_VISIBILITY, visibility == VISIBLE)
        return bundle
    }

    override fun onRestoreInstanceState(state: Parcelable) {
        if (!isFromImmersive) return super.onRestoreInstanceState(state)
        state as Bundle
        visibility = if (state.getBoolean(BUNDLE_VISIBILITY)) VISIBLE else GONE
        super.onRestoreInstanceState(state.getParcelable(BUNDLE_SUPER))
        if (activity is Activity) activity.notifyLayoutParam()

    }
}