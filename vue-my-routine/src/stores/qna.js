import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

const QNA_REST_API = `http://localhost:8080/myroutine/que/`;
const USER_REST_API = `http://localhost:8080/myroutine/user/`

// 질문 답변 CRUD
export const useQnAStore = defineStore('qna', () => {

    const qnaList = ref([]);
    const userDetails = ref({});

    const getQnAList = (searchCondition) => {
        axios.get(QNA_REST_API, {params: searchCondition})
        .then((response) => {
            qnaList.value = response.data;
            qnaList.value.forEach((qna) => {
                qna.userAge = 0;
                getUserDetails(qna);
            })
        })
    };

    const getUserDetails = (qna) => {
        axios.get(`${USER_REST_API}${qna.userId}`)
        .then((response) => {
            userDetails.value = response.data;

            const age = userDetails.value.age;

            if (age >= 50) {
                qna.userAge = '50대';
            } else if (age >= 40) {
                qna.userAge = '40대';
            } else if (age >= 30) {
                qna.userAge = '30대';
            } else if (age >= 20) {
                qna.userAge = '20대';
            } else {
                qna.userAge = '10대';
            }
            // 자세한 나이 표기 시 사용
            // qna.userAge = userDetails.value.age;
        })
    };

    return { 
        qnaList,
        userDetails,
        getQnAList,
        getUserDetails
    };
})
