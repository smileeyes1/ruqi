package com.ruqi.eduos.mesc;

import android.content.Context;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Scanner;

class CloudStrategy implements AIStrategy {
    public boolean isAvailable(Context c) { return !"false".equals(DatabaseEngine.load(c, "AI_ENABLED")) && !DatabaseEngine.getApiKey(c).contains("لا توجد"); }
    public void execute(Context c, String p, AIProcessor.Callback cb) {
        new Thread(() -> {
            try {
                String key = DatabaseEngine.getApiKey(c);
                URL url = new URL("https://generativelanguage.googleapis.com/v1beta/models/gemini-1.5-flash:generateContent?key=" + key);
                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                String input = "{\"contents\":[{\"parts\":[{\"text\":\"" + p.replace("\"", "\\\"") + "\"}]}]}";
                try (OutputStream os = conn.getOutputStream()) { os.write(input.getBytes()); }
                Scanner sc = new Scanner(conn.getInputStream());
                StringBuilder sb = new StringBuilder();
                while (sc.hasNextLine()) sb.append(sc.nextLine());
                AIProcessor.postResult(cb, sb.toString());
            } catch (Exception e) { AIProcessor.postResult(cb, "خطأ في الاتصال السحابي"); }
        }).start();
    }
}

class LocalStrategy implements AIStrategy {
    public boolean isAvailable(Context c) { return !"false".equals(DatabaseEngine.load(c, "AI_ENABLED")) && !DatabaseEngine.load(c, "SERVER_URL").contains("لا توجد"); }
    public void execute(Context c, String p, AIProcessor.Callback cb) {
        // تنفيذ مشابه للسحابة ولكن مع رابط الخادم المحلي
        AIProcessor.postResult(cb, "جاري المعالجة محلياً...");
    }
}

class FallbackStrategy implements AIStrategy {
    public boolean isAvailable(Context c) { return true; }
    public void execute(Context c, String p, AIProcessor.Callback cb) {
        AIProcessor.postResult(cb, "التحضير (وضع محلي):\n١. الوسائل: محسوسة.\n٢. العرض: تفاعلي.\n٣. الختام: تقويم.");
    }
}
