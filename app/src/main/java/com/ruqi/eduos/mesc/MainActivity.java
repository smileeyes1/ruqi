package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * المتحكم المركزي لنظام رقي (Ruqi EduOS)
 * يقوم بربط المحركات المستقلة (Engines) بالواجهات (UIFactory)
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // بناء لوحة التحكم الرئيسية
        renderDashboard();
    }

    /**
     * دالة بناء واجهة لوحة التحكم
     * يتم استدعاؤها عند التشغيل أو عند العودة من المحركات
     */
    private void renderDashboard() {
        // 1. بناء الحاوية الأساسية
        LinearLayout root = UIFactory.createRootLayout(this);
        
        // 2. إضافة الترويسة
        root.addView(UIFactory.createHeader(this, "لوحة تحكم رقي المركزية"));
        
        // 3. إضافة أزرار الربط مع المحركات
        
        // زر محرك المنهاج
        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> {
            CurriculumEngine.launch(this);
        }));

        // زر محرك المعلم
        root.addView(UIFactory.createMenuButton(this, "لوحة المعلم البديل", v -> {
            TeacherEngine.launch(this);
        }));

        // زر محرك الذكاء الاصطناعي
        root.addView(UIFactory.createMenuButton(this, "المساعد الذكي (AI)", v -> {
            AIEngine.launch(this);
        }));

        // تعيين الواجهة
        setContentView(root);
    }

    /**
     * الحماية والتحكم عند الضغط على زر الرجوع
     */
    @Override
    public void onBackPressed() {
        // إذا كان المستخدم داخل أحد المحركات، نعيده للوحة التحكم الرئيسية
        // استخدام recreate() هو الحل الأكثر استقراراً في هذه المرحلة
        renderDashboard();
    }
}
