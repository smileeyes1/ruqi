package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AIOrchestrator {
    private static final String TAG = "AIOrchestrator";
    private static final String SYSTEM_BRAIN = 
        "أنت مساعد معلم خبير ملتزم بالمنهاج الفلسطيني. " +
        "المطلوب منك: استقبال أي طلب تعليمي أو أمر برمجي من المستخدم، وتنفيذه فوراً مع التطور التراكمي التلقائي بناءً على سياق الحوار. " +
        "اتبع دائماً التدرج التربوي من المحسوس (أمثلة مرئية وسياقات) ثم شبه المحسوس (مسائل لفظية) إلى المجرد (رموز وعمليات). " +
        "استخدم الأرقام المشرقية ٠١٢٣٤٥٦٧٨٩ حصرياً في كافة ردودك وجداولك ونصوصك، واجعل المحتوى نظيفاً ومباشراً.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        if (context == null || callback == null) return;
        
        String fullPrompt = SYSTEM_BRAIN + "\nطلب المستخدم للتنفيذ والتحديث الذاتي: " + prompt;
        
        new Thread(() -> {
            try {
                String result = GeminiClient.sendMessage(fullPrompt);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(result));
            } catch (Exception e) {
                Log.e(TAG, "Error in background processing thread", e);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("حدث خطأ غير متوقع أثناء معالجة الطلب ذاتياً."));
            }
        }).start();
    }
}
