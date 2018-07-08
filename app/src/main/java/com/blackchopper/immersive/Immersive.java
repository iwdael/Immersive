package com.blackchopper.immersive;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.support.annotation.LayoutRes;
import android.support.annotation.RequiresApi;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.LinearLayout;

/**
 * author  : Black Chopper
 * e-mail  : 4884280@qq.com
 * github  : http://github.com/BlackChopper
 * project : Immersive
 */
public class Immersive {


    public static void compatible(Activity activity, boolean navigationEmbed) {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT && android.os.Build.VERSION.SDK_INT < android.os.Build.VERSION_CODES.LOLLIPOP) {
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
            //设置虚拟导航栏为透明
            activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
        }
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            //>=5.0
            if (navigationEmbed)
                activity.getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION);
            activity.getWindow().setStatusBarColor(Color.TRANSPARENT);
            activity.getWindow().setNavigationBarColor(Color.TRANSPARENT);
            activity.getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
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


    @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN_MR1)
    private static boolean hasNavigationBarShow(Activity activity) {
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
        return w > 0 || h > 0;//竖屏和横屏两种情况。
    }

    private static DisplayMetrics getScreenPix(Activity activity) {
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

    private static int getNavigationBarHeight(Context context) {
        return getSystemComponentDimen(context, "navigation_bar_height");
    }

    public static int getStatusBarHeight(Context context) {
        // 反射手机运行的类：android.R.dimen.status_bar_height.
        return getSystemComponentDimen(context, "status_bar_height");
    }
}
