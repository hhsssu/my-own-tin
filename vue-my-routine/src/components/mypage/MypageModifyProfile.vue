<template>
  <div>
    <h2>프로필 수정</h2>
  </div>

  <!-- 프로필 이미지 선택 -->
  <div>
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
  </div>

  <!-- 닉네임 수정 -->
  <div>
    <input type="text" v-model="nickname" />
  </div>

  <!-- 나의 성격 태그 수정 -->
  <div>
    <input type="text" v-model="character1" />
    <input type="text" v-model="character2" />
    <input type="text" v-model="character3" />
  </div>

  <!-- 성격 태그 추천받기 - ChatGPT -->
  <button @click="getCharacterTags">성격 태그 추천받기</button>
  <div v-html="promptmsg"></div>

  <button @click="modifyExTag">수정 완료</button>
</template>

<script setup>
import { ref } from "vue";
import { OpenApiUtil } from "/src/assets/js/OpenApiUtil.js"; // 경로를 실제 파일 위치로 수정하세요.

const promptmsg = ref("");
const nickname = ref("");
const character1 = ref("");
const character2 = ref("");
const character3 = ref("");

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
  // 수정 완료 로직 추가예정
  console.log("수정 완료 버튼 클릭됨");
};
</script>

<style scoped>
.img-profile {
  width: 100px;
}
</style>
