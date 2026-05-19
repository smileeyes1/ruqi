package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        // تسجيل الميزات ذاتياً
        CommandRegistry.register(new LessonPlanCommand());

        // بناء الواجهة برمجياً (Zero XML)
        LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(32, 32, 32, 32);

        EditText inputField = new EditText(this);
        inputField.setHint("اكتب موضوع الدرس...");

        Button btnPrepare = new Button(this);
        btnPrepare.setText("تحضير الدرس");

        TextView resultView = new TextView(this);

        btnPrepare.setOnClickListener(v -> {
            AppCommand cmd = CommandRegistry.getCommand("تحضير");
            if (cmd != null) {
                resultView.setText("جاري التحضير...");
                cmd.execute(this, inputField.getText().toString(), resultView::setText);
            }
        });

        layout.addView(inputField);
        layout.addView(btnPrepare);
        layout.addView(resultView);

        setContentView(layout);
    }
}
