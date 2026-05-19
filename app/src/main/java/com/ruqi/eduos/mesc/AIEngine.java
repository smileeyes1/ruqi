package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AIEngine {
    
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "المساعد الذكي (تحضير الدرس)"));

        // منطق تعليمي: التدرج من المحسوس إلى المجرد
        String lessonPlan = "عنوان الدرس: جمع الأعداد.\n\n" +
                            "١. المحسوس: استخدام مكعبات العد.\n" +
                            "٢. شبه المحسوس: رسم دوائر على السبورة.\n" +
                            "٣. المجرد: كتابة المعادلة الرقمية (٤+٢=٦).";

        TextView tvPlan = new TextView(context);
        tvPlan.setText(lessonPlan);
        tvPlan.setTextColor(android.graphics.Color.LTGRAY);
        tvPlan.setPadding(0, 20, 0, 40);
        layout.addView(tvPlan);

        layout.addView(UIFactory.createMenuButton(context, "توليد نشاط تفاعلي", v -> 
            Toast.makeText(context, "تم اقتراح نشاط: 'لعبة الأرقام الملونة'", Toast.LENGTH_SHORT).show()));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
