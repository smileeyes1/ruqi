package com.ruqi.eduos.mesc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderDashboard();
    }

    public void renderDashboard() {
        LinearLayout root = UIFactory.createRootLayout(this);
        root.addView(UIFactory.createHeader(this, "لوحة تحكم رقي المركزية"));

        // ربط المحركات
        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> CurriculumEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "لوحة المعلم البديل", v -> TeacherEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "المساعد الذكي (AI)", v -> AIEngine.launch(this)));

        setContentView(root);
    }

    // استقبال نتيجة اختيار الملف
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImportEngine.REQUEST_CODE_PDF && resultCode == RESULT_OK) {
            if (data != null) {
                Toast.makeText(this, "تم استلام الملف بنجاح", Toast.LENGTH_SHORT).show();
                // هنا يمكن إضافة كود لمعالجة الملف مستقبلاً
            }
        }
    }

    @Override
    public void onBackPressed() {
        renderDashboard();
    }
}
