package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // بناء الواجهة (Zero XML)
        ScrollView scrollView = new ScrollView(this);
        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(chatHistory);

        EditText inputField = new EditText(this);
        inputField.setHint("اكتب طلبك هنا...");

        Button sendBtn = new Button(this);
        sendBtn.setText("تنفيذ");

        sendBtn.setOnClickListener(v -> {
            String text = inputField.getText().toString();
            if (text.isEmpty()) return;
            
            addMessage("أنت: " + text);
            inputField.setText("");

            // التواصل المباشر مع الدماغ التعليمي
            AIOrchestrator.process(this, text, this::addMessage);
        });

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        mainLayout.addView(inputField);
        mainLayout.addView(sendBtn);

        setContentView(mainLayout);
    }

    private void addMessage(String msg) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setPadding(20, 20, 20, 20);
        chatHistory.addView(tv);
    }
}
