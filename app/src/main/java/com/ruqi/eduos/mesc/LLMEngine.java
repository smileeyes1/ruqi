package com.ruqi.eduos.mesc;

import android.os.Handler;
import android.os.Looper;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class LLMEngine {
    // ضع هنا عنوان IP الخاص بجهاز الكمبيوتر الذي يعمل عليه Ollama
    private static final String SERVER_URL = "http://192.168.1.10:11434/api/generate";

    public interface Callback { void onResponse(String result); }

    public static void generate(String prompt, Callback callback) {
        new Thread(() -> {
            try {
                URL url = new URL(SERVER_URL);
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
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse("خطأ اتصال: " + e.getMessage()));
            }
        }).start();
    }
}
