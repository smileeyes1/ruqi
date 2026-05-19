package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

public class TeacherEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "لوحة المعلم البديل"));
        
        // زر إجراء صفي
        layout.addView(UIFactory.createMenuButton(context, "إصدار أمر هدوء صفي", v -> 
            Toast.makeText(context, "📢 تم إرسال أمر الهدوء", Toast.LENGTH_SHORT).show()));
            
        // زر تسجيل حضور
        layout.addView(UIFactory.createMenuButton(context, "تسجيل الحضور اليومي", v -> 
            Toast.makeText(context, "✅ تم تسجيل الحضور", Toast.LENGTH_SHORT).show()));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
