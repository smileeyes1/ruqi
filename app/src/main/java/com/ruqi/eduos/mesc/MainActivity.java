package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

/**
 * المتحكم المركزي لنظام رقي (Ruqi EduOS)
 * يعمل كـ Shell لربط المحركات المستقلة
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. بناء الحاوية الأساسية باستخدام المصنع (UIFactory)
        LinearLayout root = UIFactory.createRootLayout(this);

        // 2. إضافة الترويسة الرئيسية
        root.addView(UIFactory.createHeader(this, "لوحة تحكم رقي المركزية"));

        // 3. ربط المحركات (Modules) بالأزرار
        
        // ربط محرك المنهاج
        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> {
            CurriculumEngine.launch(this);
        }));

        // ربط محرك المعلم
        root.addView(UIFactory.createMenuButton(this, "لوحة المعلم البديل", v -> {
            TeacherEngine.launch(this);
        }));

        // 4. تعيين الواجهة
        setContentView(root);
    }

    /**
     * وظيفة إضافية: تمكنك من العودة للواجهة الرئيسية عند الضغط على زر الرجوع في الهاتف
     */
    @Override
    public void onBackPressed() {
        // إعادة تحميل الشاشة الرئيسية عند الرجوع
        setContentView(UIFactory.createRootLayout(this)); // (سيتم تطوير هذا مستقبلاً لتنقل أكثر دقة)
        // مؤقتاً: إعادة تشغيل النشاط لضمان العودة للمنصة
        recreate();
    }
}
