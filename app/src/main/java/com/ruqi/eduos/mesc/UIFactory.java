package com.ruqi.eduos.mesc;

import android.content.Context;
import android.graphics.Color;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

public class UIFactory {
    public static LinearLayout createRootLayout(Context context) {
        LinearLayout layout = new LinearLayout(context);
        layout.setOrientation(LinearLayout.VERTICAL);
        layout.setBackgroundColor(Color.parseColor("#121212"));
        layout.setPadding(40, 40, 40, 40);
        return layout;
    }

    public static TextView createHeader(Context context, String text) {
        TextView tv = new TextView(context);
        tv.setText(text);
        tv.setTextColor(Color.parseColor("#00E5FF"));
        tv.setTextSize(22);
        tv.setPadding(0, 0, 0, 40);
        return tv;
    }

    public static Button createMenuButton(Context context, String text, android.view.View.OnClickListener listener) {
        Button btn = new Button(context);
        btn.setText(text);
        btn.setBackgroundColor(Color.parseColor("#1E1E1E"));
        btn.setTextColor(Color.WHITE);
        btn.setPadding(30, 30, 30, 30);
        btn.setOnClickListener(listener);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.setMargins(0, 0, 0, 20);
        btn.setLayoutParams(params);
        return btn;
    }
}
