package com.hacknife.immersive;

import android.app.Activity;
import android.os.Build;
import android.os.Build.VERSION;
import android.support.annotation.LayoutRes;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;


import static android.view.View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION;
import static android.view.Window.FEATURE_NO_TITLE;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION;
import static android.view.WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;


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

    private static void compatible(Activity activity) {
        if (VERSION.SDK_INT >= 19 && VERSION.SDK_INT < 21) {
            activity.getWindow().requestFeature(FEATURE_NO_TITLE);
            activity.getWindow().addFlags(FLAG_TRANSLUCENT_STATUS);
            activity.getWindow().addFlags(FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (VERSION.SDK_INT >= 21) {
            activity.getWindow().addFlags(SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION);
        }
    }

    public static void setContentView(Activity activity, @LayoutRes int layoutRes, int statusRes, int navigationRes, boolean statusEmbed, boolean navigationEmbed) {
        compatible(activity);
        Orientation orientation = ImmersiveHelper.getActivityOrientationForContext(activity);
        if (orientation== Orientation._0 || orientation == Orientation._180){
            activity.setContentView(R.layout.activity_immersive_vertical);
        }else  if (orientation == Orientation._90){
            activity.setContentView(R.layout.activity_immersive_horizontal_90);
        } else {
            activity.setContentView(R.layout.activity_immersive_horizontal_270);
        }
        StatusView statusView = activity.findViewById(R.id.immersive_status);
        FrameLayout content = activity.findViewById(R.id.immersive_content);
        NavigationView navigationView = activity.findViewById(R.id.immersive_navigation);
        statusView.setBackgroundResource(statusRes);
        navigationView.setBackgroundResource(navigationRes);
        if (statusEmbed) statusView.setVisibility(View.GONE);
        if (navigationEmbed) navigationView.setVisibility(View.GONE);
        content.addView(LayoutInflater.from(activity).inflate(layoutRes, null));
    }

    public static void hideStatus(Activity activity) {
        StatusView statusView = activity.findViewById(R.id.immersive_status);
        if (statusView != null) statusView.setVisibility(View.GONE);
    }

    public static boolean isShowOfStatus(Activity activity) {
        StatusView statusView = activity.findViewById(R.id.immersive_status);
        if (statusView != null) return statusView.getVisibility() == View.VISIBLE;
        return false;
    }

    public static boolean isShowOfNavigation(Activity activity) {
        NavigationView navigationView = activity.findViewById(R.id.immersive_navigation);
        if (navigationView != null) return navigationView.getVisibility() == View.VISIBLE;
        return false;
    }


    public static void showStatus(Activity activity) {
        StatusView statusView = activity.findViewById(R.id.immersive_status);
        if (statusView != null) statusView.setVisibility(View.VISIBLE);
    }

    public static void hideNavigation(Activity activity) {
        NavigationView navigationView = activity.findViewById(R.id.immersive_navigation);
        if (navigationView != null) navigationView.setVisibility(View.GONE);
    }

    public static void showNavigation(Activity activity) {
        NavigationView navigationView = activity.findViewById(R.id.immersive_navigation);
        if (navigationView != null) navigationView.setVisibility(View.VISIBLE);
    }


    public static void setContentBackgroundColor(Activity activity, int color) {
        View content = activity.findViewById(R.id.immersive_content);
        if (content != null) {
            content.setBackgroundColor(color);
        }
    }

    public static void setContentBackgroundColorResource(Activity activity, int colorRes) {
        View content = activity.findViewById(R.id.immersive_content);
        if (content != null) {
            content.setBackgroundResource(colorRes);
        }
    }


    public static void setStatusBarColor(Activity activity, int color) {
        View status = activity.findViewById(R.id.immersive_status);
        if (status != null) {
            status.setBackgroundColor(color);
        }

    }

    public static void setStatusBarColorResource(Activity activity, int colorRes) {
        View status = activity.findViewById(R.id.immersive_status);
        if (status != null) {
            status.setBackgroundResource(colorRes);
        }
    }

    public static void setNavigationBarColor(Activity activity, int color) {
        View navigation = activity.findViewById(R.id.immersive_navigation);
        if (navigation != null) {
            navigation.setBackgroundColor(color);
        }
    }

    public static void setNavigationBarColorResource(Activity activity, int colorRes) {
        View navigation = activity.findViewById(R.id.immersive_navigation);
        if (navigation != null) {
            navigation.setBackgroundResource(colorRes);
        }
    }

    public static boolean setStatusContentColor(Activity activity, MODE mode) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mode == MODE.BLACK)
                activity.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);

            else if (mode == MODE.WHITE)
                activity.getWindow()
                        .getDecorView()
                        .setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN | View.SYSTEM_UI_FLAG_LAYOUT_STABLE);
            return true;
        } else {
            return false;
        }
    }

    public static boolean setNavigationContentColor(Activity activity, MODE mode) {
        if (VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            if (mode == MODE.BLACK)
                activity.getWindow().clearFlags(FLAG_TRANSLUCENT_NAVIGATION);
            else if (mode == MODE.WHITE)
                activity.getWindow().addFlags(FLAG_TRANSLUCENT_NAVIGATION);
            return true;
        } else {
            return false;
        }
    }

    public static View layoutView(Activity activity) {
        ViewGroup group = activity.<ViewGroup>findViewById(R.id.immersive_content);
        if (group != null) return group.getChildAt(0);
        else return null;
    }

    public enum MODE {
        WHITE, BLACK
    }
}
