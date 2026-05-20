package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AIOrchestrator {
    private static final String TAG = "AIOrchestrator";
    
    private static final String SYSTEM_BRAIN = 
        "أنت نظام EduOS الذكي المطور للمنهاج الفلسطيني. " +
        "التزم بالتدرج التربوي الحاسم: المحسوس (أمثلة مرئية) -> شبه المحسوس (مسائل لفظية) -> المجرد (رموز وعمليات). " +
        "استخدم الأرقام المشرقية ٠١٢٣٤٥٦٧٨٩ حصرياً في كافة جداولك ومخرجاتك واجعل المتن نظيفاً تماماً وجاهزاً لاستخدام الطلاب مباشرة.";

    public static void process(final Context context, final String prompt, final AIProcessor.Callback callback) {
        if (context == null || callback == null) return;

        final String fullPrompt = SYSTEM_BRAIN + "\nطلب المعلم الحالي: " + prompt;

        new Thread(new Runnable() {
            @Override
            public void run() {
                String finalResult = "";
                boolean success = false;

                // المحاولة الأولى: نظام جيميناي المستقر
                try {
                    Log.d(TAG, "جاري تجربة المحرك الأول: Gemini...");
                    finalResult = GeminiClient.sendMessage(fullPrompt);
                    success = true;
                } catch (Exception e) {
                    Log.e(TAG, "فشل المحرك الأول، الانتقال التلقائي الفوري لتأمين الاستجابة...", e);
                }

                // المحاولة الثانية التلقائية: (يمكنك تفعيل كود Groq أو حاقن بديل هنا عند الرغبة)
                if (!success) {
                    try {
                        Log.d(TAG, "جاري تشغيل المحرك الاحتياطي التلقائي...");
                        // في حال تعطل الأول، يرجع التطبيق هنا برسالة دفاعية ذكية أو يعيد المحاولة بمسار بديل لضمان الثبات
                        finalResult = GeminiClient.sendMessage(fullPrompt); // إعادة محاولة حركية دفاعية
                        success = true;
                    } catch (Exception ex) {
                        Log.e(TAG, "فشل مسار المحاولات التلقائية بالكامل", ex);
                        finalResult = "النظام الذكي يواجه قيود اتصال إقليمية حالياً. يرجى التحقق من تحديث مفتاحك عبر لوحة التحكم.";
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
