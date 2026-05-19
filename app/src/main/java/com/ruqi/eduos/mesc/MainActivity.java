package com.ruqi.eduos.mesc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        // إثبات النواة الكودية للتنفيذ الميداني لمسألة الجمع والطرح
        TextView mathText = findViewById(R.id.mathQuestionText);
        // الأرقام تخرج للمستودع والطلاب بالصيغة المشرقية الصافية حتمًا 
        mathText.setText("٥ + ٣ = ٨");
    }
}
