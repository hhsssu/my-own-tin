import { ref, computed } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import router from '@/router';

const REST_USER_API = `http://localhost:8080//myroutine/user`;

// Login, Join, 마이페이지 회원정보 불러오기
export const useUserStore = defineStore('user', () => {

    const loginUser = ref(null);

    // 로그인
    // const user = ({ref});
    // const router = useRouter();
  
    // const signInUser = function(loginUser){
    //   const userToSend = {
    //       id: loginUser.id,
    //       password: loginUser.password
    //   };
  
    //   axios.post(REST_API + '/login', userToSend)
    //   .then((response) => { 
    //       if(response.data){ 
    //           alert('로그인 성공');
    //           console.log(response.data);
    //           sessionStorage.setItem('user', JSON.stringify(response.data));
    //           user.value = response.data;
    //           router.push({ name: 'main'}).then(() => {
    //               location.reload();
    //           });
    //       }
    //   })
    //   .catch((error) => {
    //       console.log(loginUser);
    //       alert('잘못된 아이디 혹은 비밀번호입니다.');
    //       console.log(error);
    //   });
    // }


    // 로그인
    const userLogin = function (id, password) {
        axios.post(`${REST_USER_API}/login`, {
            id: id,
            password: password
        })
        .then((response) => {
            // 성공적으로 로그인했을 때 받아온 사용자 정보를 저장
            loginUser.value = response.data;
            // 로그인 성공 후 페이지 이동
            router.push({ name: 'routine' });
        })
        .catch((error) => {
            console.log(error);
        });
    }

    // 로그아웃
    const logoutUser = function () {
        // 로그아웃 시 현재 로그인한 사용자 정보 삭제
        loginUser.value = null;
        // 로그아웃 후 로그인 페이지로 이동
        router.push({ name: 'login' });
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
    const getLoginUser = function () {
        axios.get('REST_USER_API')
            .then((response) => {
                loginUser.value = response.data
            })
            .catch((error) => {
                console.log(error);
            });
    }

    // 회원 정보 수정
    const updateLoginUser = function (updatedUserInfo) {
        axios.put(REST_USER_API, updatedUserInfo)
            .then(() => {
                router.push({ name: 'mypage' })
            })
    }

    // 회원 탈퇴
    const deleteUser = function () {
        axios.put(`${REST_USER_API}/delete`)
        .then(() => {
            // 회원 탈퇴 성공 시 로그아웃을 수행하거나 다른 처리를 수행
            logoutUser();
        })
        .catch((error) => {
            console.log(error);
        });
    }

    return {
        loginUserInfo,
        userLogin,
        loginUser,
        userJoin,
        getLoginUser,
        updateLoginUser,
        logoutUser,
        deleteUser,
    }
})