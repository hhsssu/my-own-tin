<template>
  <div class="join-container">
    <!-- 추후 로고 들어갈 예정 -->
    <div><h1>회원가입</h1></div>
    <div class="join-form">
      <form @submit.prevent="confirmJoin">
        <div class="join-form-group">
          <label for="email">아이디(이메일)</label>
          <input type="email" id="email" v-model="email" required />
          <button @click="checkEmail">중복 확인</button>
        </div>
        <div class="join-form-group">
          <label for="nickname">닉네임</label>
          <input type="text" id="nickname" v-model="nickname" required />
          <button @click="checkNickname">중복 확인</button>
        </div>
        <div class="join-form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="password" required />
        </div>
        <div class="join-form-group">
          <label for="confirmPassword">비밀번호 확인</label>
          <input
            type="password"
            id="confirmPassword"
            v-model="confirmPassword"
            required
          />
          <button @click="verifyPassword">확인</button>
        </div>
        <div class="join-form-group">
          <label for="gender">성별</label>
          <input type="checkbox" id="male" value="male" v-model="gender" />
          <label class="join-gender-check" for="male">남성</label>
          <input type="checkbox" id="female" value="female" v-model="gender" />
          <label class="join-gender-check" for="female">여성</label>
        </div>
        <div class="join-form-group">
          <label for="age">나이</label>
          <input type="number" id="age" v-model="age" required />
        </div>
      </form>
    </div>
    <div class="join-buttons">
      <button @click="confirmJoin">등록</button>
      <button @click="deleteJoin">취소</button>
    </div>
  </div>
</template>

<script setup>
// 회원가입 확인
const confirmJoin = function () {
  console.log("confirm");
};

// 회원가입 취소
const deleteJoin = function () {
  console.log("delete");
};

// 이메일 중복 확인
const checkEmail = function () {};

// 닉네임 중복 확인
const checkNickname = function () {};

// 비밀번호 확인
const verifyPassword = function () {};
</script>

<style scoped>
.join-container {
  /* background: lightblue; */
  padding: 20px;
  border-radius: 8px;
}

.join-form {
  margin-bottom: 20px;
}

.join-form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.join-form-group label {
  width: 120px; /* 고정된 너비를 설정 */
  text-align: right; /* 목업과 같이 label을 오른쪽 정렬 */
  margin-right: 10px;
}

.join-gender-check {
    text-align: left !important;
}

.join-form-group input[type="text"],
.join-form-group input[type="email"],
.join-form-group input[type="password"],
.join-form-group input[type="number"] {
  /* flex: 1; 입력 필드를 가능한 최대 너비로 설정 */
  width: 200px;
  padding: 5px;
}

.join-form-group input[type="checkbox"] {
    margin-right: 5px;
}

.join-form-group button {
  margin-left: 10px;
}

.join-buttons {
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
