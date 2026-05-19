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
        root.addView(UIFactory.createHeader(this, "لوحة التحكم المركزية"));

        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> CurriculumEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "إدارة الطلاب", v -> StudentEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "الخطط والتحاضير", v -> PlanningEngine.launch(this)));
        root.addView(UIFactory.createMenuButton(this, "إعدادات الربط الذكي", v -> SettingsEngine.launch(this)));

        setContentView(root);
    }

    @Override
    public void onBackPressed() {
        renderDashboard();
    }
}
