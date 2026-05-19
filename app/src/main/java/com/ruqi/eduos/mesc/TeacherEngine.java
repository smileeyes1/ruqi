package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

/**
 * محرك المعلم البديل (TeacherEngine)
 * المسؤول عن المهام الصفية وإدارة الطلاب
 */
public class TeacherEngine {

    public static void launch(Context context) {
        // 1. بناء واجهة لوحة المعلم
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "لوحة المعلم البديل"));

        // 2. زر إصدار أمر هدوء (إجراء صفي)
        layout.addView(UIFactory.createMenuButton(context, "إصدار أمر هدوء صفي", v -> 
            Toast.makeText(context, "📢 تم إرسال أمر الهدوء", Toast.LENGTH_SHORT).show()));

        // 3. زر تسجيل الحضور (إجراء إداري)
        layout.addView(UIFactory.createMenuButton(context, "تسجيل الحضور اليومي", v -> 
            Toast.makeText(context, "✅ تم تسجيل الحضور", Toast.LENGTH_SHORT).show()));

        // 4. زر الدخول لسجل الطلاب (ربط مع StudentEngine)
        layout.addView(UIFactory.createMenuButton(context, "إدارة سجل الطلاب", v -> 
            StudentEngine.launch(context)));

        // 5. ربط الواجهة بالنشاط الرئيسي (لتغيير الشاشة)
        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
