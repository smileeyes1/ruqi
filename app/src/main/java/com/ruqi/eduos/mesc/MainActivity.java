package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setPadding(20, 20, 20, 20);

        ScrollView scrollView = new ScrollView(this);
        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(chatHistory);

        EditText inputField = new EditText(this);
        inputField.setHint("اكتب ميزتك المطلوبة أو طلبك التعليمي هنا...");

        Button sendBtn = new Button(this);
        sendBtn.setText("تنفيذ وبدء التطور");

        sendBtn.setOnClickListener(v -> {
            String text = inputField.getText().toString().trim();
            if (text.isEmpty()) return;
            
            addMessage("أنت: " + text, Gravity.END);
            inputField.setText("");
            
            addMessage("جاري المعالجة والتحديث التلقائي...", Gravity.START);

            AIOrchestrator.process(this, text, result -> {
                if (chatHistory.getChildCount() > 0) {
                    chatHistory.removeViewAt(chatHistory.getChildCount() - 1);
                }
                addMessage("EduOS: " + result, Gravity.START);
            });
        });

        mainLayout.addView(scrollView, new LinearLayout.LayoutParams(-1, 0, 1.0f));
        mainLayout.addView(inputField);
        mainLayout.addView(sendBtn);

        setContentView(mainLayout);
    }

    private void addMessage(String msg, int gravity) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setTextSize(16);
        tv.setPadding(15, 10, 15, 10);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-2, -2);
        params.gravity = gravity;
        params.setMargins(0, 5, 0, 5);
        tv.setLayoutParams(params);
        
        chatHistory.addView(tv);
    }
}
