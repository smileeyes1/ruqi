package com.ruqi.eduos.mesc;

import android.content.Context;

public interface AppCommand {
    String getName(); // اسم الميزة (مثلاً: "تحضير")
    void execute(Context context, String input, AIProcessor.Callback callback);
}
