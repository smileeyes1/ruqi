package com.ruqi.eduos.mesc;

import android.content.Context;

public class HybridAIManager {
    public interface PlanCallback { void onResult(String result); }

    public static void generateContent(Context context, String topic, PlanCallback callback) {
        String apiKey = DatabaseEngine.getApiKey(context);
        
        // ذكاء هجين: إذا وجد مفتاح (API) سيتم الربط، إذا لم يوجد يستخدم المحلي
        if (apiKey != null && !apiKey.isEmpty()) {
            // هنا سيتم الربط المستقبلي مع API السحابي
            callback.onResult("مفتاح API مفعل. سيتم توليد المحتوى الذكي قريباً. المحتوى الحالي: " + LocalSmartEngine.generate(topic));
        } else {
            callback.onResult(LocalSmartEngine.generate(topic));
        }
    }
}
