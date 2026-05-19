package com.ruqi.eduos.mesc;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        TextView mathText = findViewById(R.id.mathQuestionText);
        mathText.setText("٥ + ٣ = ٨");
    }
}
