package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AIOrchestrator {
    // هذه "البوصلة التعليمية" ثابتة، التطبيق سيعمل بناءً عليها دائماً
    private static final String UNIVERSAL_INSTRUCTION = 
        "أنت مساعد معلم خبير. إذا طلب المستخدم 'تحضير درس' قم بالتحضير. " +
        "إذا طلب أي شيء تعليمي آخر (خطة، حل، فكرة)، نفذ المهمة مباشرة بأسلوب تربوي. " +
        "استخدم الأرقام المشرقية. كن مباشراً. اتبع تدرج: محسوس -> شبه محسوس -> مجرد.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        String fullPrompt = UNIVERSAL_INSTRUCTION + "\nالمستخدم يطلب: " + prompt;
        
        new Thread(() -> {
            String result = GeminiClient.sendMessage(fullPrompt);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (callback != null) callback.onResponse(result);
            });
        }).start();
    }
}
