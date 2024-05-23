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
    <button @click="modifyExTag">수정 완료</button>
</template>

<script setup>

</script>

<style scoped>
.img-profile {
    width: 100px;
}


</style>