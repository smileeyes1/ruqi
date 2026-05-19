package com.ruqi.eduos.mesc;

import android.content.Intent;
import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

/**
 * الواجهة الرئيسية للتطبيق (Dashboard)
 * مركز التحكم في جميع محركات النظام
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        renderDashboard();
    }

    /**
     * بناء واجهة لوحة التحكم المركزية برمجياً (Code-Based UI)
     */
    public void renderDashboard() {
        LinearLayout root = UIFactory.createRootLayout(this);
        root.addView(UIFactory.createHeader(this, "لوحة تحكم رقي المركزية"));

        // ربط المحركات الأساسية
        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> CurriculumEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "لوحة المعلم البديل", v -> TeacherEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "المساعد الذكي (AI)", v -> AIEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "إدارة الخطط والتحاضير", v -> PlanningEngine.launch(this)));

        setContentView(root);
    }

    /**
     * معالجة نتائج استيراد الملفات
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == ImportEngine.REQUEST_CODE_PDF && resultCode == RESULT_OK) {
            if (data != null && data.getData() != null) {
                // حفظ المسار في قاعدة البيانات المحلية للرجوع إليه لاحقاً
                String uriString = data.getData().toString();
                DatabaseEngine.save(this, "CurriculumURI", uriString);
                
                Toast.makeText(this, "تم حفظ الملف بنجاح في ذاكرة التطبيق", Toast.LENGTH_LONG).show();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // ضمان العودة دائماً للوحة التحكم الرئيسية بدلاً من إغلاق التطبيق
        renderDashboard();
    }
}
