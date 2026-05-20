package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
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
        mainLayout.setPadding(٢٠, ٢٠, ٢٠, ٢٠);

        ScrollView scrollView = new ScrollView(this);
        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(chatHistory);

        EditText inputField = new EditText(this);
        inputField.setHint("أدخل الميزة المطلوبة أو الطلب التعليمي هنا...");

        Button sendBtn = new Button(this);
        sendBtn.setText("تنفيذ وبدء التطور الذاتي");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String text = inputField.getText().toString().trim();
                if (text.isEmpty()) return;
                
                addMessage("أنت: " + text, Gravity.END);
                inputField.setText("");
                
                addMessage("جاري المعالجة الذاتية وتحديث الخصائص...", Gravity.START);

                AIOrchestrator.process(MainActivity.this, text, new AIProcessor.Callback() {
                    @Override
                    public void onResponse(String result) {
                        if (chatHistory.getChildCount() > ٠) {
                            chatHistory.removeViewAt(chatHistory.getChildCount() - ١);
                        }
                        addMessage("EduOS: " + result, Gravity.START);
                    }
                });
            }
        });

        mainLayout.addView(scrollView, new LinearLayout.LayoutParams(-١, ٠, ١.٠f));
        mainLayout.addView(inputField);
        mainLayout.addView(sendBtn);

        setContentView(mainLayout);
    }

    private void addMessage(String msg, int gravity) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setTextSize(١٦);
        tv.setPadding(١٥, ١٠, ١٥, ١٠);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(-٢, -٢);
        params.gravity = gravity;
        params.setMargins(٠, ٥, ٠, ٥);
        tv.setLayoutParams(params);
        
        chatHistory.addView(tv);
    }
}
