export const Emergency_Module = {
    render(container) {
        container.innerHTML = `
            <div class="kernel-glass-card emergency-card">
                <h2 class="hero-title" style="color: var(--neon-crimson);">نظام وسر الطوارئ المدمج العاجل (Emergency Terminal) 🚨</h2>
                <p class="hero-subtitle" style="margin-bottom:20px;">نظام الحماية والأمن المدرسي الفوري لمعلمي الصفوف الأولى في بيئة الطوارئ. يتيح لك إرسال إشعار فوري صامت وموثق لغرف الطوارئ ومديري المؤسسة التعليمية.</p>
                
                <div style="margin-bottom:15px;">
                    <label style="display:block; margin-bottom:6px; font-weight:bold;">حدد نوع الحدث أو سبب الاستدعاء العاجل:</label>
                    <input type="text" id="emergency-reason-input" class="kernel-input" placeholder="مثال: حالة صحية طارئة لطالب في الصف الأول / حدث أمني مستعجل..." style="border-color: var(--neon-crimson); text-align:right;"/>
                </div>

                <button class="tab-emergency-link" style="width:100%; padding:16px; font-size:1.1rem;" onclick="window.RuqiOS.triggerEmergencyEvent()">⚠️ إطلاق نداء الاستغاثة وتسجيل الحدث فوراً</button>
            </div>

            <div class="kernel-glass-card">
                <h3 style="color: var(--text-primary); margin-bottom:12px;">📋 السجل التاريخي الفوري للأحداث المسجلة (Live Emergency Logs):</h3>
                <div id="emergency-live-logs" style="background: rgba(0,0,0,0.4); padding:15px; border-radius:8px; min-height:8px; font-size:0.9rem;">
                    <div style="color: var(--text-muted);">لا توجد أحداث طارئة مسجلة حالياً. النظام يعمل بأمان واستقرار تام.</div>
                </div>
            </div>
        `;
    }
};
