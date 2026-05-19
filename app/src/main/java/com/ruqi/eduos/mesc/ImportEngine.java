package com.ruqi.eduos.mesc;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;

/**
 * محرك استيراد الملفات (ImportEngine)
 * يسمح للمعلم باختيار ملفات PDF من الهاتف
 */
public class ImportEngine {

    public static final int REQUEST_CODE_PDF = 101;

    public static void launchFilePicker(Activity activity) {
        try {
            Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
            intent.addCategory(Intent.CATEGORY_OPENABLE);
            intent.setType("application/pdf");
            activity.startActivityForResult(intent, REQUEST_CODE_PDF);
        } catch (Exception e) {
            Toast.makeText(activity, "خطأ في فتح الملفات: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}
