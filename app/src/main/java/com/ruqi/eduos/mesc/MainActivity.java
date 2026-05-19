package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // الحاوية الرئيسية لنظام رقي
        final LinearLayout shell = new LinearLayout(this);
        shell.setOrientation(LinearLayout.VERTICAL);
        shell.setBackgroundColor(Color.parseColor("#121212")); // مظهر داكن احترافي
        shell.setLayoutDirection(View.LAYOUT_DIRECTION_RTL);
        shell.setPadding(50, 50, 50, 50);

        // الترويسة الرئيسية
        TextView title = new TextView(this);
        title.setText("نظام رقي التعليمي (RuqiOS)");
        title.setTextColor(Color.parseColor("#00E5FF"));
        title.setTextSize(24);
        title.setGravity(Gravity.CENTER);
        title.setPadding(0, 0, 0, 60);
        shell.addView(title);

        // لوحة التحكم (الصدفة)
        shell.addView(createDashboardGrid());

        setContentView(shell);
    }

    private LinearLayout createDashboardGrid() {
        LinearLayout grid = new LinearLayout(this);
        grid.setOrientation(LinearLayout.VERTICAL);

        // قائمة المحركات الرئيسية (Buttons as Modules)
        addButton(grid, "إدارة المنهاج (Curriculum)");
        addButton(grid, "لوحة المعلم (Teacher Dashboard)");
        addButton(grid, "المساعد الذكي (AI Assistant)");
        addButton(grid, "سجل الطلاب (Student Progress)");

        return grid;
    }

    private void addButton(LinearLayout parent, String text) {
        Button btn = new Button(this);
        btn.setText(text);
        btn.setBackgroundColor(Color.parseColor("#1E1E1E"));
        btn.setTextColor(Color.WHITE);
        btn.setPadding(40, 40, 40, 40);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 30);
        
        btn.setOnClickListener(v -> {
            // توجيه ذكي للمحركات (سيتم ربطها لاحقاً)
        });
        
        parent.addView(btn, params);
    }
}
