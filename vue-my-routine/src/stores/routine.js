import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios'
import router from '@/router'
import { useUserStore } from './user';

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
      url: REST_ROUTINE_API,
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
  const getRoutineList = function(userId) {
    axios.get(`${REST_ROUTINE_API}/mine`, {
      params: {
        userId: userId,
      }
    })
    .then((response) => {
      routineList.value = response.data;
    })
  }
  
  // 루틴 보관함 불러오기
  const getMarkedList = function(userId) {
    axios.get(`${REST_ROUTINE_API}/marked`, {
      params: {
        userId: userId,
      }
    })
    .then((response) => {
      routineList.value = response.data
    })
  }
  
  
  // 루틴 하나 담을 변수 (상세보기에서 사용)
  const routine = ref({});
  
  // 루틴 상세보기
  const getRoutine = function(id) {
    axios.get(`${REST_ROUTINE_API}/detail`, {
      params: {
        id: id,
      }
    })
    .then((response) => {
      routine.value = response.data
    })
  }
  
  // 루틴 검색 (전체 루틴)
  const searchRoutineList = function(con) {
    axios.get(REST_ROUTINE_API, {
      params: con,
    })
    .then((response) => {
      routineList.value = response.con
    })
  }
  
  // 루틴 수정
  const updateRoutine = function(id, updatedRoutine) {
    axios.put(REST_ROUTINE_API, updatedRoutine, {
      params: {
        id: id,
      }
    })
    .then(() => {
      router.push({name: 'routine'})
    })
    .catch((error) => {
      console.log(error)
    })
  }

  // 루틴 삭제 (delete_at update)
  const deleteRoutine = function(id) {
    axios.put(`${REST_ROUTINE_API}/delete`, null, {
      params: {
        id: id,
      }
    })
    .then(() => {
      router.push({name: 'routine'})
    })
    .catch((error) => {
      console.log(error)
    })
  }
  

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
  }
})
