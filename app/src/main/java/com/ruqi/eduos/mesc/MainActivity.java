package com.ruqi.eduos.mesc;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatHistory;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        CommandRegistry.register(new LessonPlanCommand());

        ScrollView scrollView = new ScrollView(this);
        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        
        EditText inputField = new EditText(this);
        inputField.setHint("اكتب طلبك أو قل لي ماذا تريد...");
        
        Button sendBtn = new Button(this);
        sendBtn.setText("تنفيذ");

        sendBtn.setOnClickListener(v -> {
            String text = inputField.getText().toString();
            addMessage("أنت: " + text);
            SmartDispatcher.dispatch(this, text, this::addMessage);
            inputField.setText("");
        });

        LinearLayout mainLayout = new LinearLayout(this);
        mainLayout.setOrientation(LinearLayout.VERTICAL);
        mainLayout.addView(scrollView);
        scrollView.addView(chatHistory);
        mainLayout.addView(inputField);
        mainLayout.addView(sendBtn);

        setContentView(mainLayout);
    }

    private void addMessage(String msg) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setPadding(16, 16, 16, 16);
        chatHistory.addView(tv);
    }
}
