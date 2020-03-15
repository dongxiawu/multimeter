package personal.dongxia.android.utils;

import android.content.Context;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;

/**
 * @author wudongxia
 * @date 2020-03-15
 */
public class AppUtils {
    /**
     * 由于应用启动后这些参数都不变，故不需要每次都获取
     */
    private static String sAppName;
    private static String sVersionName;
    private static long sVersionCode = 0;
    private static String sPackageName;

    /**
     * 获取应用程序名称
     */
    public static String getAppName(Context context) {
        if (!TextUtils.isEmpty(sAppName)) {
            return sAppName;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            int labelRes = packageInfo.applicationInfo.labelRes;
            sAppName = context.getResources().getString(labelRes);
            return sAppName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getVersionName(Context context) {
        if (!TextUtils.isEmpty(sVersionName)) {
            return sVersionName;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            sVersionName = packageInfo.versionName;
            return sVersionName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static long getVersionCode(Context context) {
        if (sVersionCode != 0) {
            return sVersionCode;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            sVersionCode = packageInfo.getLongVersionCode();
            return sVersionCode;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return 0;
    }


    /**
     * [获取应用程序版本名称信息]
     * @param context
     * @return 当前应用的版本名称
     */
    public static String getPackageName(Context context) {
        if (!TextUtils.isEmpty(sPackageName)) {
            return sPackageName;
        }
        try {
            PackageManager packageManager = context.getPackageManager();
            PackageInfo packageInfo = packageManager.getPackageInfo(
                context.getPackageName(), 0);
            sPackageName = packageInfo.packageName;
            return sPackageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * 获取图标 bitmap
     * @param context
     */
    public static Bitmap getBitmap(Context context) {
        PackageManager packageManager = null;
        ApplicationInfo applicationInfo = null;
        try {
            packageManager = context.getApplicationContext()
                .getPackageManager();
            applicationInfo = packageManager.getApplicationInfo(
                context.getPackageName(), 0);
        } catch (PackageManager.NameNotFoundException e) {
            applicationInfo = null;
        }
        //xxx根据自己的情况获取drawable
        Drawable d = packageManager.getApplicationIcon(applicationInfo);
        BitmapDrawable bd = (BitmapDrawable) d;
        Bitmap bm = bd.getBitmap();
        return bm;
    }
}
