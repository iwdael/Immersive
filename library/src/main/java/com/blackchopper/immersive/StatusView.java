package com.blackchopper.immersive;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import com.blackchopper.immersive.R.id;
import com.blackchopper.immersive.ImmersiveHelper;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : Immersive
 */
public class StatusView extends View {


    public StatusView(Context context) {
        this(context,null);
    }

    public StatusView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs,0);
    }

    public StatusView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setId(id.status);
    }



    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int width = View.MeasureSpec.getSize(widthMeasureSpec);
        int height = ImmersiveHelper.getStatusBarHeight(getContext());
        setMeasuredDimension(width, height);
    }
}
