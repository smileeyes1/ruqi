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
        // جلب العنوان المحفوظ أو الافتراضي
        String serverUrl = DatabaseEngine.load(context, "SERVER_URL");
        if (serverUrl.equals("لا توجد بيانات محفوظة")) {
            serverUrl = "http://10.0.2.2:11434/api/generate";
        }

        new Thread(() -> {
            try {
                URL url = new URL(serverUrl);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                conn.setConnectTimeout(10000);

                // إعداد البيانات مع معالجة الاقتباسات لضمان عدم تعطل الـ JSON
                String jsonInput = "{\"model\": \"llama3\", \"prompt\": \"" + prompt.replace("\"", "\\\"") + "\", \"stream\": false}";
                try (OutputStream os = conn.getOutputStream()) {
                    os.write(jsonInput.getBytes());
                }

                Scanner scanner = new Scanner(conn.getInputStream());
                StringBuilder response = new StringBuilder();
                while (scanner.hasNextLine()) response.append(scanner.nextLine());
                scanner.close();

                // تنقية المحتوى قبل إرساله للواجهة
                String cleanResult = cleanOutput(response.toString());
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(cleanResult));

            } catch (Exception e) {
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("خطأ اتصال: تأكد من تشغيل Ollama.\n" + e.getMessage()));
            }
        }).start();
    }

    private static String cleanOutput(String raw) {
        // إزالة علامات التنسيق التقنية (Markdown/JSON) وتنظيف النص
        return raw.replaceAll("```json", "")
                  .replaceAll("```", "")
                  .replace("\\n", "\n")
                  .replace("\\\"", "\"");
    }
}
