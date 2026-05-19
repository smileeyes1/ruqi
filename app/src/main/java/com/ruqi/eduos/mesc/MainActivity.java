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
    // نظام إدارة الحالة التفاعلية والعداد الرقمي
    private int currentCount = 1;
    private final String[] arabicDigits = {"٠", "١", "٢", "٣", "٤", "٥", "٦", "٧", "٨", "٩", "١٠"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // ١. الحاوية الرئيسية للشاشة - تصميم داكن احترافي
        final LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#121212"));
        mainLayout.setPadding(50, 100, 50, 50);
        mainLayout.setGravity(Gravity.TOP);

        // ٢. الترويسة العلوية الثابتة باللون السيان النيوني
        TextView headerText = new TextView(this);
        headerText.setText("Ruqi EduOS • لوحة التحكم الذكية");
        headerText.setTextColor(Color.parseColor("#00E5FF"));
        headerText.setTextSize(24);
        headerText.setTypeface(null, Typeface.BOLD);
        headerText.setGravity(Gravity.CENTER);
        
        LinearLayout.LayoutParams headerParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        headerParams.setMargins(0, 0, 0, 120);
        mainLayout.addView(headerText, headerParams);

        // ٣. بطاقة المحتوى التكيفية (تتغير محتوياتها برمجياً عند التفاعل)
        final LinearLayout cardLayout = new LinearLayout(this);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        cardLayout.setBackgroundColor(Color.parseColor("#1E1E1E"));
        cardLayout.setPadding(50, 80, 50, 80);
        cardLayout.setGravity(Gravity.CENTER);
        
        // النص الترحيبي الافتراضي
        final TextView welcomeText = new TextView(this);
        welcomeText.setText("نظام إدارة الصف التكيفي الذكي\n(Project Zaytouna)");
        welcomeText.setTextColor(Color.WHITE);
        welcomeText.setTextSize(18);
        welcomeText.setGravity(Gravity.CENTER);
        welcomeText.setLineSpacing(1.2f, 1.2f);
        
        LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        textParams.setMargins(0, 0, 0, 60);
        cardLayout.addView(welcomeText, textParams);

        // زر التفعيل الرئيسي التفاعلي
        Button actionButton = new Button(this);
        actionButton.setText("ابدأ تفعيل الحصة الاستكشافية");
        actionButton.setBackgroundColor(Color.parseColor("#00E5FF"));
        actionButton.setTextColor(Color.parseColor("#121212"));
        actionButton.setTypeface(null, Typeface.BOLD);
        actionButton.setTextSize(16);
        
        cardLayout.addView(actionButton, new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        ));

        // ٤. تفعيل وظيفة الزر: تحويل الواجهة فوراً عند النقر إلى ميزة تعليمية حية
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // تصفير محتوى البطاقة لبناء التدرج المحسوس داخلها
                cardLayout.removeAllViews();
                
                // عنوان النشاط
                TextView lessonTitle = new TextView(MainActivity.this);
                lessonTitle.setText("النشاط الاستكشافي ١: المجموعات المحسوسة");
                lessonTitle.setTextColor(Color.parseColor("#00E5FF"));
                lessonTitle.setTextSize(18);
                lessonTitle.setGravity(Gravity.CENTER);
                lessonTitle.setPadding(0, 0, 0, 40);
                cardLayout.addView(lessonTitle);

                // العرض البصري المحسوس (رسومات استكشافية للشطب والعد)
                final TextView visualContainer = new TextView(MainActivity.this);
                visualContainer.setText("🟢");
                visualContainer.setTextSize(32);
                visualContainer.setGravity(Gravity.CENTER);
                visualContainer.setPadding(0, 0, 0, 40);
                cardLayout.addView(visualContainer);

                // المؤشر الرقمي التكيفي بالأرقام العربية المستهدفة
                final TextView counterDisplay = new TextView(MainActivity.this);
                counterDisplay.setText("العدد الإجمالي الحالي: " + arabicDigits[1]);
                counterDisplay.setTextColor(Color.WHITE);
                counterDisplay.setTextSize(20);
                counterDisplay.setGravity(Gravity.CENTER);
                counterDisplay.setPadding(0, 0, 0, 60);
                cardLayout.addView(counterDisplay);

                // زر تفاعلي إضافي لزيادة العناصر المحسوسة واختبار الاستجابة اللحظية والتكيفية للمنصة
                Button addItemButton = new Button(MainActivity.this);
                addItemButton.setText("إضافة عنصر محسوس (+١)");
                addItemButton.setBackgroundColor(Color.parseColor("#121212"));
                addItemButton.setTextColor(Color.WHITE);
                
                currentCount = 1;
                addItemButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (currentCount < 10) {
                            currentCount++;
                            // تحديث العناصر المحسوسة بصرياً
                            StringBuilder items = new StringBuilder();
                            for (int i = 0; i < currentCount; i++) {
                                items.append("🟢 ");
                            }
                            visualContainer.setText(items.toString());
                            // تحديث العداد الرقمي المشرقي التكيفي
                            counterDisplay.setText("الالعدد الإجمالي الحالي: " + arabicDigits[currentCount]);
                        } else {
                            Toast.makeText(MainActivity.this, "اكتملت المجموعة التعليمية الأولى", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

                cardLayout.addView(addItemButton, new LinearLayout.LayoutParams(
                        LinearLayout.LayoutParams.MATCH_PARENT,
                        LinearLayout.LayoutParams.WRAP_CONTENT
                ));
            }
        });

        // ربط وعرض حاوية التصميم التفاعلية مباشرة
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mainLayout.addView(cardLayout, cardParams);
        setContentView(mainLayout);
    }
}
