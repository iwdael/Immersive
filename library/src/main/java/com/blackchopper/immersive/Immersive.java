package com.blackchopper.immersive;

import android.app.Activity;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;

import static com.blackchopper.immersive.ImmersiveHelper.getNavigationBarHeight;
import static com.blackchopper.immersive.ImmersiveHelper.getScreenPix;
import static com.blackchopper.immersive.ImmersiveHelper.getStatusBarHeight;
import static com.blackchopper.immersive.ImmersiveHelper.hasNavigationBarShow;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : Immersive
 */
public class Immersive {
    private static final String TAG = Immersive.class.getName();

    private static void compatible(Activity activity, boolean navigationEmbed) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置虚拟导航栏为透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            Log.i(TAG, "compatible: system version < " + Build.VERSION_CODES.LOLLIPOP);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //>=5.0
            Log.i(TAG, "compatible: system version >= " + Build.VERSION_CODES.LOLLIPOP);
            if (navigationEmbed)
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(R.color.immersive_translucent));
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(R.color.immersive_translucent));
        }
    }

    public static void setContentView(Activity activity, @LayoutRes int layoutRes, int statusRes, int navigationRes, boolean statusEmbed, boolean navigationEmbed) {
        compatible(activity, navigationEmbed);
        if (!statusEmbed) {
            //container
            LinearLayout container = new LinearLayout(activity);
            container.setOrientation(LinearLayout.VERTICAL);
            ViewGroup.LayoutParams containerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
            container.setLayoutParams(containerParams);
            //status
            View statusBar = new View(activity);
            statusBar.setId(R.id.status);
            ViewGroup.LayoutParams statusParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getStatusBarHeight(activity));
            statusBar.setLayoutParams(statusParams);
            statusBar.setBackgroundResource(statusRes);
            container.addView(statusBar);
            //layout
            View layout = LayoutInflater.from(activity).inflate(layoutRes, null);
            if (!navigationEmbed && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT && hasNavigationBarShow(activity)) {
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getScreenPix(activity).heightPixels - getStatusBarHeight(activity));
                layout.setLayoutParams(layoutParams);
            }
            container.addView(layout);

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !navigationEmbed) {
                activity.getWindow().setNavigationBarColor(activity.getResources().getColor(navigationRes));
            }
            if (Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                    && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                    && !navigationEmbed
                    && hasNavigationBarShow(activity)) {
                View navigationBar = new View(activity);
                navigationBar.setId(R.id.navigation);
                ViewGroup.LayoutParams navigatioParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getNavigationBarHeight(activity));
                navigationBar.setLayoutParams(navigatioParams);
                navigationBar.setBackgroundResource(navigationRes);
                container.addView(navigationBar);
            }
            activity.setContentView(container);
        } else {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT
                    && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                    && !navigationEmbed
                    && hasNavigationBarShow(activity)) {
                //container
                LinearLayout container = new LinearLayout(activity);
                container.setOrientation(LinearLayout.VERTICAL);
                ViewGroup.LayoutParams containerParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
                container.setLayoutParams(containerParams);
                //layout
                View layout = LayoutInflater.from(activity).inflate(layoutRes, null);
                ViewGroup.LayoutParams layoutParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT,
                        getScreenPix(activity).heightPixels);
                layout.setLayoutParams(layoutParams);
                container.addView(layout);
                //navigation
                View navigationBar = new View(activity);
                navigationBar.setId(R.id.navigation);
                ViewGroup.LayoutParams navigatioParams = new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, getNavigationBarHeight(activity));
                navigationBar.setLayoutParams(navigatioParams);
                navigationBar.setBackgroundResource(navigationRes);
                container.addView(navigationBar);
                activity.setContentView(container);
            } else {

                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP && !navigationEmbed) {
                    activity.getWindow().setNavigationBarColor(activity.getResources().getColor(navigationRes));
                }
                activity.setContentView(layoutRes);
            }

        }
    }


    public static void setStatusBarColorRes(Activity activity, int colorRes) {
        View status = activity.findViewById(R.id.status);
        if (status != null)
            status.setBackgroundResource(colorRes);
    }

    public static void setNavigationBarColorRes(Activity activity, int colorRes) {
        View navigation = activity.findViewById(R.id.navigation);
        if (navigation != null)
            navigation.setBackgroundResource(colorRes);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(colorRes));
    }

    public static void setStatusBarColor(Activity activity, int color) {
        View status = activity.findViewById(R.id.status);
        if (status != null)
            status.setBackgroundColor(color);
    }

    public static void setNavigationBarColor(Activity activity, int color) {
        View navigation = activity.findViewById(R.id.navigation);
        if (navigation != null)
            navigation.setBackgroundColor(color);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP)
            activity.getWindow().setNavigationBarColor(color);
    }
}
