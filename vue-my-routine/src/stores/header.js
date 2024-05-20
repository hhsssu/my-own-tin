import { ref, computed } from 'vue'
import { defineStore } from 'pinia'

// Header 관련
export const useHeaderStore = defineStore('header', {

    state: () => ({
        showHeader: true,
    }),
    actions: {
        toggleHeader(visible) {
            this.showHeader = visible;
        }
    }
})