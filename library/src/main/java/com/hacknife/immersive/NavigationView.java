package com.hacknife.immersive;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class NavigationView extends View {
    private Orientation orientation;

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        orientation = ImmersiveHelper.getActivityOrientationForContext(context);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width;
        int height;
        if (orientation == Orientation._0 || orientation == Orientation._180) {
            width = View.MeasureSpec.getSize(widthMeasureSpec);
            height = ImmersiveHelper.getNavigationBarHeight(getContext());
        } else {
            height = View.MeasureSpec.getSize(heightMeasureSpec);
            width = ImmersiveHelper.getNavigationBarHeight(getContext());
        }
        setMeasuredDimension(width, height);
    }
}
