package com.ruqi.eduos.mesc;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * محرك التخزين المحلي (Offline Persistence)
 * يحفظ بيانات المعلم والتحاضير داخل الجهاز مباشرة
 */
public class DatabaseEngine {
    private static final String DB_NAME = "RuqiEduOS_DB";

    // دالة حفظ البيانات
    public static void save(Context context, String key, String value) {
        SharedPreferences prefs = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        prefs.edit().putString(key, value).apply();
    }

    // دالة استرجاع البيانات
    public static String load(Context context, String key) {
        SharedPreferences prefs = context.getSharedPreferences(DB_NAME, Context.MODE_PRIVATE);
        return prefs.getString(key, "لا توجد بيانات محفوظة");
    }
}
