package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class PlanningEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "التحضير الذكي (AI)"));

        final EditText etTopic = UIFactory.createInputField(context, "موضوع الدرس...");
        layout.addView(etTopic);

        layout.addView(UIFactory.createMenuButton(context, "توليد بالذكاء الاصطناعي", v -> {
            String topic = etTopic.getText().toString();
            if (topic.isEmpty()) {
                Toast.makeText(context, "اكتب موضوعاً أولاً", Toast.LENGTH_SHORT).show();
                return;
            }
            
            Toast.makeText(context, "جاري التواصل مع العقل الاصطناعي...", Toast.LENGTH_SHORT).show();
            
            // الربط مع المحرك الذكي
            LLMEngine.generate(context, "اكتب تحضيراً لدرس " + topic + "، يتضمن: الهدف، الاستراتيجية، التقييم.", result -> {
                PrintEngine.printText(context, "نتيجة الذكاء الاصطناعي", result);
            });
        }));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
