<template>
    <div>
        <h2>프로필 수정</h2>
    </div>

    <!-- 프로필 이미지 선택 -->
    <div>
        <img src="@/assets/img/profile_female.png" alt="프로필이미지1" class="img-profile">
        <img src="@/assets/img/profile_male.png" alt="프로필이미지2" class="img-profile">
        <img src="@/assets/img/profile_gentlelady.png" alt="프로필이미지3" class="img-profile">
        <img src="@/assets/img/profile_gentleman.png" alt="프로필이미지4" class="img-profile">
    </div>

    <!-- 닉네임 수정 -->
    <div>
        <input type="text" v-model="nickname">
    </div>

    <!-- 나의 성격 태그 수정 -->
    <div>
        <input type="text" v-model="character1">
        <input type="text" v-model="character2">
        <input type="text" v-model="character3">
    </div>
    
    <!-- 성격 태그 추천받기 - ChatGPT -->
    <div>{{ promptmsg }}</div>
    
    <button @click="modifyExTag">수정 완료</button>
</template>

<script setup>
import { ref } from 'vue';

const promptmsg = ref("");
// PromiseResult
const hint = () => {
  promptmsg.value = OpenApiUtil.prompt("성격 추천해줘")
  .then((res) => {
    promptmsg.value = res;
  });
};
</script>

<style scoped>
.img-profile {
    width: 100px;
}

</style>
