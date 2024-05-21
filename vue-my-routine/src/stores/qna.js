import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';

const QNA_REST_API = `http://localhost:8080/myroutine/que/`;
const ANS_REST_API = `http://localhost:8080/myroutine/ans/`;
const USER_REST_API = `http://localhost:8080/myroutine/user/`;
const ROUTINE_REST_API = `http://localhost:8080/myroutine/routine/`;

// 질문 답변 CRUD
export const useQnAStore = defineStore('qna', () => {

    const qnaList = ref([]);
    const ansList = ref([]);
    const userDetails = ref({});

    const getQnAList = (searchCondition) => {
        axios.get(QNA_REST_API, {params: searchCondition})
        .then((response) => {
            qnaList.value = response.data;
            qnaList.value.forEach((qna) => {
                qna.userAge = 0;
                qna.userLevel = 1;
                qna.userGender = 'NONE';
                qna.routine = {};

                getUserDetails(qna, 'qna');
                getRoutine(qna);
            })
        })
    };

    const getUserDetails = (item, type) => {
        console.log(type + ' ' + item.userId);
        axios.get(`${USER_REST_API}${item.userId}`)
            .then((response) => {
                const user = response.data;
                const age = user.age;

                if (age >= 50) {
                    item.userAge = '50대';
                } else if (age >= 40) {
                    item.userAge = '40대';
                } else if (age >= 30) {
                    item.userAge = '30대';
                } else if (age >= 20) {
                    item.userAge = '20대';
                } else {
                    item.userAge = '10대';
                }

                item.userLevel = user.level;
                item.userGender = user.gender;

                if (type === 'qna') {
                    userDetails.value = user;
                }
            })
    };

    const getRoutine = function (qna) {
        axios.get(`${ROUTINE_REST_API}detail`, {params: { routineId: qna.routineId }})
        .then((response) => {
            qna.routine = response.data;
        })
    }

    const getAnsList = function (questionId) {
        axios.get(ANS_REST_API, {params: {questionId: questionId}})
        .then((response) => {
            ansList.value = response.data;
            ansList.value.forEach((ans) => {
                ans.userAge = 0;
                ans.userLevel = 1;
                ans.userGender = 'NONE';

                getUserDetails(ans, 'ans');
            })
        })
    }


    return { 
        qnaList,
        ansList,
        userDetails,
        getQnAList,
        getUserDetails,
        getRoutine,
        getAnsList
    };
})
