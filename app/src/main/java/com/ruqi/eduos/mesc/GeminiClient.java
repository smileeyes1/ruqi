package com.ruqi.eduos.mesc;

import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

public class GeminiClient {
    private static final String API_KEY = BuildConfig.GEMINI_API_KEY;
    private static final String URL_API = "https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + API_KEY;

    public static String sendMessage(String prompt) {
        try {
            URL url = new URL(URL_API);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            String jsonInput = "{\"contents\":[{\"parts\":[{\"text\":\"" + prompt + "\"}]}]}";
            try (OutputStream os = conn.getOutputStream()) {
                os.write(jsonInput.getBytes());
            }

            if (conn.getResponseCode() == 200) {
                Scanner scanner = new Scanner(conn.getInputStream());
                return scanner.useDelimiter("\\A").hasNext() ? scanner.next() : "لا يوجد رد";
            } else {
                return "خطأ اتصال: " + conn.getResponseCode();
            }
        } catch (Exception e) {
            return "فشل: " + e.getMessage();
        }
    }
}
