package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AIOrchestrator {
    private static final String TAG = "AIOrchestrator";
    private static final String SYSTEM_BRAIN = 
        "أنت نظام EduOS الذكي المطور للمنهاج الفلسطيني. " +
        "المطلوب منك: تلبية كافة الأوامر والميزات التعليمية التي يطلبها المستخدم مباشرة من الشاشة دون كتابة أكواد جافا إضافية. " +
        "التزم بالتدرج التربوي الحاسم: المحسوس (أمثلة مرئية) -> شبه المحسوس (مسائل لفظية) -> المجرد (رموز وعمليات). " +
        "استخدم الأرقام المشرقية ٠١٢٣٤٥٦٧٨٩ حصرياً في كافة جداولك ومخرجاتك واجعل المتن نظيفاً تماماً وجاهزاً لاستخدام الطلاب مباشرة.";

    public static void process(Context context, String prompt, AIProcessor.Callback callback) {
        if (context == null || callback == null) return;
        
        String fullPrompt = SYSTEM_BRAIN + "\nطلب المعلم الحالي للتنفيذ الفوري والتطوير التراكمي الداخلي: " + prompt;
        
        new Thread(() -> {
            try {
                String result = GeminiClient.sendMessage(fullPrompt);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(result));
            } catch (Exception e) {
                Log.e(TAG, "Orchestrator Thread Error Checked", e);
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("حدث خطأ في تسيير المعالجة الذاتية."));
            }
        }).start();
    }
}
