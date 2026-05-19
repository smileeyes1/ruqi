package com.ruqi.eduos.mesc;

import android.content.Context;

public class SmartDispatcher {
    public static void dispatch(Context context, String userInput, AIProcessor.Callback callback) {
        // لا نحتاج لتحليل أوامر برمجية معقدة، سنوجه كل شيء للمحرك العام
        AIOrchestrator.process(context, userInput, callback);
    }
}
