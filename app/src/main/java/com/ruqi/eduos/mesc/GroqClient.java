package com.ruqi.eduos.mesc;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class GroqClient {
    private static final String TAG = "GroqClient";
    // ضع مفتاح Groq الخاص بك هنا
    private static final String API_KEY = "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"; 

    public static String sendMessage(String prompt) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.groq.com/openai/v1/chat/completions");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setDoOutput(true);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", "llama3-8b-8192"); // نموذج سريع وممتاز جداً للمهام التعليمية

            JSONArray messages = new JSONArray();
            JSONObject messageObj = new JSONObject();
            messageObj.put("role", "user");
            messageObj.put("content", prompt);
            messages.put(messageObj);
            
            jsonBody.put("messages", messages);

            OutputStream os = conn.getOutputStream();
            os.write(jsonBody.toString().getBytes("UTF-8"));
            os.flush();
            os.close();

            int responseCode = conn.getResponseCode();
            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(conn.getInputStream(), "UTF-8");
                String response = scanner.useDelimiter("\\A").next();
                scanner.close();

                JSONObject jsonResponse = new JSONObject(response);
                return jsonResponse.getJSONArray("choices")
                                .getJSONObject(0)
                                .getJSONObject("message")
                                .getString("content");
            } else {
                InputStream errorStream = conn.getErrorStream();
                if (errorStream != null) {
                    Scanner scanner = new Scanner(errorStream, "UTF-8");
                    Log.e(TAG, "Groq Error Details: " + scanner.useDelimiter("\\A").next());
                    scanner.close();
                }
                return "فشل استقبال الاستجابة من Groq. رمز الخطأ: " + responseCode;
            }
        } catch (Exception e) {
            Log.e(TAG, "Groq Exception", e);
            return "عطل فني في الاتصال بشبكة Groq: " + e.getMessage();
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
