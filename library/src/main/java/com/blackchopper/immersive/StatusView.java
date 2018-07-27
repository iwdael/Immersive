package com.blackchopper.immersive;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.blackchopper.immersive.ImmersiveHelper;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : Immersive
 */
public class StatusView extends View {


    public StatusView(Context context) {
        super(context);
    }

    public StatusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public StatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = ImmersiveHelper.getStatusBarHeight(getContext());
        setMeasuredDimension(width, height);
    }
}
