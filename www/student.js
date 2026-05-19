export const Student_Module = {
    render(container, data) {
        let lessonsHtml = '';
        if (data && data.units) {
            data.units.forEach(u => {
                lessonsHtml += `<h3>${u.title}</h3>`;
                u.lessons.forEach(l => {
                    lessonsHtml += `
                        <div class="card">
                            <h4>${l.title} <span class="badge">رياضيات</span></h4>
                            <p><strong>المحسوس البصري:</strong> ${l.concrete_visual}</p>
                            <p><strong>التمرين الحسابي:</strong> ${l.abstract_symbols}</p>
                            <input type="text" class="input-field" placeholder="اكتب الحل هنا بالأرقام العربية المستقرة..." />
                        </div>
                    `;
                });
            });
        }

        container.innerHTML = `
            <div class="card">
                <h2>مرحباً بك في فضاء نظام رقي التعليمي الذكي 🧭</h2>
                <p>استعرض الوحدات التعليمية التفاعلية وابدأ التدريب الفوري والمستقر بالتدرج المنهجي.</p>
            </div>
            ${lessonsHtml}
        `;
    }
};
