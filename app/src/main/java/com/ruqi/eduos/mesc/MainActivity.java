package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // 1. الحاوية الرئيسية للشاشة - تصميم داكن بالكامل
        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#121212"));
        mainLayout.setPadding(50, 100, 50, 50);
        mainLayout.setGravity(Gravity.TOP);

        // 2. الترويسة العلوية الذكية باللون السيان النيوني (Neon Cyan)
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

        // 3. بطاقة المحتوى المركزية المتطورة (SaaS Dashboard Card)
        LinearLayout cardLayout = new LinearLayout(this);
        cardLayout.setOrientation(LinearLayout.VERTICAL);
        cardLayout.setBackgroundColor(Color.parseColor("#1E1E1E"));
        cardLayout.setPadding(50, 80, 50, 80);
        cardLayout.setGravity(Gravity.CENTER);
        
        // النص التعريفي للمشروع داخل البطاقة
        TextView welcomeText = new TextView(this);
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

        // زر التفعيل الرئيسي التفاعلي باللون السيان النيوني العريض
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

        // إضافة البطاقة للحاوية الرئيسية
        LinearLayout.LayoutParams cardParams = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
        );
        mainLayout.addView(cardLayout, cardParams);

        // تفعيل وعرض الواجهة البرمجية الصلبة مباشرة
        setContentView(mainLayout);
    }
}
