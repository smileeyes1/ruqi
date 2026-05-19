package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات النظام الذكي"));

        // إنشاء مفتاح التبديل (المُشغل/المعطل)
        final Switch aiSwitch = new Switch(context);
        aiSwitch.setText("تفعيل الذكاء الاصطناعي (Hybrid Mode)");
        
        // قراءة الحالة الحالية من القاعدة (الافتراضي مفعل إذا لم يتم ضبطه)
        boolean isEnabled = !"false".equals(DatabaseEngine.load(context, "AI_ENABLED"));
        aiSwitch.setChecked(isEnabled);
        
        layout.addView(aiSwitch);

        // زر الحفظ لتثبيت الحالة في DatabaseEngine
        layout.addView(UIFactory.createMenuButton(context, "حفظ الإعدادات", v -> {
            DatabaseEngine.save(context, "AI_ENABLED", String.valueOf(aiSwitch.isChecked()));
            Toast.makeText(context, "تم تطبيق التغييرات على المنسق", Toast.LENGTH_SHORT).show();
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
