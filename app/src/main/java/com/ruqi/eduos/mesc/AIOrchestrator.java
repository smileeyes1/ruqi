package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AIOrchestrator {
    // هذا النص هو الوحيد الذي ستحتاج لتغييره مستقبلاً إذا أردت إضافة "قدرات" جديدة للتطبيق
    private static final String SYSTEM_BRAIN = 
        "أنت مساعد معلم خبير. إذا طلب المستخدم أي شيء (تحضير درس، طباعة، جداول، اختبارات)، " +
        "فكر أولاً: هل تحتاج لأداة خاصة؟ " +
        "إذا كانت المهمة تعليمية، نفذها مباشرة بأسلوب تربوي (محسوس -> مجرد). " +
        "استخدم الأرقام المشرقية. لا تسألني كيف أعمل، بل تصرف كخبير.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        String fullPrompt = SYSTEM_BRAIN + "\nالمهمة: " + prompt;
        
        new Thread(() -> {
            // هنا يتم الربط مع Gemini مباشرة
            String result = GeminiClient.sendMessage(fullPrompt);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (callback != null) callback.onResponse(result);
            });
        }).start();
    }
}
