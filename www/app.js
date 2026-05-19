import { DB_Module } from './db.js';
import { Router_Module } from './router.js';
import { AI_Module } from './ai.js';

class RuqiOSKernel {
    async boot() {
        console.log("[Ruqi OS Pro] Kernel boot sequence initiated...");
        await DB_Module.init();
        
        // التحقق وتأمين حالة الترخيص والاشتراك في الذاكرة
        const status = await DB_Module.getLicenseStatus();
        window.RuqiOS_License = status || { type: "free", expires: null, active: false };

        const curriculumData = await DB_Module.fetchCurriculum();
        
        // ربط الواجهة العالمية بالنواة الحية لتسهيل الاستدعاء من الـ DOM
        window.RuqiOS = {
            navigate: (route) => Router_Module.navigate(route),
            toggleAssistant: () => this.toggleAssistantPanel(),
            sendAssistantMessage: () => this.handleAssistantInput(),
            triggerEmergencyEvent: () => this.executeEmergencySequence(),
            verifyRedeemCode: () => this.verifyVoucherCode()
        };

        Router_Module.init(curriculumData);
    }

    toggleAssistantPanel() {
        const chat = document.getElementById('chat-box-viewport');
        if (chat) chat.classList.toggle('hidden');
    }

    async handleAssistantInput() {
        const input = document.getElementById('chat-input-field');
        const container = document.getElementById('chat-messages-container');
        if (!input || !input.value.trim()) return;

        const text = input.value.trim();
        input.value = '';

        container.innerHTML += `<div class="msg user">${text}</div>`;
        container.scrollTop = container.scrollHeight;

        // استدعاء نظام التوزيع الذكي للذكاء الاصطناعي متعدد الأنماط
        const aiResponse = await AI_Module.routeTaskToExpert("tutor", text);
        container.innerHTML += `<div class="msg ai">${aiResponse}</div>`;
        container.scrollTop = container.scrollHeight;
    }

    executeEmergencySequence() {
        const reason = document.getElementById('emergency-reason-input')?.value || "تنبيه طوارئ عاجل وصامت";
        const logContainer = document.getElementById('emergency-live-logs');
        const timestamp = new Date().toLocaleTimeString('ar-EG');
        
        if (logContainer) {
            logContainer.innerHTML = `<div style="color: var(--neon-crimson); font-weight: bold;">[⏰ ${timestamp}] تم إرسال إشعار فوري لوزارة التربية وغرفة الطوارئ المشتركة: ${reason}</div>` + logContainer.innerHTML;
        }
    }

    async verifyVoucherCode() {
        const codeInput = document.getElementById('redeem-code-field');
        const statusBox = document.getElementById('activation-status-callback');
        if (!codeInput || !codeInput.value.trim()) return;

        const code = codeInput.value.trim();
        let result = { success: false, message: "كود التفعيل غير صالح أو منتهي الصلاحية" };

        if (code.startsWith("RUQI-TEACH-") && code.length > 15) {
            result = { success: true, message: "تم تفعيل حساب معلم محترف بنجاح! صلاحية غير محدودة.", type: "teacher" };
        } else if (code.startsWith("RUQI-SCHOOL-")) {
            result = { success: true, message: "تم تفعيل اشتراك المؤسسة المدرسية بنجاح بنظام SaaS.", type: "school" };
        }

        if (result.success) {
            const licensePayload = { type: result.type, expires: "2027-12-31", active: true, key: code };
            await DB_Module.saveLicense(licensePayload);
            window.RuqiOS_License = licensePayload;
            statusBox.innerHTML = `<div style="color: var(--neon-emerald); font-weight: bold; margin-top:10px;">🟢 ${result.message}</div>`;
            setTimeout(() => Router_Module.navigate('home'), 2000);
        } else {
            statusBox.innerHTML = `<div style="color: var(--neon-crimson); font-weight: bold; margin-top:10px;">❌ ${result.message}</div>`;
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const systemOS = new RuqiOSKernel();
    systemOS.boot();
});
