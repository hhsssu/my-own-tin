import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
import router from '@/router';
import { errorMessages } from '@vue/compiler-core';

const QNA_REST_API = `http://localhost:8080/myroutine/que/`;
const ANS_REST_API = `http://localhost:8080/myroutine/ans/`;
const USER_REST_API = `http://localhost:8080/myroutine/user/`;
const ROUTINE_REST_API = `http://localhost:8080/myroutine/routine/`;

// 질문 답변 CRUD
export const useQnAStore = defineStore('qna', () => {

    const qnaList = ref([]);
    const ansList = ref([]);
    const userDetails = ref({});
    const question = ref({});

    const getQnAList = (searchCondition) => {
        axios.get(QNA_REST_API, { params: searchCondition })
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

    const getQuestion = function (questionId) {
        return axios.get(`${QNA_REST_API}${questionId}`)
            .then((response) => {
                question.value = response.data;
                question.value.routine = null;
                getUserDetails(question.value, 'question');
                getRoutine(question.value);
                return question.value;
            })
            .catch((error) => {
                console.log(error);
                throw error;
            })
    }

    const getUserDetails = (item, type) => {
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

    const getNickname = (item, type) => {
        axios.get(`${USER_REST_API}${item.userId}`)
            .then((response) => {
                const user = response.data;
                const nickname = user.nickname;

                item.writer = nickname;
            })
    }

    const getRoutine = function (qna) {
        axios.get(`${ROUTINE_REST_API}detail`, { params: { routineId: qna.routineId } })
            .then((response) => {
                qna.routine = response.data;
                // console.log(qna.routine);
            })
    }

    const getAnsList = function (questionId) {
        return axios.get(ANS_REST_API, { params: { questionId: questionId } })
            .then((response) => {
                ansList.value = response.data;
                ansList.value.forEach((ans) => {
                    ans.userAge = 0;
                    ans.userLevel = 1;
                    ans.userGender = 'NONE';

                    getUserDetails(ans, 'ans');
                    return ansList.value;
                })
            })
    }

    const createQuestion = async function (question) {
        try {
            // getNickname을 기다려서 writer 설정
            await axios.get(`${USER_REST_API}${question.userId}`)
                .then((response) => {
                    const user = response.data;
                    question.writer = user.nickname;
                    // question.routineId = selectedRoutine.id;
                });

            console.log(question);
            // 질문 등록 과정
            await axios({
                url: QNA_REST_API,
                method: 'POST',
                data: question
            });

            router.go({ name: 'qnaList' });
        } catch (error) {
            console.error(error);
        }
    };

    const createAnswer = async function (answer) {
        try {
            // getNickname을 기다려서 writer 설정
            await axios.get(`${USER_REST_API}${answer.userId}`)
                .then((response) => {
                    const user = response.data;
                    answer.writer = user.nickname;
                });

            // 댓글 등록 과정
            await axios({
                url: ANS_REST_API,
                method: 'POST',
                data: answer
            });

            router.go({ name: 'answerList', params: { questionId: answer.questionId } });
        } catch (error) {
            console.error(error);
        }
    }

    const updateQuestion = function (questionId) {
        axios.put(`${QNA_REST_API}?id=${questionId}`, question.value)
        .then(() => {
            router.go({ name: 'qnaDetail', params: {questionId: questionId} });
        })
    }

    const updateAnswer = function (answer) {
        console.log(answer);
        // answer.isPicked = 1;
        axios.put(`${ANS_REST_API}?id=${answer.id}`, answer)
        .then(() => {
            console.log(answer.isPicked);
            // router.go({ name: 'qnaList' });
        })
    }

    const searchQuestionList = function (searchCondition) {
        return axios.get(`${QNA_REST_API}`, {params: {
            level: searchCondition.level,
            gender: searchCondition.gender,
            ageRange: searchCondition.ageRange,
            orderBy: searchCondition.orderBy,
            word: searchCondition.word
        }})
        .then((response) => {
            qnaList.value = response.data;
            qnaList.value.forEach((qna) => {
                getUserDetails(qna, 'qna');
                getRoutine(qna);
            });

            return qnaList.value;
        })
        .catch((error) => {
            console.log(error);
            throw error;
        })
    }


    return {
        question,
        qnaList,
        ansList,
        userDetails,
        getQnAList,
        getQuestion,
        getUserDetails,
        getNickname,
        getRoutine,
        getAnsList,
        createQuestion,
        createAnswer,
        updateQuestion,
        updateAnswer,
        searchQuestionList
    };
})
