package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AIOrchestrator {
    private static final String SYSTEM_BRAIN = 
        "أنت نظام EduOS الذكي المبني على المنهاج الفلسطيني. " +
        "استقبل طلبات المستخدم البرمجية أو التعليمية ونفذها وتطور معها تلقائياً. " +
        "اتبع التدرج التربوي (محسوس -> شبه محسوس -> مجرد) واستخدم الأرقام المشرقية ٠١٢٣٤٥٦٧٨٩ دائماً في جميع النصوص والردود.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        if (context == null || callback == null) return;
        
        String fullPrompt = SYSTEM_BRAIN + "\nطلب المستخدم الحالي للتنفيذ والتطوير: " + prompt;
        
        new Thread(() -> {
            try {
                String result = GeminiClient.sendMessage(fullPrompt);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(result));
            } catch (Exception e) {
                Log.e("AIOrchestrator", "خطأ في خيط المعالجة الخلفي", e);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("حدث خطأ في المعالجة الذاتية."));
            }
        }).start();
    }
}
