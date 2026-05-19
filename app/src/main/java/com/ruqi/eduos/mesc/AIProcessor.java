package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class AIProcessor {
    public interface Callback { void onResponse(String result); }

    public static void generate(Context context, String prompt, Callback callback) {
        String apiKey = DatabaseEngine.load(context, "API_KEY");
        String serverUrl = DatabaseEngine.load(context, "SERVER_URL");

        // ١. الأولوية للـ Cloud (إذا وجد مفتاح)
        if (!apiKey.equals("لا توجد بيانات محفوظة") && !apiKey.isEmpty()) {
            callCloud(apiKey, prompt, callback);
        }
        // ٢. الأولوية للـ Local Server (إذا وجد رابط)
        else if (!serverUrl.equals("لا توجد بيانات محفوظة") && !serverUrl.isEmpty()) {
            callLocalServer(serverUrl, prompt, callback);
        }
        // ٣. الأولوية الأخيرة للـ Template (محلي)
        else {
            callTemplate(prompt, callback);
        }
    }

    private static void callCloud(String key, String prompt, Callback cb) {
        new Thread(() -> {
            try {
                URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + key);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                String input = "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt.replace("\"", "\\\"") + "\"}]}]}";
                try(OutputStream os = conn.getOutputStream()) { os.write(input.getBytes()); }
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder sb = new StringBuilder();
                while(sc.hasNextLine()) sb.append(sc.nextLine());
                sc.close();
                new Handler(Looper.getMainLooper()).post(() -> cb.onResponse("Gemini: " + sb.toString()));
            } catch (Exception e) { new Handler(Looper.getMainLooper()).post(() -> cb.onResponse("خطأ سحابي: " + e.getMessage())); }
        }).start();
    }

    private static void callLocalServer(String urlStr, String prompt, Callback cb) {
        new Thread(() -> {
            try {
                URL url = new URL(urlStr);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setDoOutput(true);
                String input = "{\"model\": \"llama3\", \"prompt\": \"" + prompt.replace("\"", "\\\"") + "\", \"stream\": false}";
                try(OutputStream os = conn.getOutputStream()) { os.write(input.getBytes()); }
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder sb = new StringBuilder();
                while(sc.hasNextLine()) sb.append(sc.nextLine());
                sc.close();
                new Handler(Looper.getMainLooper()).post(() -> cb.onResponse("Local: " + sb.toString()));
            } catch (Exception e) { new Handler(Looper.getMainLooper()).post(() -> cb.onResponse("خطأ خادم: " + e.getMessage())); }
        }).start();
    }

    private static void callTemplate(String prompt, Callback cb) {
        String res = "تحضير ذاتي: " + prompt + "\n- الهدف: التعلم.\n- الوسيلة: التفاعل.\n(ملاحظة: هذا رد محلي لعدم وجود إعدادات ربط).";
        new Handler(Looper.getMainLooper()).post(() -> cb.onResponse(res));
    }
}
