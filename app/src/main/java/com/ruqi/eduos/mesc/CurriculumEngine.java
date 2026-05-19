package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CurriculumEngine {

    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إدارة المنهاج"));

        // زر استيراد ملف PDF
        layout.addView(UIFactory.createMenuButton(context, "استيراد ملف منهاج (PDF)", v -> {
            if (context instanceof MainActivity) {
                ImportEngine.launchFilePicker((MainActivity) context);
            }
        }));

        // زر عرض المنهاج المحمل (يقرأ من قاعدة البيانات)
        layout.addView(UIFactory.createMenuButton(context, "عرض المنهاج المحمل", v -> {
            String savedUri = DatabaseEngine.load(context, "CurriculumURI");
            
            if (savedUri.equals("لا توجد بيانات محفوظة")) {
                Toast.makeText(context, "لا يوجد منهاج محمل حالياً", Toast.LENGTH_SHORT).show();
            } else {
                // هنا سيتم ربط ViewerEngine لاحقاً
                Toast.makeText(context, "تم العثور على الملف، جارٍ التحضير للعرض...", Toast.LENGTH_LONG).show();
            }
        }));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
