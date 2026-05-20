package com.ruqi.eduos.mesc;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.util.Log;

public class AIOrchestrator {
    private static final String TAG = "AIOrchestrator";

    public static void process(final Context context, final String prompt, final AIProcessor.Callback callback) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String result = "";
                // ١. محاولة جيميناي
                try { result = GeminiClient.sendMessage(prompt); } 
                catch (Exception e) {
                    // ٢. محاولة غروك عند الفشل
                    try { result = GroqClient.sendMessage(prompt); }
                    catch (Exception e2) {
                        // ٣. محاولة شات جي بي تي كخيار أخير
                        try { result = OpenAIClient.sendMessage(prompt); }
                        catch (Exception e3) { result = "تعذر الاتصال بجميع المحركات، يرجى فحص إعدادات الأسرار."; }
                    }
                }
                final String finalResult = result;
                new Handler(Looper.getMainLooper()).post(() -> callback.onResponse(finalResult));
            }
        }).start();
    }
}
