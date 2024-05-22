import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_USER_API = `http://localhost:8080/myroutine/user/`;

// Login, Join, 마이페이지 회원정보 불러오기
export const useUserStore = defineStore('user', () => {

    const user = ref(null);
    const loginUser = ref(null);

    // 로그인
    const userLogin = (loginUser) => {
        // 1. 로그인 요청
        const userToSend = {
            email: loginUser.email,
            password: loginUser.password
        }
        axios.post(`${REST_USER_API}login`, userToSend)
            .then((response) => {
                if (response.data) {
                    // 2. 데이터 받음 (응답 처리)
                    user.value = response.data;
                    // 3. 세션 스토리지 업데이트
                    sessionStorage.setItem('user', JSON.stringify(response.data));
                    alert('로그인 되었습니다.');
                    // 4. 로그인 완료되면 루틴페이지로 이동
                    router.replace({ name: 'routine' });
                } else {
                    // 사용자 정보가 없으면 오류 처리
                    alert(response.data.message || '로그인 실패: 잘못된 아이디 혹은 비밀번호입니다.');
                }
            })
            .catch((error) => {
                console.log(loginUser);
                if (error.response && error.response.status === 400) {
                    alert('아이디나 비밀번호를 확인해주세요');
                } else {
                    // console.log(error.response.status);
                    alert('서버 오류가 발생했습니다. 나중에 다시 시도하세요.');
                }
                console.log(error);
            });
    }

    // 로그아웃
    const logoutUser = function () {
        axios.delete(`${REST_USER_API}logout`)
        .then(response => {
            // 로그아웃 시 현재 로그인한 사용자 정보 삭제
            user.value = null;
            sessionStorage.removeItem('user');
            // 로그아웃 전으로 재진입 불가
            router.replace({ name: 'main' });
            // 서버로부터 받은 응답 로그
            console.log(response.data); 
        })
        .catch(error => {
            console.error('Error logging out:', error);
        });
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
    // const getLoginUser = function () {
    //     axios.get(REST_USER_API)
    //         .then((response) => {
    //             console.log(response.data);
    //             user.value = response.data
    //         })
    //         .catch((error) => {
    //             console.log(error);
    //         });
    // };

    const getLoginUser = function () {
        const login = sessionStorage.getItem('user');
        loginUser.value = JSON.parse(login);
    }

    // 회원 정보 수정
    const updateLoginUser = function (updatedUserInfo) {
        // const userId = updatedUserInfo.id;
        axios.put(`${REST_USER_API}?id=${updatedUserInfo.id}`, updatedUserInfo)
            .then(() => {
                console.log(updatedUserInfo);
                updateSessionInfo(updatedUserInfo);
                alert("수정이 완료되었습니다!");
                router.replace({ name: 'mypage' });
            }).catch(error => {
                console.error("Error updating user:", error);
            });
    }

    // 회원 탈퇴
    const deleteUser = function () {
        axios.put(`${REST_USER_API}delete`)
            .then(() => {
                // 회원 탈퇴 성공 시 로그아웃을 수행하거나 다른 처리를 수행
                logoutUser();
            })
            .catch((error) => {
                console.log(error);
            });
    }

    // 회원 정보 수정 후 세션 정보 업데이트
    const updateSessionInfo = function (updatedUserInfo) {
        const sessionUserInfo = JSON.parse(sessionStorage.getItem('user'));
        if (sessionUserInfo) {
            // 세션에서 사용자 정보 업데이트
            const updatedSessionUserInfo = { ...sessionUserInfo, ...updatedUserInfo };
            sessionStorage.setItem('user', JSON.stringify(updatedSessionUserInfo));
        }
    }

    // 중복검사 결과 받을 변수
    const result = ref(false);
    // 이메일 중복 검사
    const checkEmail = function (email) {
        axios.get(REST_USER_API, email)
        .then((response) => {
            result.value = response.data;
            console.log("이메일 중복검사 완료");
        })
        .catch((error) => {
            console.log(error + "이메일 중복");
        })
    }

    const checkNickname = function (nickname) {
        axios.get(REST_USER_API, nickname)
        .then((response) => {
            result.value = response.data;
            console.log("닉네임 중복검사 완료");
        })
        .catch((error) => {
            console.log(error + "닉네임 중복");
        })
    }


    return {
        user,
        loginUser,
        userLogin,
        userJoin,
        getLoginUser,
        updateLoginUser,
        logoutUser,
        deleteUser,
        updateSessionInfo,
        checkEmail,
        checkNickname,
    }
})