package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PlanningEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "المخطط الذكي"));

        final EditText etTopic = UIFactory.createInputField(context, "موضوع الدرس...");
        layout.addView(etTopic);

        layout.addView(UIFactory.createMenuButton(context, "توليد الخطة الذكية", v -> {
            String topic = etTopic.getText().toString();
            if (topic.isEmpty()) {
                Toast.makeText(context, "يجب تحديد الموضوع", Toast.LENGTH_SHORT).show();
                return;
            }
            // استدعاء الذكاء الهجين
            HybridAIManager.generateContent(context, topic, result -> {
                PrintEngine.printText(context, "تحضير: " + topic, result);
            });
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
