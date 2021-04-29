package com.example.stepcount.utils;

import android.content.Context;
import android.content.SharedPreferences;


/**
 * @ClassName: PreferencesHelper
 * @Description: (公用类 ， 用于缓存一些key — — value类型的数据)
 */

public class PreferencesHelper {

    public static final String APP_SHARD = "com_example_step_share_prefs";

    //系统运行时间
    public static final String ELAPSED_REALTIMEl = "elapsed_realtime";

    /**
     * Get SharedPreferences
     */
    private static SharedPreferences getSharedPreferences(Context context) {
        return context.getSharedPreferences(APP_SHARD, Context.MODE_PRIVATE);
    }

    public static void setElapsedRealtime(Context context, long elapsedRealtime) {
        getSharedPreferences(context).edit().putLong(ELAPSED_REALTIMEl, elapsedRealtime).commit();
    }

    public static long getElapsedRealtime(Context context) {
        return getSharedPreferences(context).getLong(ELAPSED_REALTIMEl, 0L);
    }
}
