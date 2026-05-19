package com.ruqi.eduos.mesc;

import android.content.Context;

public interface AIStrategy {
    boolean isAvailable(Context context);
    // تأكد من وجود المتغيرات كاملة هنا
    void execute(Context context, String prompt, AIProcessor.Callback callback);
}
