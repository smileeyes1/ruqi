package com.ruqi.eduos.mesc;

import android.content.Context;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class PrintEngine {

    public static void printText(Context context, String title, String content) {
        WebView webView = new WebView(context);
        
        // قمنا بإضافة dir='rtl' ومحاذاة النصوص لليمين (text-align: right)
        String htmlData = "<html dir='rtl'><body style='font-family: Arial, sans-serif; text-align: right; margin: 20px;'>" +
                          "<h1 style='color: #000;'>" + title + "</h1>" +
                          "<p style='font-size: 16px; line-height: 1.6;'>" + content.replace("\n", "<br>") + "</p>" +
                          "</body></html>";
        
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                createPrintJob(context, view);
            }
        });
        
        webView.loadDataWithBaseURL(null, htmlData, "text/HTML", "UTF-8", null);
    }

    private static void createPrintJob(Context context, WebView webView) {
        PrintManager printManager = (PrintManager) context.getSystemService(Context.PRINT_SERVICE);
        // إعدادات الطباعة الافتراضية
        printManager.print("Ruqi_Document", webView.createPrintDocumentAdapter(), new PrintAttributes.Builder().build());
    }
}
