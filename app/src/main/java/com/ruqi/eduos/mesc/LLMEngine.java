package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;

public class LLMEngine {

    public interface Callback { void onResponse(String result); }

    public static void generate(Context context, String prompt, Callback callback) {
        // محرك توليد محلي (لا يحتاج إنترنت أو خادم)
        new Thread(() -> {
            String topic = prompt.replace("اكتب تحضير درس: ", "");
            String result = buildLessonPlan(topic);
            
            new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(result));
        }).start();
    }

    private static String buildLessonPlan(String topic) {
        return "التحضير الأكاديمي لمادة: " + topic + "\n" +
               "-----------------------------------\n" +
               "١. الأهداف التعليمية:\n" +
               "   - أن يتعرف الطالب على المفهوم الأساسي لـ " + topic + ".\n" +
               "   - أن يربط الطالب المفاهيم بحياته اليومية.\n\n" +
               "٢. التمهيد (المحسوس):\n" +
               "   - عرض صورة أو مجسم يمثل " + topic + " وفتح نقاش مع الطلاب.\n\n" +
               "٣. العرض (شبه المحسوس):\n" +
               "   - حل مسألة لفظية بسيطة تتعلق بـ " + topic + ".\n" +
               "   - توظيف استراتيجية التعلم النشط.\n\n" +
               "٤. التجريد والختام:\n" +
               "   - كتابة القاعدة الأساسية أو القانون.\n" +
               "   - تقييم ختامي سريع (سؤال وتحدي).\n\n" +
               "تم التوليد محلياً بنظام: EduOS Smart-Local";
    }
}
