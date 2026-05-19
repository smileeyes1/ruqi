package com.ruqi.eduos.mesc;

import android.content.Context;
import android.content.Intent;
import android.print.PrintAttributes;
import android.print.PrintManager;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

public class PrintEngine {

    public static void printText(Context context, String title, String content) {
        // إنشاء WebView مؤقت لتحويل النص إلى تنسيق طباعة
        WebView webView = new WebView(context);
        String htmlData = "<html><body><h1>" + title + "</h1><p>" + content.replace("\n", "<br>") + "</p></body></html>";
        
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
        printManager.print("Ruqi_Document", webView.createPrintDocumentAdapter(), new PrintAttributes.Builder().build());
    }
}
