package com.ruqi.eduos.mesc;

import android.content.Context;
import android.content.SharedPreferences;

public class DatabaseEngine {

    public static void save(Context context, String key, String value) {
        context.getSharedPreferences("EduOS_DB", Context.MODE_PRIVATE).edit().putString(key, value).apply();
    }

    public static String load(Context context, String key) {
        return context.getSharedPreferences("EduOS_DB", Context.MODE_PRIVATE).getString(key, "لا توجد بيانات محفوظة");
    }

    public static void saveApiKey(Context context, String key) {
        context.getSharedPreferences("EduOS_Settings", Context.MODE_PRIVATE).edit().putString("API_KEY", key).apply();
    }

    public static String getApiKey(Context context) {
        return context.getSharedPreferences("EduOS_Settings", Context.MODE_PRIVATE).getString("API_KEY", "");
    }
}
