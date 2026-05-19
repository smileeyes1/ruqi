package com.ruqi.eduos.mesc;

import android.app.AlertDialog;
import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

public class SettingsEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "إعدادات الربط التلقائي"));
        
        final EditText etUrl = UIFactory.createInputField(context, "عنوان الخادم...");
        etUrl.setText(DatabaseEngine.load(context, "SERVER_URL"));
        layout.addView(etUrl);
        
        layout.addView(UIFactory.createMenuButton(context, "اكتشاف تلقائي (البحث في الشبكة)", v -> {
            Toast.makeText(context, "جاري البحث في الشبكة... انتظر قليلاً", Toast.LENGTH_LONG).show();
            DiscoveryEngine.scan(context, new DiscoveryEngine.Callback() {
                @Override
                public void onFound(String ip) {
                    etUrl.setText(ip);
                    DatabaseEngine.save(context, "SERVER_URL", ip);
                    Toast.makeText(context, "تم العثور عليه! " + ip, Toast.LENGTH_LONG).show();
                }
                @Override
                public void onFinished() {
                    Toast.makeText(context, "لم يتم العثور على خادم. تأكد من اتصال الجهازين بنفس الواي فاي.", Toast.LENGTH_LONG).show();
                }
            });
        }));

        layout.addView(UIFactory.createMenuButton(context, "حفظ الإعدادات", v -> {
            DatabaseEngine.save(context, "SERVER_URL", etUrl.getText().toString());
            Toast.makeText(context, "تم الحفظ", Toast.LENGTH_SHORT).show();
        }));
        
        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
