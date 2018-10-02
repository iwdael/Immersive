package com.hacknife.immersive;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.LayoutRes;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.widget.FrameLayout;


import com.hacknife.immersive.R.color;
import com.hacknife.immersive.R.id;


/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */
public class Immersive {
    private static final String TAG = Immersive.class.getName();

    public Immersive() {
    }

    private static void compatible(Activity activity, boolean navigationEmbed) {
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
            activity.getWindow().requestFeature(1);
            activity.getWindow().addFlags(67108864);
            activity.getWindow().addFlags(134217728);
            Log.i(TAG, "compatible: system version < 21");
        }

        if (VERSION.SDK_INT >= 21) {
            Log.i(TAG, "compatible: system version >= 21");
            if (navigationEmbed) {
                activity.getWindow().addFlags(134217728);
            }

            activity.getWindow().getDecorView().setSystemUiVisibility(1280);
            activity.getWindow().setStatusBarColor(activity.getResources().getColor(color.immersive_translucent));
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(color.immersive_translucent));
        }

    }

    public static void setContentView(Activity activity, @LayoutRes int layoutRes, int statusRes, int navigationRes, boolean statusEmbed, boolean navigationEmbed) {
        compatible(activity, navigationEmbed);
        activity.setContentView(R.layout.activity_immersive);
        FrameLayout immersive_status = activity.findViewById(R.id.immersive_status);
        FrameLayout immersive_content = activity.findViewById(R.id.immersive_content);
        FrameLayout immersive_navigation = activity.findViewById(R.id.immersive_navigation);


        if (!statusEmbed) {

            StatusView status = new StatusView(activity);
            status.setBackgroundResource(statusRes);
            immersive_status.addView(status);
            immersive_content.addView(LayoutInflater.from(activity).inflate(layoutRes, (ViewGroup) null));

            if (VERSION.SDK_INT >= 21 && !navigationEmbed) {
                activity.getWindow().setNavigationBarColor(activity.getResources().getColor(navigationRes));
            }

            if (VERSION.SDK_INT < 21 && VERSION.SDK_INT >= 19 && !navigationEmbed && ImmersiveHelper.hasNavigationBarShow(activity)) {
                View navigationBar = new View(activity);
                navigationBar.setId(id.navigation);
                LayoutParams navigatioParams = new LayoutParams(-1, ImmersiveHelper.getNavigationBarHeight(activity));
                navigationBar.setLayoutParams(navigatioParams);
                navigationBar.setBackgroundResource(navigationRes);
                immersive_navigation.addView(navigationBar);
            }

        } else if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21 && !navigationEmbed && ImmersiveHelper.hasNavigationBarShow(activity)) {

            immersive_content.addView(LayoutInflater.from(activity).inflate(layoutRes, (ViewGroup) null));

            View navigation = new View(activity);
            navigation.setId(id.navigation);
            LayoutParams navigation_layout = new LayoutParams(-1, ImmersiveHelper.getNavigationBarHeight(activity));
            navigation.setLayoutParams(navigation_layout);
            navigation.setBackgroundResource(navigationRes);
            immersive_navigation.addView(navigation);
        } else {
            if (VERSION.SDK_INT >= 21 && !navigationEmbed) {
                activity.getWindow().setNavigationBarColor(activity.getResources().getColor(navigationRes));
            }
            immersive_content.addView(LayoutInflater.from(activity).inflate(layoutRes, (ViewGroup) null));
        }

    }

    public static void setStatusBarColorRes(Activity activity, int colorRes) {
        View status = activity.findViewById(id.status);
        if (status != null) {
            status.setBackgroundResource(colorRes);
        }

    }

    public static void setNavigationBarColorRes(Activity activity, int colorRes) {
        View navigation = activity.findViewById(id.navigation);
        if (navigation != null) {
            navigation.setBackgroundResource(colorRes);
        }

        if (VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(activity.getResources().getColor(colorRes));
        }

    }

    public static void setStatusBarColor(Activity activity, int color) {
        View status = activity.findViewById(id.status);
        if (status != null) {
            status.setBackgroundColor(color);
        }

    }

    public static void setNavigationBarColor(Activity activity, int color) {
        View navigation = activity.findViewById(id.navigation);
        if (navigation != null) {
            navigation.setBackgroundColor(color);
        }

        if (VERSION.SDK_INT >= 21) {
            activity.getWindow().setNavigationBarColor(color);
        }

    }

    public static boolean setStatusBarLightMode(Activity activity) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            activity.getWindow().getDecorView().setSystemUiVisibility(activity.getWindow().getDecorView().getSystemUiVisibility() | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            return true;
        } else {
            return false;
        }
    }
}
