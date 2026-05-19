import { DB_Module } from './db.js';
import { Router_Module } from './router.js';

class RuqiOSKernel {
    async boot() {
        try {
            console.log("[Kernel] Ruqi OS Runtime Activated.");
            await DB_Module.init();
            const curriculumData = await DB_Module.fetchCurriculum();
            
            window.RuqiOS = {
                navigate: (route) => Router_Module.navigate(route)
            };
            
            Router_Module.init(curriculumData);
        } catch (error) {
            console.error("[Kernel Fatal Error]:", error);
            const root = document.getElementById('app-root');
            if (root) {
                root.innerHTML = `<div class="card" style="border: 1px solid var(--danger);">
                    <h3 style="color: var(--danger);">حدث خطأ أثناء تحميل النواة</h3>
                    <p>${error.message}</p>
                </div>`;
            }
        }
    }
}

document.addEventListener('DOMContentLoaded', () => {
    const kernel = new RuqiOSKernel();
    kernel.boot();
});
