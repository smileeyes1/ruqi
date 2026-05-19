const DB_NAME = "RuqiStorageEngine";
const DB_VERSION = 1;
const STORE_NAME = "curriculum";

export const DB_Module = {
    dbInstance: null,

    init() {
        return new Promise((resolve, reject) => {
            const request = indexedDB.open(DB_NAME, DB_VERSION);
            request.onerror = () => reject(new Error("IndexedDB Database access failure"));
            request.onsuccess = (e) => {
                this.dbInstance = e.target.result;
                resolve(true);
            };
            request.onupgradeneeded = (e) => {
                const db = e.target.result;
                if (!db.objectStoreNames.contains(STORE_NAME)) {
                    db.createObjectStore(STORE_NAME, { keyPath: "curriculumId" });
                }
            };
        });
    },

    async fetchCurriculum() {
        try {
            const response = await fetch('./curriculum.json');
            if (!response.ok) throw new Error("Network asset unreadable");
            return await response.json();
        } catch (err) {
            console.log("[DB] Falling back to default baseline data");
            return null;
        }
    }
};
