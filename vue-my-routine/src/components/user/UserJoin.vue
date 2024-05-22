<template>
  <div class="join-container">
    <!-- 추후 로고 들어갈 예정 -->
    <div><h1>회원가입</h1></div>
    <div class="join-form">
      <form @submit.prevent="confirmJoin">
        <div class="join-form-group">
          <label for="email">아이디(이메일)</label>
          <input type="email" id="email" v-model="user.email" required />
          <button @click="checkEmail">중복 확인</button>
        </div>
        <div class="join-form-group">
          <label for="nickname">닉네임</label>
          <input type="text" id="nickname" v-model="user.nickname" placeholder="한글 기준 10자 이내로 작성해주세요" required />
          <button @click="checkNickname">중복 확인</button>
        </div>
        <div class="join-form-group">
          <label for="password">비밀번호</label>
          <input type="password" id="password" v-model="user.password" required />
        </div>
        <div class="join-form-group">
          <label for="confirmPassword">비밀번호 확인</label>
          <input type="password" id="confirmPassword" v-model="confirmPassword" required />
          <button @click="verifyPassword">확인</button>
        </div>
        <div class="join-form-group">
          <div class="input-label">성별</div>
          <label for="gender">
            <input class="input-radio" type="radio" name="gender" v-model="user.gender" value="남성" />
            <span>남성</span>
          </label>
          <label for="gender">
            <input class="input-radio" type="radio" name="gender" v-model="user.gender" value="여성"/>
            <span>여성</span>
          </label>
        </div>
        <div class="join-form-group">
          <label for="age">나이</label>
          <input type="number" id="age" v-model="user.age" required />
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
import { useUserStore } from '@/stores/user';
import { useRouter } from "vue-router";
import { ref } from 'vue';

const router = useRouter();
const userStore = useUserStore();


const user = {
  id: '',
  email: '',
  nickname: '',
  password: '',
  gender: '',
  age: '',
  level: '',
  isPublic: '', // TINYINT DEFAULT 값에 대해 알아보기..
  isAdmin: '',
  isExpert: '',
  joinAt: '',
  updateAt: '',
  deleteAt: '',
  part1: '',
  part2: '',
  workoutTime: '',
  // profileUrl: '',
  // 성격 1, 2, 3
}

// 회원가입 확인
const confirmJoin = function () {
  if(!user.email || !user.password || !confirmPassword.value || !user.gender || !user.age) {
    alert("빈칸을 채워주세요!");
    return;
  }

  if(user.password !== confirmPassword.value) {
    alert("입력하신 비밀번호가 다릅니다. 다시 입력해주세요.")
    return;
  }

  console.log(user.isPublic);
  userStore.userJoin(user);
  alert("가입 완료!");
};

// 회원가입 취소
const deleteJoin = function () {
  router.push({name: 'main'});

};

// 이메일 중복 확인
const checkEmail = function () {
  if(!user.email) {
    alert("이메일을 입력하세요.");
    return;
  }
  userStore.checkEmail(user.email);
    if(userStore.result) { // 중복일 때
      alert("이미 있는 이메일입니다.");
    } else {
      alert("사용 가능한 이메일입니다.");
    }
};

// 닉네임 중복 확인
const checkNickname = function () {
  if(!user.nickname) {
    alert("닉네임을 입력하세요.");
    return;
  }
  userStore.checkNickname(user.nickname);
  if(userStore.result) { // 중복일 때
    alert("이미 있는 닉네임입니다.");
  } else {
    alert("사용 가능한 닉네임입니다.");
  }
};

// 비밀번호 확인
const verifyPassword = function () {
  if(!user.password || !confirmPassword.value) {
    alert("비밀번호를 입력하세요.");
    return;
  }
  if(user.password !== confirmPassword.value) {
    alert("입력하신 비밀번호가 다릅니다. 다시 입력해주세요.")
    return;
  }
  alert("확인 완료!")
};

</script>

<style scoped>
.join-container {
  /* background: lightblue; */
  padding: 20px;
  border-radius: 8px;
  width: 1000px;
}

.join-form {
  margin-bottom: 20px;
  width: 1000px;
  margin-left: 500px;
}

.join-form form {
  width: 100%;
  text-align: center;
}

.join-form-group {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.join-form-group label {
  width: 120px; /* 고정된 너비를 설정 */
  text-align: right; /* 목업과 같이 label을 오른쪽 정렬 */
  margin-right: 15px;
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
  height: 30px;
  padding: 5px;
  border: none;
  border-bottom: 1px solid #000;
  font-size: 1em;
}

.join-form-group input[type="checkbox"] {
  margin-right: 5px;
}

.join-form-group button {
  margin-left: 15px;
}

.join-buttons {
  display: flex;
  gap: 10px;
  justify-content: center;
}
</style>
