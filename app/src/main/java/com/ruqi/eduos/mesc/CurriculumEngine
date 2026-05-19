package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.Toast;

public class CurriculumEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إدارة المنهاج"));
        
        layout.addView(UIFactory.createMenuButton(context, "الرياضيات - الوحدة الأولى", v -> 
            Toast.makeText(context, "جاري فتح الوحدة...", Toast.LENGTH_SHORT).show()));

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
