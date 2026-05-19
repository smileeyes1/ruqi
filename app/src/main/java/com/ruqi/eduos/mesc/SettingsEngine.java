package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات الربط الذكي"));

        final EditText etKey = UIFactory.createInputField(context, "مفتاح Gemini API");
        etKey.setText(DatabaseEngine.load(context, "API_KEY"));
        layout.addView(etKey);

        final EditText etUrl = UIFactory.createInputField(context, "رابط الخادم (Ollama)");
        etUrl.setText(DatabaseEngine.load(context, "SERVER_URL"));
        layout.addView(etUrl);

        layout.addView(UIFactory.createMenuButton(context, "حفظ الإعدادات", v -> {
            DatabaseEngine.save(context, "API_KEY", etKey.getText().toString());
            DatabaseEngine.save(context, "SERVER_URL", etUrl.getText().toString());
            Toast.makeText(context, "تم حفظ الإعدادات", Toast.LENGTH_SHORT).show();
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
