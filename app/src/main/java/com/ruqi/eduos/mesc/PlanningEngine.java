package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.view.View;

public class PlanningEngine {
    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "توليد التحضير الذكي"));

        final EditText etTopic = UIFactory.createInputField(context, "موضوع الدرس...");
        layout.addView(etTopic);

        final ProgressBar loader = new ProgressBar(context);
        loader.setVisibility(View.GONE);
        layout.addView(loader);

        layout.addView(UIFactory.createMenuButton(context, "توليد", v -> {
            String topic = etTopic.getText().toString();
            if (topic.isEmpty()) return;
            loader.setVisibility(View.VISIBLE);

            // الاستدعاء الذكي
            AIProcessor.generate(context, "اكتب تحضير درس: " + topic, result -> {
                loader.setVisibility(View.GONE);
                DatabaseEngine.save(context, "LAST_PLAN", result);
                PrintEngine.printText(context, "التحضير: " + topic, result);
            });
        }));

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
