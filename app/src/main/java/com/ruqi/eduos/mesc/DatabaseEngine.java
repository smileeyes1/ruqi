package com.ruqi.eduos.mesc;

import android.content.Context;
import android.content.SharedPreferences;

public class DatabaseEngine {
    private static final String PREF_NAME = "EduOS_DB";

    public static void save(Context context, String key, String value) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        pref.edit().putString(key, value).apply();
    }

    public static String load(Context context, String key) {
        SharedPreferences pref = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE);
        return pref.getString(key, "لا توجد بيانات محفوظة");
    }

    // الدالة الجديدة التي يحتاجها مشروعك
    public static String getApiKey(Context context) {
        return load(context, "API_KEY");
    }
}
