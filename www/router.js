import { Teacher_Module } from './teacher.js';
import { Student_Module } from './student.js';

export const Router_Module = {
    activeRoute: 'home',
    curriculumData: null,

    init(data) {
        this.curriculumData = data;
        this.navigate('home');
    },

    navigate(route) {
        this.activeRoute = route;
        const container = document.getElementById('app-root');
        const navHome = document.getElementById('btn-home');
        const navTeacher = document.getElementById('btn-teacher');
        
        if (!container) return;

        if (navHome) navHome.classList.remove('active');
        if (navTeacher) navTeacher.classList.remove('active');

        if (route === 'teacher') {
            if (navTeacher) navTeacher.classList.add('active');
            Teacher_Module.render(container, this.curriculumData);
        } else {
            if (navHome) navHome.classList.add('active');
            Student_Module.render(container, this.curriculumData);
        }
    }
};
