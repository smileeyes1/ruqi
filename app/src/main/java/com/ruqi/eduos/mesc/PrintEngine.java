package com.ruqi.eduos.mesc;

import android.content.Context;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Button;

public class PrintEngine {

    public static void showPreview(Context context, String content) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "معاينة الطباعة"));

        // إنشاء محرك عرض للـ HTML
        WebView webView = new WebView(context);
        webView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));

        // قالب HTML احترافي للتعليم (يدعم RTL والأرقام المشرقية)
        String htmlData = "<html><body style='direction:rtl; font-family:Arial; padding:20px;'>" +
                "<div style='border:2px solid #333; padding:20px;'>" +
                content.replace("\n", "<br>") +
                "</div></body></html>";

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
        layout.addView(webView);

        // زر الطباعة (يتصل بنظام أندرويد لفتح مربع حوار الطباعة)
        Button printBtn = UIFactory.createMenuButton(context, "طباعة / حفظ كـ PDF", v -> {
            // هنا يتم استدعاء Android PrintManager لاحقاً
        });
        layout.addView(printBtn);

        if (context instanceof MainActivity) ((MainActivity) context).setContentView(layout);
    }
}
