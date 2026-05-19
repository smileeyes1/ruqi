package com.ruqi.eduos.mesc;

import android.content.Context;
import android.webkit.WebView;
import android.widget.LinearLayout;
import android.widget.Button;

public class PrintEngine {

    // الدالة التي استدعيتها سابقاً في الأكواد الأخرى
    public static void printText(Context context, String title, String content) {
        showPreview(context, "<h1>" + title + "</h1><p>" + content + "</p>");
    }

    // الدالة الأصلية للمعاينة
    public static void showPreview(Context context, String content) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "معاينة الطباعة"));

        WebView webView = new WebView(context);
        webView.setLayoutParams(new LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT, 0, 1.0f));

        String htmlData = "<html><body style='direction:rtl; font-family:Arial; padding:20px;'>" +
                "<div style='border:2px solid #333; padding:20px;'>" +
                content.replace("\n", "<br>") +
                "</div></body></html>";

        webView.loadDataWithBaseURL(null, htmlData, "text/html", "UTF-8", null);
        layout.addView(webView);
        
        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
