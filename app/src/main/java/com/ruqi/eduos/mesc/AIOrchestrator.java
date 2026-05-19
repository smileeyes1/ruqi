package com.ruqi.eduos.mesc;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class AIOrchestrator {
    private static final List<AIStrategy> strategies = new ArrayList<>();

    static {
        // ترتيب الأولويات: السحابة أولاً، ثم المحلي، ثم القالب الآمن
        strategies.add(new CloudStrategy());
        strategies.add(new LocalStrategy());
        strategies.add(new FallbackStrategy());
    }

    public static void process(Context context, String topic, AIProcessor.Callback callback) {
        String fullPrompt = "تعليمات: تدرج من المحسوس للمجرد. استخدم الأرقام ٠١٢٣٤٥٦٧٨٩. موضوع الدرس: " + topic;

        for (AIStrategy strategy : strategies) {
            if (strategy.isAvailable(context)) {
                strategy.execute(context, fullPrompt, callback);
                return;
            }
        }
    }
}
