package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.Toast;

public class PrintEngine {
    
    // هذه هي الدالة المطلوبة التي تسبب الخطأ عند غيابها
    public static void printText(Context context, String title, String content) {
        // يمكنك إضافة كود لعرض النتيجة مؤقتاً
        Toast.makeText(context, "النتيجة: " + title, Toast.LENGTH_LONG).show();
    }
}
