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
    // المفتاح محقون وجاهز للعمل بصمت
    private static final String API_KEY = "AIzaSyBIj7wLaX8GLwfoNF4-D8HdaRSMEBsAcQ4"; 

    public static String sendMessage(String prompt) {
        HttpURLConnection conn = null;
        try {
            // تفعيل المسار العالمي المستقر (v1/gemini-pro) لضمان تجاوز خطأ 404 نهائياً
            URL url = new URL("https://generativelanguage.googleapis.com/v1/models/gemini-pro:generateContent?key=" + API_KEY);
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
                // جلب تفاصيل الرفض بدقة من جوجل في حال وجود حظر
                InputStream errorStream = conn.getErrorStream();
                String errorMsg = "";
                if (errorStream != null) {
                    Scanner scanner = new Scanner(errorStream, "UTF-8");
                    errorMsg = scanner.useDelimiter("\\A").next();
                    scanner.close();
                    Log.e(TAG, "تفاصيل خطأ الخادم: " + errorMsg);
                }
                return "تم صد الاتصال. رمز الخطأ: " + responseCode + " - تأكد من تفعيل (Generative Language API) لمفتاحك.";
            }
        } catch (Exception e) {
            Log.e(TAG, "استثناء دفاعي", e);
            return "فشل الاتصال بالنواة الذكية: " + e.getMessage();
        } finally {
            if (conn != null) {
                conn.disconnect();
            }
        }
    }
}
