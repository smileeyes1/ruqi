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

        // خيارات عرض المنهاج
        layout.addView(UIFactory.createMenuButton(context, "عرض المنهاج المحمل", v -> 
            Toast.makeText(context, "لا يوجد منهاج محمل حالياً", Toast.LENGTH_SHORT).show()));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
