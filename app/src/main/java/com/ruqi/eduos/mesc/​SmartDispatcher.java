package com.ruqi.eduos.mesc;

import android.content.Context;

public class SmartDispatcher {
    public static void dispatch(Context context, String userInput, AIProcessor.Callback callback) {
        // نطلب من الذكاء الاصطناعي تحديد الأمر المناسب
        String prompt = "صنف هذا الطلب إلى أحد الأوامر التالية: [تحضير]. " +
                        "إذا كان الطلب لا يطابق أي أمر، أجب بالمحتوى التعليمي مباشرة. " +
                        "الطلب: " + userInput;

        AIOrchestrator.process(context, prompt, response -> {
            // منطق التوجيه الذاتي
            if (response.contains("تحضير")) {
                AppCommand cmd = CommandRegistry.getCommand("تحضير");
                cmd.execute(context, userInput, callback);
            } else {
                callback.onResponse(response);
            }
        });
    }
}
