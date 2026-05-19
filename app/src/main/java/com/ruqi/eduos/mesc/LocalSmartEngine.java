package com.ruqi.eduos.mesc;

public class LocalSmartEngine {
    public static String generate(String topic) {
        return "<h2>خطة تعليمية (الذكاء المحلي):</h2>" +
               "<p>الموضوع: " + topic + "</p>" +
               "<ul><li><b>الهدف:</b> فهم المبادئ الأساسية لـ " + topic + ".</li>" +
               "<li><b>الاستراتيجية:</b> التعلم النشط والربط بالسياق المحلي.</li>" +
               "<li><b>التقييم:</b> تطبيق عملي ومسألة لفظية.</li></ul>";
    }
}
