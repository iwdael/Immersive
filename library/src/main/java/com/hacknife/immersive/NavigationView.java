package com.hacknife.immersive;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;

public class NavigationView extends View {

    public NavigationView(Context context) {
        this(context, null);
    }

    public NavigationView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public NavigationView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = ImmersiveHelper.getNavigationBarHeight(getContext());
        setMeasuredDimension(width, height);
    }
}
