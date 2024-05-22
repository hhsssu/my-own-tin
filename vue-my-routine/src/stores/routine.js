import { ref, computed, toRaw } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { useUserStore } from './user';

// axios 기본 설정
axios.defaults.baseURL = 'http://localhost:8080';
axios.defaults.withCredentials = true;

const REST_ROUTINE_API = `http://localhost:8080/myroutine/routine`;
const REST_USER_API = `http://localhost:8080/myroutine/user`;

// 루틴 불러오기, 등록, 수정, 삭제 (CRUD)
// routine 페이지, 검색 페이지
export const useRoutineStore = defineStore('routine', () => {
  // 루틴 리스트 담을 변수
  const routineList = ref([]);
  const userStore = useUserStore();


  // 루틴 등록 (직접 등록, 다른 사람의 루틴을 스크랩하여 등록)
  const createRoutine = function (routine) {
    axios({
      url: `${REST_ROUTINE_API}/`,
      method: 'POST',
      data: routine
    })
      .then(() => {
        router.push({ name: 'routine' })
      })
      .catch((error) => {
        console.log(error);
      })
  }


  // 루틴 목록 불러오기 (내 루틴)
  const getRoutineList = function (userId) {
    axios.get(`${REST_ROUTINE_API}/mine`, {
      params: {
        userId: userId,
      }
    })
      .then((response) => {
        routineList.value = response.data;
        routineList.value.forEach((routine) => {
          getUserDetails(routine, 'routine');
        })
      })
  }

  // 루틴 보관함 불러오기
  const getMarkedList = function (userId) {
    axios.get(`${REST_ROUTINE_API}/marked`, {
      params: {
        userId: userId,
      }
    })
    .then((response) => {
      routineList.value = response.data;
        routineList.value.forEach((routine) => {
          getUserDetails(routine, 'routine');
        })
    })
    .catch((error) => {
        console.log(error);
      })
  }


  // 루틴 하나 담을 변수 (상세보기에서 사용)
  const routine = ref({});

  // 루틴 상세보기
  const getRoutine = function (id) {
    return axios.get(`${REST_ROUTINE_API}/detail`, {
      params: {
        routineId: id,
      }
    })
      .then((response) => {
        routine.value = response.data;
        getUserDetails(routine.value, 'routine');
        return routine.value;
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  }

  // 루틴 검색 (전체 루틴)
  const searchRoutineList = function (searchCondition) {

    return axios.get(`${REST_ROUTINE_API}/search`, {params: {
      level: searchCondition.level,
      gender: searchCondition.gender,
      ageRange: searchCondition.ageRange,
      orderBy: searchCondition.orderBy
    }})
      .then((response) => {
        routineList.value = response.data;
        routineList.value.forEach((routine) => {
          getUserDetails(routine, 'routine');
        });

        return routineList.value;
      })
      .catch((error) => {
        console.log(error);
        throw error;
      });
  };


  // 루틴 수정
  const updateRoutine = function (routineId) {
    axios.put(`${REST_ROUTINE_API}/?id=${routineId}`, routine.value)
      .then(() => {
        router.push({ name: 'routine' })
      })
      .catch((error) => {
        console.log(error)
      })
  }

  // 루틴 삭제 (delete_at update)
  const deleteRoutine = function (id) {
    axios.put(`${REST_ROUTINE_API}/delete`, null, {
      params: {
        id: id,
      }
    })
      .then(() => {
        router.push({ name: 'routine' })
      })
      .catch((error) => {
        console.log(error)
      })
  }

  // 사용자 정보 불러오기
  const getUserDetails = (item, type) => {
    axios.get(`${REST_USER_API}/${item.userId}`)
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
      })
  };


  return {
    createRoutine,
    routineList,
    routine,
    getRoutineList,
    getMarkedList,
    getRoutine,
    searchRoutineList,
    updateRoutine,
    deleteRoutine,
    getUserDetails
  }
})
