package com.ruqi.eduos.mesc;

import android.content.Context;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

public class PlanningEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "التحضير الذكي"));

        final EditText et = UIFactory.createInputField(context, "اكتب موضوع الدرس...");
        layout.addView(et);

        final ProgressBar loader = new ProgressBar(context);
        loader.setVisibility(View.GONE);
        layout.addView(loader);

        layout.addView(UIFactory.createMenuButton(context, "توليد", v -> {
            loader.setVisibility(View.VISIBLE);
            AIOrchestrator.process(context, et.getText().toString(), result -> {
                loader.setVisibility(View.GONE);
                // هنا سيتم لاحقاً ربط PrintEngine
            });
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
