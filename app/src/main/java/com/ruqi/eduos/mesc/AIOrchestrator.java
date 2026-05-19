package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class AIOrchestrator {
    private static final String SYSTEM_CONTEXT = 
        "أنت مساعد معلم خبير بالمنهاج الفلسطيني. اتبع التدرج التربوي (محسوس-شبه محسوس-مجرد). " +
        "استخدم الأرقام المشرقية. كن مباشراً وعملياً.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        String fullPrompt = SYSTEM_CONTEXT + "\n" + "المهمة: " + prompt;
        
        new Thread(() -> {
            String result = GeminiClient.sendMessage(fullPrompt);
            new Handler(Looper.getMainLooper()).post(() -> {
                if (callback != null) callback.onResponse(result);
            });
        }).start();
    }
}
