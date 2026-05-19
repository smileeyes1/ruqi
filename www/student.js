export const Student_Module = {
    render(container, data) {
        let lessonsHtml = '';
        if (data && data.units) {
            data.units.forEach(u => {
                let cards = '';
                u.lessons.forEach(l => {
                    cards += `
                        <div class="kernel-glass-card" style="box-shadow: none; background: rgba(255,255,255,0.02);">
                            <h4 style="color: var(--neon-cyan); margin-bottom: 10px;">${l.title}</h4>
                            <p style="color: var(--text-muted); font-size: 0.85rem; margin-bottom:12px;">تدرج في الحل من الشكل البصري إلى الرمز النهائي:</p>
                            
                            <div class="pedagogical-concrete-box">${l.concrete_visual}</div>
                            <div class="abstract-symbols-display">${l.abstract_symbols}</div>
                            
                            <input type="text" class="kernel-input" placeholder="اكتب الناتج المستقر بالأرقام العربية (٠١٢٣٤٥٦٧٨٩)..." />
                        </div>
                    `;
                });

                lessonsHtml += `
                    <div style="margin-top: 30px;">
                        <h3 style="border-right: 4px solid var(--neon-emerald); padding-right:12px; margin-bottom:15px; font-size:1.2rem;">${u.title}</h3>
                        <div class="grid-3-columns">${cards}</div>
                    </div>
                `;
            });
        }

        container.innerHTML = `
            <div class="kernel-glass-card">
                <h2 class="hero-title">فضاء الطالب الذكي والتعلم التكيفي 🛸</h2>
                <p class="hero-subtitle">استكشف دروس المناهج الفلسطينية بطرق التدريس التدرجية والحديثة المعتمدة عالمياً.</p>
            </div>
            ${lessonsHtml}
        `;
    }
};
