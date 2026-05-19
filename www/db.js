const DB_NAME = "RuqiEducationalOS_DB";
const DB_VERSION = 2;
const STORE_CURRICULUM = "curriculum_store";
const STORE_LICENSE = "system_license";

export const DB_Module = {
    db: null,

    init() {
        return new Promise((resolve, reject) => {
            const request = indexedDB.open(DB_NAME, DB_VERSION);
            request.onerror = () => reject(new Error("Database engine failure"));
            request.onsuccess = (e) => {
                this.db = e.target.result;
                resolve(true);
            };
            request.onupgradeneeded = (e) => {
                const db = e.target.result;
                if (!db.objectStoreNames.contains(STORE_CURRICULUM)) {
                    db.createObjectStore(STORE_CURRICULUM, { keyPath: "curriculumId" });
                }
                if (!db.objectStoreNames.contains(STORE_LICENSE)) {
                    db.createObjectStore(STORE_LICENSE, { keyPath: "id" });
                }
            };
        });
    },

    async fetchCurriculum() {
        try {
            const res = await fetch('./curriculum.json');
            return await res.json();
        } catch {
            return null;
        }
    },

    saveLicense(payload) {
        return new Promise((resolve) => {
            const tx = this.db.transaction(STORE_LICENSE, "readwrite");
            const store = tx.objectStore(STORE_LICENSE);
            store.put({ id: "current_active_license", ...payload });
            tx.oncomplete = () => resolve(true);
        });
    },

    getLicenseStatus() {
        return new Promise((resolve) => {
            if (!this.db) return resolve(null);
            const tx = this.db.transaction(STORE_LICENSE, "readonly");
            const store = tx.objectStore(STORE_LICENSE);
            const req = store.get("current_active_license");
            req.onsuccess = () => resolve(req.result);
            req.onerror = () => resolve(null);
        });
    }
};
