export const Monetization_Module = {
    render(container) {
        // التحقق من حالة الاشتراك الحالية من النواة
        const license = window.RuqiOS_License || { type: "free", active: false };
        
        container.innerHTML = `
            <div class="kernel-glass-card premium-card">
                <h2 class="hero-title" style="color: var(--neon-amber);">النظام الاقتصادي وبوابة تفعيل الاشتراكات 💰</h2>
                <p class="hero-subtitle" style="margin-bottom:20px;">قم بإدخال كود الهدية أو كود التفعيل المعتمد (Redeem Gift Code) لترقية صلاحيات حسابك التعليمي والاستفادة من محركات الذكاء الاصطناعي الشاملة لوحدات الـ SaaS.</p>
                
                <div style="background: rgba(0,0,0,0.2); padding:15px; border-radius:8px; margin-bottom:20px;">
                    <strong>حالة ترخيص النظام الحالي:</strong> 
                    <span style="color: ${license.active ? 'var(--neon-emerald)' : 'var(--neon-amber)'}; font-weight:bold;">
                        ${license.active ? `نشط ومميز (نوع الباقة: ${license.type})` : 'النسخة المجانية المحدودة (Free Demo Baseline)'}
                    </span>
                </div>

                <div class="activation-form-block">
                    <label style="display:block; margin-bottom:8px; font-weight:bold;">أدخل كود الترخيص السري (Gift / Redeem Code):</label>
                    <input type="text" id="redeem-code-field" class="kernel-input" placeholder="مثال: RUQI-TEACH-XXXX-YYYY" style="text-transform: uppercase; letter-spacing:1px;"/>
                    <button class="kernel-btn-primary" style="width:100%; background: linear-gradient(90deg, var(--neon-amber), #d97706); color:white;" onclick="window.RuqiOS.verifyRedeemCode()">التحقق الفوري وتفعيل الصلاحيات</button>
                    <div id="activation-status-callback"></div>
                </div>
            </div>

            <div class="kernel-glass-card">
                <h3 style="color: var(--neon-cyan); margin-bottom:10px;">🛡️ لوحة الإدارة والتحكم المالي (إصدار الـ Gift Codes الجاهزة للبيع)</h3>
                <p style="font-size:0.85rem; color: var(--text-muted); margin-bottom:15px;">خاصة بإدارة المنصة لإنتاج أكواد تفعيل جديدة وتوزيعها للمعلمين أو المدارس المتعاقدة.</p>
                
                <div style="display:flex; gap:10px; margin-bottom:15px;">
                    <button class="kernel-btn-primary" style="font-size:0.85rem; padding:10px;" id="gen-teach-code-btn">توليد كود معلم محترف (Premium Teacher)</button>
                    <button class="kernel-btn-primary" style="font-size:0.85rem; padding:10px; background:var(--neon-emerald);" id="gen-school-code-btn">توليد كود مدرسة متكاملة (SaaS School)</button>
                </div>
                <div id="admin-generated-codes-log" style="background:#000000; padding:12px; border-radius:6px; font-family:monospace; font-size:0.9rem; color:var(--neon-cyan); min-height:40px;">
                    أضغط على الأزرار أعلاه لتوليد كود تفعيل حقيقي وصالح للاستخدام فوراً...
                </div>
            </div>
        `;

        // تفعيل أزرار التوليد الإدارية والمالية الفورية
        document.getElementById('gen-teach-code-btn').addEventListener('click', () => {
            const rand = Math.random().toString(36).substring(2, 8).toUpperCase();
            const code = `RUQI-TEACH-${rand}-2026`;
            document.getElementById('admin-generated-codes-log').innerHTML = `<code>كود جاهز للنسخ والبيع: ${code}</code>`;
        });

        document.getElementById('gen-school-code-btn').addEventListener('click', () => {
            const rand = Math.random().toString(36).substring(2, 8).toUpperCase();
            const code = `RUQI-SCHOOL-${rand}-GLOBAL`;
            document.getElementById('admin-generated-codes-log').innerHTML = `<code>كود جاهز للنسخ والبيع: ${code}</code>`;
        });
    }
};
