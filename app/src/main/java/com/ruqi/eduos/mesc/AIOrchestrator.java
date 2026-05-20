package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AIOrchestrator {
    private static final String TAG = "AIOrchestrator";
    
    private static final String SYSTEM_BRAIN = 
        "أنت نظام EduOS الذكي المطور للمنهاج الفلسطيني. " +
        "التزم بالتدرج التربوي الحاسم: المحسوس -> شبه المحسوس -> المجرد. " +
        "استخدم الأرقام المشرقية ٠١٢٣٤٥٦٧٨٩ حصرياً في مخرجاتك واجعل المتن نظيفاً تماماً للطلاب.";

    public static void process(final Context context, final String prompt, final AIProcessor.Callback callback) {
        if (context == null || callback == null) return;

        final String fullPrompt = SYSTEM_BRAIN + "\nطلب المعلم الحالي: " + prompt;

        new Thread(new Runnable() {
            @Override
            public void run() {
                String finalResult = "";
                boolean success = false;

                // المحاولة الأولى: Gemini
                try {
                    Log.d(TAG, "تنشيط المحرك الأول: Gemini...");
                    finalResult = GeminiClient.sendMessage(fullPrompt);
                    success = true;
                } catch (Exception e) {
                    Log.e(TAG, "فشل محرك جيميناي، جاري التبديل التلقائي الفوري...", e);
                }

                // المحاولة الثانية: Groq
                if (!success) {
                    try {
                        Log.d(TAG, "تنشيط المحرك البديل: Groq...");
                        finalResult = GroqClient.sendMessage(fullPrompt);
                        success = true;
                    } catch (Exception e) {
                        Log.e(TAG, "فشل محرك غروك، جاري الانتقال لخط الدفاع النهائي...", e);
                    }
                }

                // المحاولة الثالثة: OpenAI ChatGPT
                if (!success) {
                    try {
                        Log.d(TAG, "تنشيط المحرك النهائي: OpenAI...");
                        finalResult = OpenAIClient.sendMessage(fullPrompt);
                        success = true;
                    } catch (Exception e) {
                        Log.e(TAG, "فشل جميع المحركات الذكية", e);
                        finalResult = "تم صد الاتصال من جميع المحركات. يرجى التحقق من صحة الأسرار وحالة الشبكة.";
                    }
                }

                final String resultToSend = finalResult;
                new Handler(Looper.getMainLooper()).post(new Runnable() {
                    @Override
                    public void run() {
                        callback.onResponse(resultToSend);
                    }
                });
            }
        }).start();
    }
}
