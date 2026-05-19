package com.ruqi.eduos.mesc;

import android.content.Context;

public class LessonPlanCommand implements AppCommand {
    public String getName() { return "تحضير"; }
    
    public void execute(Context context, String input, AIProcessor.Callback callback) {
        String prompt = "بصفتك معلماً خبيراً بالمنهاج الفلسطيني، قم بتحضير درس حول: " + input + 
                        " مع مراعاة التدرج من المحسوس إلى المجرد.";
        AIOrchestrator.process(context, prompt, callback);
    }
}
