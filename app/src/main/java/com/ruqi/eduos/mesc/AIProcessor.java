package com.ruqi.eduos.mesc;

import android.os.Handler;
import android.os.Looper;

public class AIProcessor {
    public interface Callback { void onResponse(String result); }

    public static void postResult(Callback callback, String result) {
        new Handler(Looper.getMainLooper()).post(() -> {
            if (callback != null) callback.onResponse(result);
        });
    }
}
