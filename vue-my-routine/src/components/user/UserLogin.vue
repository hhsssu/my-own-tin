<template>
  <div class="login-container">
    <input
      type="text"
      placeholder="e-mail"
      v-model.trim="id"
      class="login-input"
    />
    <input
      type="password"
      placeholder="password"
      v-model.trim="pw"
      class="login-input"
    />
    <button @click="login">로그인</button>
    <button @click="join">회원가입</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

const store = useUserStore();

const id = ref("");
const pw = ref("");

const login = function () {
  console.log("login");
};
const join = function () {
  console.log("join");
};
</script>

<style scoped>
.login-container {
    width: 1000px;
    margin: 0 auto;
    background: #adf;
}
.login-input {
    display: block;
}
</style>