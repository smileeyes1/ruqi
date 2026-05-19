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

    public static void generate(final Context context, final String prompt, final Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String serverUrl = DatabaseEngine.load(context, "SERVER_URL");
                if (serverUrl.equals("لا توجد بيانات محفوظة")) {
                    serverUrl = "http://10.0.2.2:11434/api/generate";
                }

                try {
                    URL url = new URL(serverUrl);
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/json");
                    conn.setDoOutput(true);
                    conn.setConnectTimeout(10000);

                    String jsonInput = "{\"model\": \"llama3\", \"prompt\": \"" + prompt.replace("\"", "\\\"") + "\", \"stream\": false}";
                    
                    try (OutputStream os = conn.getOutputStream()) {
                        os.write(jsonInput.getBytes());
                    }

                    Scanner scanner = new Scanner(conn.getInputStream());
                    StringBuilder response = new StringBuilder();
                    while (scanner.hasNextLine()) response.append(scanner.nextLine());
                    scanner.close();

                    final String cleanResult = response.toString().replaceAll("```json", "").replaceAll("```", "").replace("\\n", "\n").replace("\\\"", "\"");
                    
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse(cleanResult);
                        }
                    });

                } catch (final Exception e) {
                    new Handler(Looper.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            callback.onResponse("خطأ اتصال: تأكد من تشغيل Ollama. " + e.getMessage());
                        }
                    });
                }
            }
        }).start();
    }
}
