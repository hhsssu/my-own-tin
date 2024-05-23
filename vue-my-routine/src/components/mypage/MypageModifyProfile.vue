<template>
  <div class="modify-container">
    <h2 class="view-title">프로필 수정</h2>

    <!-- 프로필 이미지 선택 -->
    <!-- <div>
      <img src="@/assets/img/profile_female.png" alt="프로필이미지1" class="img-profile" />
      <img src="@/assets/img/profile_male.png" alt="프로필이미지2" class="img-profile" />
      <img
      src="@/assets/img/profile_gentlelady.png"
      alt="프로필이미지3"
      class="img-profile"
      />
      <img
      src="@/assets/img/profile_gentleman.png"
      alt="프로필이미지4"
      class="img-profile"
      />
    </div> -->

    <!-- 닉네임 수정 -->
    <!-- <div>
      <input type="text" v-model="nickname" />
    </div> -->

    <!-- 나의 성격 태그 수정 -->
    <div class="profile-title">나의 성격 태그</div>
    <div class="flex-box">
      <label for="character1">키워드 1</label>
      <input type="text" name="character1" id="character1" v-model="character1" />
      <label for="character2">키워드 2</label>
      <input type="text" name="character2" id="character2" v-model="character2" />
      <label for="character3">키워드 3</label>
      <input type="text" name="character3" id="character3" v-model="character3" />
    </div>

    <!-- 성격 태그 추천받기 - ChatGPT -->
    <button @click="getCharacterTags" class="profile-btn" style="font-size: 1rem; background-color: #FFA101; margin: 20px 0 10px; padding: 5px;">성격 태그 추천받기</button>
    <div v-html="promptmsg" class="ai-container"></div>

    <button @click="modifyExTag" class="profile-btn">수정 완료</button>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";

import { OpenApiUtil } from "/src/assets/js/OpenApiUtil.js"; // 경로를 실제 파일 위치로 수정하세요.

const userStore = useUserStore();

const user = JSON.parse(sessionStorage.getItem("user"));

const promptmsg = ref("");
// const nickname = ref("");
const character1 = ref(user.character1);
const character2 = ref(user.character2);
const character3 = ref(user.character3);

// 성격 태그 추천 함수
const getCharacterTags = async () => {
  try {
    const response = await OpenApiUtil.prompt("운동과 관련된 성격 20가지 정도만 뽑아서 태그로 달아둘 것 추천해줘. 줄바꿈도 해줘.");
    // 줄바꿈 문자를 <br> 태그로 변환
    promptmsg.value = response.replace(/\n/g, '<br>');
  } catch (error) {
    console.error(error);
    promptmsg.value = "오류가 발생했습니다. 다시 시도해주세요.";
  }
};

// 수정 완료 함수 (필요에 따라 구현)
const modifyExTag = () => {
  console.log("수정 완료 버튼 클릭됨");
  const updatedUserProfile = {
    id: user.id,
    character1: character1.value,
    character2: character2.value,
    character3: character3.value,
  };

  userStore.updateLoginUser(updatedUserProfile);
};
</script>

<style scoped>
.img-profile {
  width: 100px;
}

.profile-title {
  font-weight: 700;
  margin-bottom: 15px;
  margin-right: 15px;
}

.modify-container {
  padding: 0 20px;
}

.flex-box {
  flex-direction: column;
  align-items: start;
}

.flex-box label {
  margin: 10px 0 8px;
}

.flex-box input {
  padding: 10px 5px;
  border: none;
  border-bottom: 1px solid black;
}

.profile-btn {
  display: block;
  margin: 50px auto;
  text-align: center;
  border: none;
  background-color: #31525B;
  color: #fff;
  padding: 10px;
  border-radius: 5px;
  font-weight: 600;
  font-size: 1.2rem;
}

.ai-container {
  border: 1px solid #777; 
  color: #555; 
  padding: 15px; 
  line-height: 1.5rem; 
  border-radius: 5px; 
  margin: 0 auto;
}
</style>
