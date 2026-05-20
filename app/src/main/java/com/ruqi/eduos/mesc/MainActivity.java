package com.ruqi.eduos.mesc;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    private LinearLayout chatHistory;
    private ScrollView scrollView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LinearLayout main = new LinearLayout(this);
        main.setOrientation(LinearLayout.VERTICAL);
        main.setBackgroundColor(Color.parseColor("#0B0F19"));
        main.setPadding(24, 24, 24, 24);

        scrollView = new ScrollView(this);
        scrollView.setLayoutParams(new LinearLayout.LayoutParams(-1, 0, 1.0f));
        chatHistory = new LinearLayout(this);
        chatHistory.setOrientation(LinearLayout.VERTICAL);
        scrollView.addView(chatHistory);
        main.addView(scrollView);

        LinearLayout inputCont = new LinearLayout(this);
        EditText input = new EditText(this);
        input.setLayoutParams(new LinearLayout.LayoutParams(0, -2, 1.0f));
        input.setHint("اكتب أمرك هنا...");
        input.setTextColor(Color.WHITE);
        inputCont.addView(input);

        Button btn = new Button(this);
        btn.setText("إرسال");
        inputCont.addView(btn);
        main.addView(inputCont);
        setContentView(main);

        btn.setOnClickListener(v -> {
            String text = input.getText().toString();
            addMsg("أنت: " + text, Gravity.END);
            AIOrchestrator.process(this, text, res -> addMsg("النظام: " + res, Gravity.START));
            input.setText("");
        });
    }

    private void addMsg(String msg, int g) {
        TextView tv = new TextView(this);
        tv.setText(msg);
        tv.setTextColor(Color.WHITE);
        tv.setPadding(24, 24, 24, 24);
        LinearLayout.LayoutParams p = new LinearLayout.LayoutParams(-2, -2);
        p.gravity = g;
        tv.setLayoutParams(p);
        chatHistory.addView(tv);
    }
}
