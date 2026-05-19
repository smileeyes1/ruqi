package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    
    // مصفوفة الدروس الرقمية بنظام الأرقام العربية المعتمدة ٠١٢٣٤٥٦٧٨٩
    private final String[] lessonTitles = {
            "الدرس ١: عد العناصر من ١ إلى ٥",
            "الدرس ٢: مفهوم الجمع اللفظي والرمزي",
            "الدرس ٣: لوحة الأعداد التكيفية للمتفوقين"
    };
    
    private final String[] arabicDigits = {"٠", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩", "١٠"};
    private int currentLessonIndex = 0;
    private int elementCount = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // البرمجة الدفاعية: التحقق من سلامة السياق قبل البناء
        if (this == null) return;

        try {
            // ١. الحاوية الرئيسية للشاشة (اتجاه RTL إجباري للمنهاج)
            final LinearLayout mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setBackgroundColor(Color.parseColor("#121212"));
            mainLayout.setPadding(40, 80, 40, 40);
            mainLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            // ٢. ترويسة النظام المؤسسية (Neon Cyan)
            TextView systemHeader = new TextView(this);
            systemHeader.setText("منصة رقي الرقمية • Ruqi EduOS");
            systemHeader.setTextColor(Color.parseColor("#00E5FF"));
            systemHeader.setTextSize(22);
            systemHeader.setTypeface(null, Typeface.BOLD);
            systemHeader.setGravity(Gravity.CENTER);
            
            LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            headerParams.setMargins(0, 0, 0, 80);
            mainLayout.addView(systemHeader, headerParams);

            // ٣. بطاقة عرض الدرس الحالية (SaaS Dashboard Card)
            final LinearLayout lessonCard = new LinearLayout(this);
            lessonCard.setOrientation(LinearLayout.VERTICAL);
            lessonCard.setBackgroundColor(Color.parseColor("#1E1E1E"));
            lessonCard.setPadding(40, 60, 40, 60);
            lessonCard.setGravity(Gravity.CENTER);

            // مكونات داخل البطاقة: عنوان الدرس الحالي
            final TextView currentLessonText = new TextView(this);
            currentLessonText.setText(lessonTitles[currentLessonIndex]);
            currentLessonText.setTextColor(Color.WHITE);
            currentLessonText.setTextSize(18);
            currentLessonText.setGravity(Gravity.CENTER);
            lessonCard.addView(currentLessonText);

            // المكون البصري المحسوس (رسومات الشطب والعد التفاعلية)
            final TextView visualContainer = new TextView(this);
            visualContainer.setText("🟢");
            visualContainer.setTextSize(28);
            visualContainer.setGravity(Gravity.CENTER);
            visualContainer.setPadding(0, 40, 0, 40);
            lessonCard.addView(visualContainer);

            // العداد شبه المحسوس والمجرد بالأرقام العربية المستهدفة
            final TextView counterDisplay = new TextView(this);
            counterDisplay.setText("مجموع العناصر المحسوسة: " + arabicDigits[elementCount]);
            counterDisplay.setTextColor(Color.parseColor("#B0BEC5"));
            counterDisplay.setTextSize(16);
            counterDisplay.setGravity(Gravity.CENTER);
            counterDisplay.setPadding(0, 0, 0, 40);
            lessonCard.addView(counterDisplay);

            // ٤. حاوية أزرار التحكم والعمليات (موزعة خارج خيط الواجهة الرئيسي عند الضغط)
            LinearLayout actionContainer = new LinearLayout(this);
            actionContainer.setOrientation(LinearLayout.HORIZONTAL);
            actionContainer.setWeightSum(2.0f);

            // زر التفاعل والمحاكاة (+١)
            Button btnAdd = new Button(this);
            btnAdd.setText("إضافة عنصر محسوس");
            btnAdd.setBackgroundColor(Color.parseColor("#00E5FF"));
            btnAdd.setTextColor(Color.parseColor("#121212"));
            btnAdd.setTypeface(null, Typeface.BOLD);
            
            LinearLayout.LayoutParams btnAddParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            btnAddParams.setMargins(10, 0, 10, 0);
            actionContainer.addView(btnAdd, btnAddParams);

            // زر الانتقال للدرس التالي لتغيير مستوى الصعوبة تكيّفياً
            Button btnNextLesson = new Button(this);
            btnNextLesson.setText("الدرس التالي ⬅");
            btnNextLesson.setBackgroundColor(Color.parseColor("#263238"));
            btnNextLesson.setTextColor(Color.WHITE);
            
            LinearLayout.LayoutParams btnNextParams = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1.0f);
            btnNextParams.setMargins(10, 0, 10, 0);
            actionContainer.addView(btnNextLesson, btnNextParams);

            lessonCard.addView(actionContainer);

            // ٥. إدارة الأحداث والتفاعل الديناميكي (Event Handling)
            btnAdd.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (elementCount < 10) {
                        elementCount++;
                        StringBuilder progressVisual = new StringBuilder();
                        for (int i = 0; i < elementCount; i++) {
                            progressVisual.append("🟢 ");
                        }
                        visualContainer.setText(progressVisual.toString());
                        counterDisplay.setText("مجموع العناصر المحسوسة: " + arabicDigits[elementCount]);
                    } else {
                        Toast.makeText(MainActivity.this, "وصلت للحد الأقصى للمجموعة الحالية", Toast.LENGTH_SHORT).show();
                    }
                }
            });

            btnNextLesson.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    currentLessonIndex = (currentLessonIndex + 1) % lessonTitles.length;
                    currentLessonText.setText(lessonTitles[currentLessonIndex]);
                    
                    // إعادة ضبط العداد للمستوى الجديد
                    elementCount = 1;
                    visualContainer.setText("🟢");
                    counterDisplay.setText("مجموع العناصر المحسوسة: " + arabicDigits[elementCount]);
                    
                    Toast.makeText(MainActivity.this, "تم الانتقال إلى المستوى التكيفي التالي", Toast.LENGTH_SHORT).show();
                }
            });

            // دمج العناصر وعرض الواجهة الصلبة
            mainLayout.addView(lessonCard);
            setContentView(mainLayout);

        } catch (Exception e) {
            // معالجة دفاعية ذكية لمنع انهيار التطبيق تحت أي ظرف
            TextView errorView = new TextView(this);
            errorView.setText("حدث خطأ أثناء تحميل الواجهة المؤسسية.");
            errorView.setTextColor(Color.RED);
            setContentView(errorView);
        }
    }
}
