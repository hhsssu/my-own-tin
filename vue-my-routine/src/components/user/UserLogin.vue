<template>
  <div class="login-container">
    <div class="login-logo">
      <img src="@/assets/img/logo1.png" alt="메인페이지 로고" class="img-login-logo">
    </div>
    <div class="login-input">
      <input type="email" placeholder="e-mail" v-model.trim="email" />
      <input type="password" placeholder="password" v-model.trim="password" @keyup.enter="login" />
    </div>
    <div class="login-buttons">
      <button @click="login">로그인</button>
    </div>
    <button @click="joinpage" class="join-btn">회원가입</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";


const store = useUserStore();
const router = useRouter();

const email = ref("");
const password = ref("");

// 비동기 함수를 선언을 위한 async 키워드 사용
const login = async function () {
  // 로그인 요청 보내기
  try {
    store.userLogin({email: email.value, password: password.value});
    
  } catch (error) {
    console.log(error);
  }
};

const joinpage = function () {
  console.log("회원가입 페이지 이동");
  router.push({name: 'join'});
};
</script>

<style scoped>
.login-container {
  width: 1000px;
  margin: 200px auto;
  text-align: center;
  /* background: #adf; */
}

.img-login-logo {
  width: 150px;
}

.login-input {
  display: flex;
  flex-direction: column;
  align-items: center;
}

.login-input input {
  width: 400px;
  padding: 10px;
  margin: 30px 0px;
  font-size: 18px;
  border: transparent;
  border-bottom: 2px solid lightgray;
}

/* input 태그 클릭 시 */
.login-input input:focus {
  outline: none;
}

.login-buttons button {
  width: 300px;
  padding: 10px;
  margin: 20px 10px;
  font-size: 18px;
  color: white;
  background-color: #ffa101;
  border: 1px solid transparent;
  border-radius: 5px;
}

.join-btn {
  border: none;
  background: none;
  cursor: pointer;
  color: #777;
}

/* 버튼에 마우스 커서 올렸을 시 */
.login-buttons button:hover {
  background-color: #B3DEE5;
  cursor: pointer;
}

/* 버튼 클릭 시 */
.login-buttons button:active {
  border: 2px solid #31525B;
}
</style>
