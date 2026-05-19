package com.ruqi.eduos.mesc;

import android.content.Context;

public class SmartDispatcher {
    public static void dispatch(Context context, String userInput, AIProcessor.Callback callback) {
        AIOrchestrator.process(context, userInput, callback);
    }
}
