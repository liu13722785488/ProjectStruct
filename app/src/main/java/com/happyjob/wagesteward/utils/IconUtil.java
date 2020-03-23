package com.happyjob.wagesteward.utils;

import android.app.Activity;
import android.app.ActivityManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;

import java.util.List;

public class IconUtil {
    private static String ACTIVITY_ALIAS_Main1="com.happyjob.wagesteward.MainActivity";
    private static String ACTIVITY_ALIAS_Main2="com.happyjob.wagesteward.RoundActivity";
    public static void setIcon(Context context, String enabledActivity) {

        PackageManager packageManager = context.getPackageManager();

        // 根据传入的enabledActivity判断是让ACTIVITY_ALIAS_1失效或生效
        packageManager
                .setComponentEnabledSetting(
                        new ComponentName(context,
                                ACTIVITY_ALIAS_Main1),
                        ACTIVITY_ALIAS_Main1.equals(enabledActivity) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);
        // 根据传入的enabledActivity判断是让ACTIVITY_ALIAS_Main2失效或生效
        packageManager
                .setComponentEnabledSetting(
                        new ComponentName(context,
                                ACTIVITY_ALIAS_Main2),
                        ACTIVITY_ALIAS_Main2.equals(enabledActivity) ? PackageManager.COMPONENT_ENABLED_STATE_ENABLED
                                : PackageManager.COMPONENT_ENABLED_STATE_DISABLED,
                        PackageManager.DONT_KILL_APP);

        // kill掉桌面app,让它自动重启。以便观察到动态更换后的Icon
        ActivityManager am = (ActivityManager) context
                .getSystemService(Activity.ACTIVITY_SERVICE);
        Intent i = new Intent(Intent.ACTION_MAIN);
        i.addCategory(Intent.CATEGORY_HOME);
        i.addCategory(Intent.CATEGORY_DEFAULT);
        List<ResolveInfo> resolves = packageManager.queryIntentActivities(i, 0);
        for (ResolveInfo res : resolves) {
            if (res.activityInfo != null) {
                am.killBackgroundProcesses(res.activityInfo.packageName);
            }
        }
    }
}
