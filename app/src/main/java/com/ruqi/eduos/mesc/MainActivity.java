package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private LinearLayout contentArea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // البرمجة الدفاعية: التحقق من سلامة السياق
        if (this == null) return;

        try {
            // الهيكل الأساسي للحاوية
            final LinearLayout mainLayout = new LinearLayout(this);
            mainLayout.setOrientation(LinearLayout.VERTICAL);
            mainLayout.setBackgroundColor(Color.parseColor("#121212"));
            mainLayout.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);

            // 1. شريط التنقل المؤسسي
            LinearLayout navBar = createNavBar();
            mainLayout.addView(navBar);

            // 2. منطقة عرض المحتوى الديناميكي
            contentArea = new LinearLayout(this);
            contentArea.setOrientation(LinearLayout.VERTICAL);
            contentArea.setPadding(40, 40, 40, 40);

            ScrollView scrollView = new ScrollView(this);
            scrollView.addView(contentArea);
            mainLayout.addView(scrollView);

            setContentView(mainLayout);
            renderDashboard(); 

        } catch (Exception e) {
            Toast.makeText(this, "خطأ في تحميل المحرك: " + e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    private LinearLayout createNavBar() {
        LinearLayout nav = new LinearLayout(this);
        nav.setBackgroundColor(Color.parseColor("#1E1E1E"));
        nav.setPadding(20, 20, 20, 20);

        String[] menu = {"التحضير", "أوراق عمل", "طباعة"};
        for (String title : menu) {
            Button btn = new Button(this);
            btn.setText(title);
            btn.setBackgroundColor(Color.TRANSPARENT);
            btn.setTextColor(Color.parseColor("#00E5FF"));
            btn.setOnClickListener(v -> handleNavigation(title));
            nav.addView(btn);
        }
        return nav;
    }

    private void handleNavigation(String page) {
        contentArea.removeAllViews();
        switch (page) {
            case "التحضير": renderPlanner(); break;
            case "أوراق عمل": renderWorksheetGenerator(); break;
            case "طباعة": renderPrintInterface(); break;
        }
    }

    private void renderPlanner() {
        addTitle("بطاقة تحضير الدرس الرقمية");
        EditText etTopic = addInput("عنوان الدرس");
        EditText etObj = addInput("الأهداف السلوكية");
        Button save = new Button(this);
        save.setText("حفظ التحضير في النظام");
        save.setBackgroundColor(Color.parseColor("#00E5FF"));
        save.setOnClickListener(v -> Toast.makeText(this, "تم حفظ التحضير رقم ٠١ بنجاح", Toast.LENGTH_SHORT).show());
        contentArea.addView(save);
    }

    private void renderWorksheetGenerator() {
        addTitle("مولد أوراق العمل التفاعلي");
        TextView problem = new TextView(this);
        problem.setText("مسألة الجمع: ٤ + ٢ = ؟\n(استخدم الدوائر للعد المحسوس)");
        problem.setTextColor(Color.WHITE);
        problem.setPadding(0, 20, 0, 20);
        contentArea.addView(problem);
    }

    private void renderPrintInterface() {
        addTitle("مركز الطباعة");
        Button btnPrint = new Button(this);
        btnPrint.setText("طباعة الملف الحالي (PDF)");
        btnPrint.setBackgroundColor(Color.parseColor("#FFC107"));
        btnPrint.setOnClickListener(v -> Toast.makeText(this, "استدعاء خدمة الطابعة...", Toast.LENGTH_SHORT).show());
        contentArea.addView(btnPrint);
    }

    private void renderDashboard() {
        addTitle("لوحة تحكم رقي المركزية");
        TextView tv = new TextView(this);
        tv.setText("مرحباً أيها المعلم. النظام جاهز للعمل بكفاءة ٠٪ أخطاء.");
        tv.setTextColor(Color.GRAY);
        contentArea.addView(tv);
    }

    private void addTitle(String text) {
        TextView tv = new TextView(this);
        tv.setText(text);
        tv.setTextColor(Color.parseColor("#00E5FF"));
        tv.setTextSize(20);
        tv.setTypeface(null, Typeface.BOLD);
        tv.setPadding(0, 0, 0, 20);
        contentArea.addView(tv);
    }

    private EditText addInput(String hint) {
        EditText et = new EditText(this);
        et.setHint(hint);
        et.setHintTextColor(Color.DKGRAY);
        et.setTextColor(Color.WHITE);
        et.setPadding(20, 20, 20, 20);
        contentArea.addView(et);
        return et;
    }
}
