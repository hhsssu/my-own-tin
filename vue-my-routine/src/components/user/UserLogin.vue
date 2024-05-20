<template>
  <div class="login-container">
    <div class="login-logo">
      <img src="@/assets/img/logo1.png" alt="메인페이지 로고" class="img-login-logo">
    </div>
    <div class="login-input">
      <input type="text" placeholder="e-mail" v-model.trim="id" />
      <input type="password" placeholder="password" v-model.trim="pw" />
    </div>
    <div class="login-buttons">
      <button @click="login">로그인</button>
      <button @click="join">회원가입</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";


const store = useUserStore();
const router = useRouter();

const id = ref("");
const pw = ref("");

const login = function () {
  console.log("login");
};
const join = function () {
  console.log("join");
  router.push({name: 'join'})
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
  width: 200px;
  padding: 10px;
  margin: 20px 10px;
  font-size: 18px;
  color: white;
  background-color: #ffa101;
  border: 1px solid transparent;
  border-radius: 5px;
}

/* 버튼에 마우스 커서 올렸을 시 */
.login-buttons button:hover {
  background-color: lightgray;
}

/* 버튼 클릭 시 */
.login-buttons button:active {
  border: 2px solid #31525B;
}
</style>
