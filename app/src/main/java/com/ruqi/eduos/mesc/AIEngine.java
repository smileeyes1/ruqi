package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AIEngine {

    public static void launch(Context context) {
        // بناء الواجهة
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "المساعد الذكي (AI)"));

        // مثال لتحضير
        String lessonContent = "الدرس: الجمع\nالهدف: التدرج من المحسوس للمجرد.";

        // عرض نص التحضير
        TextView tvPlan = new TextView(context);
        tvPlan.setText(lessonContent);
        tvPlan.setTextColor(android.graphics.Color.LTGRAY);
        tvPlan.setPadding(0, 20, 0, 40);
        layout.addView(tvPlan);

        // زر حفظ التحضير
        layout.addView(UIFactory.createMenuButton(context, "حفظ التحضير في الجهاز", v -> {
            DatabaseEngine.save(context, "LastLesson", lessonContent);
            Toast.makeText(context, "تم حفظ التحضير بنجاح", Toast.LENGTH_SHORT).show();
        }));

        // زر استرجاع التحضير
        layout.addView(UIFactory.createMenuButton(context, "استرجاع آخر تحضير", v -> {
            String saved = DatabaseEngine.load(context, "LastLesson");
            Toast.makeText(context, saved, Toast.LENGTH_LONG).show();
        }));

        // الربط بالنشاط الرئيسي
        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
