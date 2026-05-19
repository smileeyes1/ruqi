package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات الربط الذكي"));
        
        final EditText etKey = UIFactory.createInputField(context, "أدخل مفتاح API...");
        etKey.setText(DatabaseEngine.getApiKey(context));
        layout.addView(etKey);
        
        layout.addView(UIFactory.createMenuButton(context, "حفظ الإعدادات", v -> {
            DatabaseEngine.saveApiKey(context, etKey.getText().toString());
            Toast.makeText(context, "تم حفظ المفتاح بنجاح", Toast.LENGTH_SHORT).show();
        }));
        
        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
