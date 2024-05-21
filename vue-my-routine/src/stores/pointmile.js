import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_POINT_API = `http://localhost:8080/myroutine/point/`;
const REST_MILE_API = `http://localhost:8080/myroutine/mile/`;

// 포인트 등록(적립), 내역 조회, 총합, 삭제 (CRD)
// 마일리지 등록(적립), 내역 조회, 총합, 삭제 (CRD)
export const usePointmileStore = defineStore('pointmile', () => {

    const pointList = ref([]);
    const pointTotal = ref(null);
    const mileList = ref([]);
    const mileTotal = ref(null);

    // 포인트 등록(적립)
    const createPoint = (point) => {
        // 포인트 점수, 내역, 적립받을 유저 담아서 보냄
        axios({ 
            url: REST_POINT_API,
            method: 'POST',
            data: point
        })
        .then(() => {
            console.log("포인트 적립 완료");
        })
        .catch((error) => {
            console.log(error);
        })
    }

    // 포인트 내역 조회
    const getPointList = function(userId) {
        axios.get(REST_POINT_API, {
            params: {
                userId: userId,
            }
        })
        .then((response) => {
            pointList.value = response.data;
        })
    }

    // 포인트 총합 조회
    const getPointTotal = function(userId) {
        axios.get(`${REST_POINT_API}total`, {
            params: {
                userId: userId,
            }
        })
        .then((response) => {
            pointTotal.value = response.data;
        })
    }

    //////////////////////// Mileage 마일리지 //////////////////////////////
    
    // 마일리지 등록(적립)
    const createMile = (mile) => {
        // 포인트 점수, 내역, 적립받을 유저 담아서 보냄
        axios({ 
            url: REST_MILE_API,
            method: 'POST',
            data: mile
        })
        .then(() => {
            console.log("마일리지 적립 완료");
        })
        .catch((error) => {
            console.log(error);
        })
    }
    
    // 마일리지 내역 조회
    const getMileList = function(userId) {
        axios.get(REST_MILE_API, {
            params: {
                userId: userId,
            }
        })
        .then((response) => {
            pointList.value = response.data;
        })
    }

    // 마일리지 총합 조회
    const getMileTotal = function(userId) {
        axios.get(`${REST_MILE_API}total`, {
            params: {
                userId: userId,
            }
        })
        .then((response) => {
            mileTotal.value = response.data;
        })
    }
    
    
    return {
        pointList,
        pointTotal,
        mileList,
        mileTotal,
        createPoint,
        createMile,
        getPointList,
        getMileList,
        getPointTotal,
        getMileTotal,

    }
})