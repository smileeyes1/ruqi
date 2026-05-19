package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.EditText;
import android.widget.Toast;

public class PlanningEngine {

    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "مولد الخطط والتحاضير"));

        // حقول الإدخال للمخطط
        final EditText etTopic = UIFactory.createInputField(context, "موضوع الدرس/الوحدة...");
        final EditText etObjectives = UIFactory.createInputField(context, "الأهداف التعليمية...");
        layout.addView(etTopic);
        layout.addView(etObjectives);

        // زر توليد وطباعة التحضير
        layout.addView(UIFactory.createMenuButton(context, "طباعة التحضير (PDF)", v -> {
            String topic = etTopic.getText().toString();
            String obj = etObjectives.getText().toString();
            
            if (topic.isEmpty()) {
                Toast.makeText(context, "يجب تحديد الموضوع أولاً", Toast.LENGTH_SHORT).show();
            } else {
                String fullPlan = "الخطة التعليمية:\n" +
                                  "الموضوع: " + topic + "\n" +
                                  "الأهداف: " + obj + "\n" +
                                  "--------------------------\n" +
                                  "١. التمهيد (المحسوس): استعراض الوسائل البصرية.\n" +
                                  "٢. العرض (شبه المحسوس): الربط بالسياق الحياتي.\n" +
                                  "٣. التجريد (الرموز): صياغة المفاهيم.\n" +
                                  "٤. التقييم: ورقة عمل ختامية.";
                                  
                PrintEngine.printText(context, "تحضير: " + topic, fullPlan);
            }
        }));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
