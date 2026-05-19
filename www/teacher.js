export const Teacher_Module = {
    render(container, data) {
        let options = '';
        if (data && data.units) {
            data.units.forEach(u => {
                u.lessons.forEach(l => {
                    options += `<option value="${u.unit_id}-${l.lesson_id}">${u.title} - ${l.title}</option>`;
                });
            });
        }

        container.innerHTML = `
            <div class="card print-hide">
                <h2>بوابة المعلم لتوليد أوراق العمل والأنشطة 📄</h2>
                <p>اختر الدرس المناسب لتوليد ورقة عمل إنتاجية قابلة للطباعة مباشرة بفورمات المنهج الفلسطيني بالتدرج التعليمي المتكامل.</p>
                <select id="lesson-selector" class="input-field">
                    ${options}
                </select>
                <button class="btn-block" id="generate-btn">توليد ورقة العمل للطباعة</button>
            </div>
            <div id="worksheet-area"></div>
        `;

        document.getElementById('generate-btn').addEventListener('click', () => {
            const selection = document.getElementById('lesson-selector').value;
            const [unitId, lessonId] = selection.split('-').map(Number);
            this.generateWorksheet(unitId, lessonId, data);
        });
    },

    generateWorksheet(unitId, lessonId, data) {
        const unit = data.units.find(u => u.unit_id === unitId);
        const lesson = unit.lessons.find(l => l.lesson_id === lessonId);
        const worksheetArea = document.getElementById('worksheet-area');

        worksheetArea.innerHTML = `
            <div class="print-header-flex">
                <div>وزارة التربية والتعليم والتعليم العالي</div>
                <div>نظام رقي التعليمي المستقر</div>
                <div>المادة: رياضيات (المرحلة الأساسية)</div>
            </div>
            <div class="card">
                <center><h2>ورقة عمل: ${lesson.title}</h2></center>
                <div style="display: flex; justify-content: space-between; margin-bottom: 15px;">
                    <span><strong>اسم الطالب:</strong> ............................................</span>
                    <span><strong>الصف:</strong> ${data.grade}</span>
                </div>
                <hr style="border: 1px solid #e2e8f0; margin: 15px 0;"/>
                
                <h3>المرحلة الأولى: التدرج المحسوس (المحسوسات البصرية)</h3>
                <p>تأمل الشكل البصري التالي ثم أجب عن المسألة المرفقة:</p>
                <div class="math-equation">${lesson.concrete_visual}</div>
                
                <h3>المرحلة الثانية: التدرج شبه المحسوس (السياق والمسائل اللفظية)</h3>
                <p>${lesson.semi_concrete}</p>
                <p>الحل مفسراً بالكلمات: .......................................................................................................</p>

                <h3>المرحلة الثالثة: التدرج المجرد (الرموز والأرقام العربية)</h3>
                <p>قم بحل المعادلة الحسابية التالية مستخدماً الأرقام المشرقية الصحيحة المعتمدة:</p>
                <div class="math-equation">${lesson.abstract_symbols}</div>
                
                <button class="btn-block print-hide" onclick="window.print()">اضغط هنا لطباعة ورقة العمل الحالية (A4)</button>
            </div>
        `;
    }
};
