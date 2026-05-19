package com.ruqi.eduos.mesc;

import android.content.Context;
import android.graphics.Color;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * المحرك الذكي (AIEngine)
 * المسؤول عن توليد التحاضير، حفظها، وطباعتها
 */
public class AIEngine {

    public static void launch(Context context) {
        // 1. بناء واجهة المحرك
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "المساعد الذكي (AI)"));

        // 2. المحتوى التعليمي (التحضير)
        String lessonContent = "الدرس: الجمع\n" +
                               "١. المحسوس: استخدام مكعبات العد.\n" +
                               "٢. شبه المحسوس: رسم دوائر.\n" +
                               "٣. المجرد: معادلة (٤+٢=٦).";

        TextView tvPlan = new TextView(context);
        tvPlan.setText(lessonContent);
        tvPlan.setTextColor(Color.LTGRAY);
        tvPlan.setTextSize(16);
        tvPlan.setPadding(0, 20, 0, 40);
        layout.addView(tvPlan);

        // 3. زر حفظ التحضير
        layout.addView(UIFactory.createMenuButton(context, "حفظ التحضير في الجهاز", v -> {
            DatabaseEngine.save(context, "LastLesson", lessonContent);
            Toast.makeText(context, "تم حفظ التحضير بنجاح", Toast.LENGTH_SHORT).show();
        }));

        // 4. زر استرجاع التحضير
        layout.addView(UIFactory.createMenuButton(context, "استرجاع آخر تحضير", v -> {
            String saved = DatabaseEngine.load(context, "LastLesson");
            Toast.makeText(context, saved, Toast.LENGTH_LONG).show();
        }));

        // 5. زر طباعة التحضير
        layout.addView(UIFactory.createMenuButton(context, "طباعة التحضير (PDF)", v -> {
            String saved = DatabaseEngine.load(context, "LastLesson");
            if (saved.equals("لا توجد بيانات محفوظة")) {
                Toast.makeText(context, "يجب حفظ التحضير أولاً", Toast.LENGTH_SHORT).show();
            } else {
                PrintEngine.printText(context, "تحضير درس رقي", saved);
            }
        }));

        // ربط الواجهة بالنشاط الرئيسي
        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
