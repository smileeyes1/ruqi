package com.ruqi.eduos.mesc;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class OpenAIClient {
    private static final String TAG = "OpenAIClient";
    // ضع مفتاح OpenAI (ChatGPT) الخاص بك هنا
    private static final String API_KEY = "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"; 

    public static String sendMessage(String prompt) {
        HttpURLConnection conn = null;
        try {
            URL url = new URL("https://api.openai.com/v1/chat/completions");
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("Authorization", "Bearer " + API_KEY);
            conn.setDoOutput(true);

            JSONObject jsonBody = new JSONObject();
            jsonBody.put("model", "gpt-4o-mini"); // نموذج ذكي واقتصادي وسريع للغاية

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
                    Log.e(TAG, "OpenAI Error Details: " + scanner.useDelimiter("\\A").next());
                    scanner.close();
                }
                return "فشل استقبال الاستجابة من OpenAI. رمز الخطأ: " + responseCode;
            }
        } catch (Exception e) {
            Log.e(TAG, "OpenAI Exception", e);
            return "عطل فني في الاتصال بشبكة OpenAI: " + e.getMessage();
        } finally {
            if (conn != null) conn.disconnect();
        }
    }
}
