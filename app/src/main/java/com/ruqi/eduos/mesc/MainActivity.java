package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        LinearLayout root = UIFactory.createRootLayout(this);
        root.addView(UIFactory.createHeader(this, "لوحة تحكم رقي المركزية"));
        
        root.addView(UIFactory.createMenuButton(this, "إدارة المنهاج", v -> {
            CurriculumEngine.launch(this);
        }));

        setContentView(root);
    }
}
