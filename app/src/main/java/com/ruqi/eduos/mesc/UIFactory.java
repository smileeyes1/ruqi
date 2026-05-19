package com.ruqi.eduos.mesc;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.view.View;

public class UIFactory {

    public static LinearLayout createRootLayout(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setPadding(30, 30, 30, 30);
        layout.setGravity(Gravity.TOP);
        return layout;
    }

    public static TextView createHeader(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextSize(20);
        tv.setPadding(0, 20, 0, 20);
        return tv;
    }

    public static EditText createInputField(Context context, String hint) {
        EditText et = new EditText(context);
        et.setHint(hint);
        et.setPadding(20, 20, 20, 20);
        return et;
    }

    public static Button createMenuButton(Context context, String text, View.OnClickListener listener) {
        Button btn = new Button(context);
        btn.setText(text);
        btn.setOnClickListener(listener);
        return btn;
    }
}
