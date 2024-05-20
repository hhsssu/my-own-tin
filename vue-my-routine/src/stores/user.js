import { ref, computed } from 'vue'
import { defineStore } from 'pinia'
import axios from 'axios';
import router from '@/router';

const REST_USER_API = `http://localhost:8080//myroutine/user`

// Login, Join, 마이페이지 회원정보 불러오기
export const useUserStore = defineStore('user', () => {

    const loginUserId = ref(null);

    // 로그인
    const userLogin = function (id, password) {
        axios.post(`${REST_USER_API}/login`, {
            id: id,
            password: password
        }) // 성공
            .then((res) => {
                sessionStorage.setItem('access-token', res.data["access-token"])

                const token = res.data['access-token'].split('.');
                let id = JSON.parse(atob(token[1]))['id'];

                loginUserId.value = id;

                router.push({ name: 'routine' });
            })
            .catch((error) => {
                console.log(error);
            })
    }

    // 회원가입
    const userJoin = function (user) {
        axios({
            url: REST_USER_API,
            method: 'POST',
            data: user
        })
            .then(() => {
                router.push({ name: 'login' })
            })
            .catch((error) => {
                console.log(error);
            })
    }

    // 회원 정보 표시
    const loginUser = ref({});
    const getLoginUser = function () {
        axios.get(`${REST_USER_API}/`)
            .then((response) => {
                loginUser.value = response.data
            })
    }

    // 회원 정보 수정
    const updateLoginUser = function () {
        axios.put(REST_USER_API, loginUser.value)
            .then(() => {
                router.push({ name: 'mypage' })
            })
    }

    return {
        loginUserId,
        userLogin,
        loginUser,
        userJoin,
        getLoginUser,
        updateLoginUser,
    }
})
