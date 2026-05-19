package com.ruqi.eduos.mesc;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات الربط الذكي"));
        
        final EditText etUrl = UIFactory.createInputField(context, "عنوان خادم الذكاء (Ollama)...");
        etUrl.setText(DatabaseEngine.load(context, "SERVER_URL"));
        layout.addView(etUrl);
        
        // 1. زر الاكتشاف التلقائي (Address via button)
        layout.addView(UIFactory.createMenuButton(context, "اكتشاف الخادم تلقائياً", v -> {
            String defaultUrl = "http://10.0.2.2:11434/api/generate";
            showConfirmationDialog(context, defaultUrl, etUrl);
        }));

        // 2. زر الحفظ اليدوي
        layout.addView(UIFactory.createMenuButton(context, "حفظ الإعدادات", v -> {
            DatabaseEngine.save(context, "SERVER_URL", etUrl.getText().toString());
            Toast.makeText(context, "تم الحفظ", Toast.LENGTH_SHORT).show();
        }));
        
        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }

    // 3. آلية الموافقة من المستخدم (User Approval)
    private static void showConfirmationDialog(Context context, String url, EditText field) {
        new AlertDialog.Builder(context)
            .setTitle("اكتشاف خادم جديد")
            .setMessage("تم العثور على عنوان الخادم الافتراضي:\n" + url + "\nهل ترغب في اعتماده؟")
            .setPositiveButton("موافقة", (dialog, which) -> {
                field.setText(url);
                DatabaseEngine.save(context, "SERVER_URL", url);
                Toast.makeText(context, "تم اعتماد العنوان بنجاح", Toast.LENGTH_SHORT).show();
            })
            .setNegativeButton("إلغاء", null)
            .show();
    }
}
