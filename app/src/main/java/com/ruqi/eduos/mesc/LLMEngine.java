package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LLMEngine {

    public interface Callback { void onResponse(String result); }

    public static void generate(Context context, String prompt, Callback callback) {
        // جلب العنوان من قاعدة البيانات
        String serverUrl = DatabaseEngine.load(context, "SERVER_URL");
        if (serverUrl.equals("لا توجد بيانات محفوظة")) {
            serverUrl = "http://10.0.2.2:11434/api/generate"; // الافتراضي للذكاء التلقائي
        }

        final String finalUrl = serverUrl;

        new Thread(() -> {
            try {
                URL url = new URL(finalUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setConnectTimeout(5000);

                String jsonInput = "{\"model\": \"llama3\", \"prompt\": \"" + prompt + "\", \"stream\": false}";
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonInput.getBytes());
                }

                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) response.append(scanner.nextLine());
                scanner.close();

                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(response.toString()));
            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("خطأ: تأكد من تشغيل Ollama وسلامة العنوان: " + e.getMessage()));
            }
        }).start();
    }
}
