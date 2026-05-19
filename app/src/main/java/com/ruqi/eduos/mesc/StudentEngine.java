package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * محرك بيانات الطلاب (Student Management)
 */
public class StudentEngine {

    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "سجل الطلاب"));

        // محاكاة بيانات (سيتم تطويرها لاحقاً لتدعم الإدخال اليدوي)
        String studentList = "١. أحمد محمود - ٩٥\n" +
                             "٢. سارة محمد - ٨٨\n" +
                             "٣. خالد يوسف - ٩٢";

        TextView tv = new TextView(context);
        tv.setText(studentList);
        tv.setTextColor(android.graphics.Color.WHITE);
        tv.setTextSize(18);
        tv.setPadding(0, 40, 0, 40);
        layout.addView(tv);

        layout.addView(UIFactory.createMenuButton(context, "تحديث السجلات", v -> 
            Toast.makeText(context, "تم تحديث بيانات الصف بنجاح", Toast.LENGTH_SHORT).show()));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
