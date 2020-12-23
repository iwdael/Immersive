package com.hacknife.immersive;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.Surface;
import android.view.WindowManager;

import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_BEHIND;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_FULL_USER;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_LOCKED;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_NOSENSOR;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_REVERSE_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_SENSOR_PORTRAIT;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_UNSPECIFIED;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_LANDSCAPE;
import static android.content.pm.ActivityInfo.SCREEN_ORIENTATION_USER_PORTRAIT;

/**
 * author  : Hacknife
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/hacknife
 * project : Immersive
 */
public class ImmersiveHelper {
    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    public static boolean hasNavigationBarShow(Activity activity) {
        WindowManager wm = activity.getWindowManager();
        Display display = wm.getDefaultDisplay();
        DisplayMetrics outMetrics = new DisplayMetrics();
        //获取整个屏幕的高度
        display.getRealMetrics(outMetrics);
        int heightPixels = outMetrics.heightPixels;
        int widthPixels = outMetrics.widthPixels;
        //获取内容展示部分的高度
        outMetrics = new DisplayMetrics();
        display.getMetrics(outMetrics);
        int heightPixels2 = outMetrics.heightPixels;
        int widthPixels2 = outMetrics.widthPixels;
        int w = widthPixels - widthPixels2;
        int h = heightPixels - heightPixels2;
        return !(w == 0 && h == 0);//竖屏和横屏两种情况。
    }

    public static DisplayMetrics getScreenPix(Activity activity) {
        DisplayMetrics displaysMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displaysMetrics);
        return displaysMetrics;
    }

    private static int getSystemComponentDimen(Context context, String dimenName) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        int statusHeight = 0;
        try {
            Class<?> clazz = Class.forName("com.android.internal.R$dimen");
            Object object = clazz.newInstance();
            String heightStr = clazz.getField(dimenName).get(object).toString();
            int height = Integer.parseInt(heightStr);
            //dp--->px
            statusHeight = context.getResources().getDimensionPixelSize(height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return statusHeight;
    }

    public static int getNavigationBarHeight(Context context) {
        Activity activity = getActivity(context);
        if (activity != null) {
            if (!hasNavigationBarShow(activity)) return 0;
        }
        return getSystemComponentDimen(context, "navigation_bar_height");
    }

    public static int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(context, "status_bar_height");
    }

    public static Activity getActivity(Context context) {
        if (context instanceof Activity) return (Activity) context;
        return null;
    }


    public static Orientation getActivityOrientationForContext(Context context) {
        WindowManager wm = ((WindowManager) context.getSystemService(Context.WINDOW_SERVICE));
        if (wm == null) return Orientation._0;
        int angle = wm.getDefaultDisplay().getRotation();
        switch (angle) {
            case Surface.ROTATION_90:
                Log.v("dzq", "Orientation._90");
                return Orientation._90;
            case Surface.ROTATION_180:
                Log.v("dzq", "Orientation._180");
                return Orientation._180;
            case Surface.ROTATION_270:
                Log.v("dzq", "Orientation._270");
                return Orientation._270;
            default:
                Log.v("dzq", "Orientation._0");
                return Orientation._0;
        }
    }


    /**
     * The preferred screen orientation this activity would like to run in.
     * From the {@link android.R.attr#screenOrientation} attribute, one of

     */

    public static Orientation getActivityOrientationForActivity(Activity context) {
        int screenOrientation = context.getWindow().getAttributes().screenOrientation;
        if (screenOrientation == SCREEN_ORIENTATION_UNSPECIFIED) {
            return Orientation._0;
        } else if (screenOrientation == SCREEN_ORIENTATION_LANDSCAPE) {
            return Orientation._90;
        } else if (screenOrientation == SCREEN_ORIENTATION_PORTRAIT) {
            return Orientation._90;
        } else if (screenOrientation == SCREEN_ORIENTATION_USER) {
        } else if (screenOrientation == SCREEN_ORIENTATION_BEHIND) {
        } else if (screenOrientation == SCREEN_ORIENTATION_SENSOR) {
        } else if (screenOrientation == SCREEN_ORIENTATION_NOSENSOR) {
        } else if (screenOrientation == SCREEN_ORIENTATION_SENSOR_LANDSCAPE) {
        } else if (screenOrientation == SCREEN_ORIENTATION_SENSOR_PORTRAIT) {
        } else if (screenOrientation == SCREEN_ORIENTATION_REVERSE_LANDSCAPE) {
        } else if (screenOrientation == SCREEN_ORIENTATION_REVERSE_PORTRAIT) {
        } else if (screenOrientation == SCREEN_ORIENTATION_USER_LANDSCAPE) {
        } else if (screenOrientation == SCREEN_ORIENTATION_USER_PORTRAIT) {
        } else if (screenOrientation == SCREEN_ORIENTATION_FULL_USER) {
        } else if (screenOrientation == SCREEN_ORIENTATION_LOCKED) {

        }
        return Orientation._0;
    }
}
