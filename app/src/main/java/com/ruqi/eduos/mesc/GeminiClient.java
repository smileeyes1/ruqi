package com.ruqi.eduos.mesc;

import android.util.Log;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;

public class GeminiClient {
    private static final String TAG = "GeminiClient";
    // المفتاح محقون بشكل نهائي
    private static final String API_KEY = "AIzaSyBIj7wLaX8GLwfoNF4-D8HdaRSMEBsAcQ4"; 

    public static String sendMessage(String prompt) {
        HttpURLConnection conn = null;
        try {
            // المسار الأحدث والمضمون لتفادي أي 404
            URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash-latest:generateContent?key=" + API_KEY);
            conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setDoOutput(true);

            JSONObject jsonBody = new JSONObject();
            JSONArray contents = new JSONArray();
            JSONObject contentObj = new JSONObject();
            JSONArray parts = new JSONArray();
            JSONObject textObj = new JSONObject();
            
            textObj.put("text", prompt);
            parts.put(textObj);
            contentObj.put("parts", parts);
            contents.put(contentObj);
            jsonBody.put("contents", contents);

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
                return jsonResponse.getJSONArray("candidates")
                        .getJSONObject(0)
                        .getJSONObject("content")
                        .getJSONArray("parts")
                        .getJSONObject(0)
                        .getString("text");
            } else {
                InputStream errorStream = conn.getErrorStream();
                if (errorStream != null) {
                    Scanner scanner = new Scanner(errorStream, "UTF-8");
                    Log.e(TAG, "Error Details: " + scanner.useDelimiter("\\A").next());
                    scanner.close();
                }
                return "صد الاتصال. تأكد أن مفتاحك مفعل لخدمات (Generative Language API) من منصة جوجل.";
            }
        } catch (Exception e) {
            Log.e(TAG, "Exception Triggered", e);
            return "عطل فني في الإرسال: " + e.getMessage();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
