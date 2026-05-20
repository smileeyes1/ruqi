package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatHistory;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.setBackgroundColor(Color.parseColor("#0B0F19"));
        mainLayout.setPadding(24, 24, 24, 24);

        LinearLayout headerLayout = new LinearLayout(this);
        headerLayout.setOrientation(LinearLayout.HORIZONTAL);
        headerLayout.setGravity(Gravity.CENTER_VERTICAL);
        headerLayout.setPadding(0, 0, 0, 24);

        TextView titleTv = new TextView(this);
        titleTv.setText("EduOS | نظام القيادة الذاتي");
        titleTv.setTextColor(Color.parseColor("#00F0FF"));
        titleTv.setTextSize(20);
        titleTv.setTypeface(null, Typeface.BOLD);
        headerLayout.addView(titleTv);
        mainLayout.addView(headerLayout);

        scrollView = new ScrollView(this);
        LinearLayout.LayoutParams scrollParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 0, 1.0f);
        scrollParams.setMargins(0, 0, 0, 24);
        scrollView.setLayoutParams(scrollParams);
        scrollView.setFillViewport(true);

        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        chatHistory.setGravity(Gravity.BOTTOM); 
        scrollView.addView(chatHistory);
        mainLayout.addView(scrollView);

        LinearLayout inputContainer = new LinearLayout(this);
        inputContainer.setOrientation(LinearLayout.HORIZONTAL);
        inputContainer.setGravity(Gravity.CENTER_VERTICAL);
        inputContainer.setBackgroundColor(Color.parseColor("#0B0F19"));

        final EditText inputField = new EditText(this);
        inputField.setHint("اكتب أمرك التعليمي هنا للتنفيذ...");
        inputField.setHintTextColor(Color.parseColor("#718096")); 
        inputField.setTextColor(Color.WHITE); 
        inputField.setTextSize(18); 
        inputField.setBackgroundColor(Color.parseColor("#1A202C"));
        inputField.setPadding(32, 32, 32, 32);
        LinearLayout.LayoutParams inputParams = new LinearLayout.LayoutParams(0, ViewGroup.LayoutParams.WRAP_CONTENT, 1.0f);
        inputParams.setMargins(0, 0, 16, 0);
        inputField.setLayoutParams(inputParams);
        inputContainer.addView(inputField);

        Button sendBtn = new Button(this);
        sendBtn.setText("إرسال");
        sendBtn.setTextColor(Color.BLACK);
        sendBtn.setBackgroundColor(Color.parseColor("#00F0FF"));
        sendBtn.setTypeface(null, Typeface.BOLD);
        sendBtn.setPadding(40, 24, 40, 24);
        inputContainer.addView(sendBtn);
        mainLayout.addView(inputContainer);

        setContentView(mainLayout);

        addMessage("نظام المحركات الذاتية النشطة يعمل الآن في الخلفية لتأمين الاتصال الاستباقي.", Gravity.START, "#1E293B", "#00F0FF");

        sendBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String text = inputField.getText().toString().trim();
                if (text.isEmpty()) return;

                addMessage("أنت: " + text, Gravity.END, "#00F0FF", "#0B0F19");
                inputField.setText("");
                addMessage("جاري تأمين الاستجابة الذكية وتخطي العوائق التلقائي...", Gravity.START, "#1A202C", "#A0AEC0");

                AIOrchestrator.process(MainActivity.this, text, new AIProcessor.Callback() {
                    @Override
                    public void onResponse(String result) {
                        if (chatHistory.getChildCount() > 0) {
                            chatHistory.removeViewAt(chatHistory.getChildCount() - 1);
                        }
                        addMessage(result, Gravity.START, "#1E293B", "#FFFFFF");
                    }
                });
            }
        });
    }

    private void addMessage(String msg, int gravity, String bgColor, String textColor) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setTextSize(16);
        tv.setTextColor(Color.parseColor(textColor));
        tv.setBackgroundColor(Color.parseColor(bgColor));
        tv.setPadding(32, 24, 32, 24); 
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.gravity = gravity;
        params.setMargins(0, 12, 0, 12);
        tv.setLayoutParams(params);
        
        chatHistory.addView(tv);
        scrollView.post(new Runnable() {
            @Override
            public void run() {
                scrollView.fullScroll(View.FOCUS_DOWN);
            }
        });
    }
}
