import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

const QNA_REST_API = `http://localhost:8080/myroutine/que/`;

// 질문 답변 CRUD
export const useQnAStore = defineStore('qna', () => {

    const qnaList = ref([]);
    const getQnAList = (searchCondition) => {
        axios.get(QNA_REST_API, {params: searchCondition})
        .then((response) => {
            qnaList.value = response.data;
        })
    };

    return { 
        qnaList,
        getQnAList
    };
})
