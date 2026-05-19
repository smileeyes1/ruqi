package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات الربط الذكي"));
        
        final EditText etUrl = UIFactory.createInputField(context, "عنوان خادم Ollama (مثال: http://10.0.2.2:11434/api/generate)");
        
        // جلب العنوان المحفوظ، إذا كان فارغاً نضع الافتراضي للمحاكي
        String savedUrl = DatabaseEngine.load(context, "SERVER_URL");
        if (savedUrl.equals("لا توجد بيانات محفوظة")) {
            savedUrl = "http://10.0.2.2:11434/api/generate";
        }
        etUrl.setText(savedUrl);
        
        layout.addView(etUrl);
        layout.addView(UIFactory.createMenuButton(context, "حفظ العنوان", v -> {
            DatabaseEngine.save(context, "SERVER_URL", etUrl.getText().toString());
            Toast.makeText(context, "تم حفظ العنوان بنجاح", Toast.LENGTH_SHORT).show();
        }));
        
        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
