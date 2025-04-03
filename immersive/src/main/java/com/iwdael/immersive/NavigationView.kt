package com.iwdael.immersive

import android.app.Activity
import android.content.Context
import android.os.Bundle
import android.os.Parcelable
import android.util.AttributeSet
import android.view.View
import com.iwdael.immersive.State.Companion.immersiveState
import com.iwdael.immersive.ext.notifyLayoutParam

class NavigationView @JvmOverloads constructor(
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
        val width: Int
        val height: Int
        activity as Activity
        val angle = activity.immersiveState().angle
        if (angle == Angle.Angle0 || angle == Angle.Angle180) {
            width = MeasureSpec.getSize(widthMeasureSpec)
            height = currentPhoneRom.navigationBarHeight(context)
        } else {
            height = MeasureSpec.getSize(heightMeasureSpec)
            width = currentPhoneRom.navigationBarHeight(context)
        }
        setMeasuredDimension(width, height)
    }


    companion object {
        const val BUNDLE_SUPER = "NAVIGATION_VIEW_BUNDLE_SUPER"
        const val BUNDLE_VISIBILITY = "NAVIGATION_VIEW_VISIBILITY"
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