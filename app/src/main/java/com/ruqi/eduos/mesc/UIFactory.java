package com.ruqi.eduos.mesc;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.ViewGroup;

/**
 * مصنع الواجهات (UIFactory)
 * لتوحيد تصميم التطبيق ومنع تكرار الكود
 */
public class UIFactory {

    // 1. هيكل الصفحة الرئيسي
    public static LinearLayout createRootLayout(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.MATCH_PARENT));
        layout.setPadding(30, 30, 30, 30);
        layout.setBackgroundColor(Color.parseColor("#121212")); // خلفية داكنة احترافية
        return layout;
    }

    // 2. الترويسة (Header)
    public static TextView createHeader(Context context, String text) {
        TextView header = new TextView(context);
        header.setText(text);
        header.setTextSize(24);
        header.setTextColor(Color.WHITE);
        header.setTypeface(null, Typeface.BOLD);
        header.setGravity(Gravity.CENTER);
        header.setPadding(0, 0, 0, 50);
        return header;
    }

    // 3. الأزرار (Menu Buttons)
    public static Button createMenuButton(Context context, String text, android.view.View.OnClickListener listener) {
        Button button = new Button(context);
        button.setText(text);
        button.setBackgroundColor(Color.parseColor("#03DAC5"));
        button.setTextColor(Color.BLACK);
        button.setPadding(20, 20, 20, 20);
        button.setOnClickListener(listener);
        // إضافة مسافة بين الأزرار
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 20, 0, 20);
        button.setLayoutParams(params);
        return button;
    }

    // 4. حقل الإدخال الجديد (الذي سألته عنه)
    public static EditText createInputField(Context context, String hint) {
        EditText editText = new EditText(context);
        editText.setHint(hint);
        editText.setHintTextColor(Color.GRAY);
        editText.setTextColor(Color.WHITE);
        editText.setBackgroundColor(Color.parseColor("#333333"));
        editText.setPadding(30, 30, 30, 30);
        
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, 
                ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 10, 0, 10);
        editText.setLayoutParams(params);
        return editText;
    }
}
