import { Student_Module } from './student.js';
import { Teacher_Module } from './teacher.js';
import { Monetization_Module } from './monetization.js';
import { Emergency_Module } from './emergency.js';

export const Router_Module = {
    currentRoute: 'home',
    curriculum: null,

    init(data) {
        this.curriculum = data;
        this.navigate('home');
    },

    navigate(route) {
        this.currentRoute = route;
        const root = document.getElementById('app-root-workspace');
        if (!root) return;

        // مسح مساحة العمل استعداداً لحقن الموديل الجديد
        root.innerHTML = '';

        // إدارة تفعيل الأزرار والتبويبات النشطة علوياً بمحاذاة تامة
        document.querySelectorAll('.nav-control-links button').forEach(btn => btn.classList.remove('active'));
        const activeBtn = document.getElementById(`nav-${route}`);
        if (activeBtn) activeBtn.classList.add('active');

        switch(route) {
            case 'home':
                Student_Module.render(root, this.curriculum);
                break;
            case 'teacher':
                Teacher_Module.render(root, this.curriculum);
                break;
            case 'monetization':
                Monetization_Module.render(root);
                break;
            case 'emergency':
                Emergency_Module.render(root);
                break;
        }
    }
};
