package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AIOrchestrator {
    
    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        // تشغيل في خلفية لمنع توقف الواجهة (NetworkOnMainThreadException)
        new Thread(() -> {
            String result = GeminiClient.sendMessage(prompt);
            // العودة لـ UI Thread لتحديث الواجهة
            new Handler(Looper.getMainLooper()).post(() -> {
                callback.onResponse(result);
            });
        }).start();
    }
}
