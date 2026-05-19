package com.ruqi.eduos.mesc;

import android.content.Context;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class StudentEngine {

    public static void launch(Context context) {
        LinearLayout layout = UIFactory.createRootLayout(context);
        layout.addView(UIFactory.createHeader(context, "سجل الطلاب (الرصد)"));

        // حقول الإدخال
        final android.widget.EditText etName = UIFactory.createInputField(context, "اسم الطالب...");
        final android.widget.EditText etGrade = UIFactory.createInputField(context, "الدرجة...");
        layout.addView(etName);
        layout.addView(etGrade);

        // زر إضافة
        layout.addView(UIFactory.createMenuButton(context, "إضافة إلى السجل", v -> {
            String name = etName.getText().toString();
            String grade = etGrade.getText().toString();
            if (name.isEmpty() || grade.isEmpty()) {
                Toast.makeText(context, "يرجى تعبئة البيانات", Toast.LENGTH_SHORT).show();
            } else {
                String currentList = DatabaseEngine.load(context, "StudentList");
                String newList = (currentList.equals("لا توجد بيانات محفوظة") ? "" : currentList) + 
                                 "\n" + name + " : " + grade;
                DatabaseEngine.save(context, "StudentList", newList);
                launch(context); // تحديث الواجهة
            }
        }));

        // زر طباعة التقرير
        layout.addView(UIFactory.createMenuButton(context, "طباعة تقرير الصف", v -> {
            String list = DatabaseEngine.load(context, "StudentList");
            if (list.equals("لا توجد بيانات محفوظة")) {
                Toast.makeText(context, "السجل فارغ!", Toast.LENGTH_SHORT).show();
            } else {
                PrintEngine.printText(context, "تقرير أداء الصف", list);
            }
        }));

        // عرض القائمة
        TextView tvList = new TextView(context);
        String savedList = DatabaseEngine.load(context, "StudentList");
        tvList.setText(savedList.equals("لا توجد بيانات محفوظة") ? "لا يوجد طلاب" : savedList);
        tvList.setTextColor(android.graphics.Color.WHITE);
        tvList.setTextSize(16);
        tvList.setPadding(0, 30, 0, 30);
        layout.addView(tvList);

        if (context instanceof MainActivity) {
            ((MainActivity) context).setContentView(layout);
        }
    }
}
