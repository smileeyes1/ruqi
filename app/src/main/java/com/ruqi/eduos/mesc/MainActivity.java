package com.ruqi.eduos.mesc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // استدعاء تصميم لوحة التحكم مباشرة عند تشغيل التطبيق
        setContentView(R.layout.activity_main);
    }
}
