package com.ruqi.eduos.mesc;

import android.content.Context;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.LinearLayout;

public class ViewerEngine {

    public static void launch(Context context, String uri) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "عرض المنهاج"));

        WebView webView = new WebView(context);
        WebSettings settings = webView.getSettings();
        settings.setJavaScriptEnabled(true);
        settings.setAllowFileAccess(true);
        
        webView.setWebViewClient(new WebViewClient());
        
        // تحميل المسار المحفوظ
        webView.loadUrl(uri);
        
        layout.addView(webView);

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
