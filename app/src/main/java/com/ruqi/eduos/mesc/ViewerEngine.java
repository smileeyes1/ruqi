package com.ruqi.eduos.mesc;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.widget.Toast;

public class ViewerEngine {

    public static void launch(Context context, String uriString) {
        try {
            Uri uri = Uri.parse(uriString);
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(uri, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY | Intent.FLAG_GRANT_READ_URI_PERMISSION);
            
            context.startActivity(intent);
        } catch (Exception e) {
            Toast.makeText(context, "لا يوجد تطبيق لقراءة الملفات، يرجى تثبيت قارئ PDF.", Toast.LENGTH_LONG).show();
        }
    }
}
