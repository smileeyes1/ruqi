package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Toast;

public class PlanningEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "التحضير الذكي"));

        final EditText etTopic = UIFactory.createInputField(context, "موضوع الدرس...");
        layout.addView(etTopic);

        // مؤشر تحميل مخفي
        final ProgressBar loader = new ProgressBar(context);
        loader.setVisibility(android.view.View.GONE);
        layout.addView(loader);

        layout.addView(UIFactory.createMenuButton(context, "توليد التحضير", v -> {
            String topic = etTopic.getText().toString();
            if (topic.isEmpty()) return;

            loader.setVisibility(android.view.View.VISIBLE); // إظهار المؤشر
            
            LLMEngine.generate(context, "اكتب تحضير درس: " + topic, result -> {
                loader.setVisibility(android.view.View.GONE); // إخفاء المؤشر
                
                // حفظ النتيجة في قاعدة البيانات للرجوع إليها
                DatabaseEngine.save(context, "LAST_PLAN", result);
                
                PrintEngine.printText(context, "التحضير: " + topic, result);
            });
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
