<template>
    <div>
        <h2>프로필 수정</h2>
    </div>

    <!-- 프로필 이미지 선택 -->
    <div>
        <img src="@/assets/img/profile_female.png" alt="">
        <!-- <img src="@/assets/img/profile-male.png" alt=""> -->
        <!-- <img src="@/assets/img/profile-gentlelady.png" alt=""> -->
        <!-- <img src="@/assets/img/profile-gentleman.png" alt=""> -->
        <!-- <img src="@/assets/img/profile-female.png" alt="">
        <img src="@/assets/img/profile-female.png" alt=""> -->
    </div>

    <!-- 닉네임 수정 -->
    <div>
        <input type="text" v-model="nickname">
    </div>

    <!-- 나의 성격 태그 수정 -->
    <div>
        <input type="text">
        <input type="text">
        <input type="text">
    </div>
    <button @click="modifyExTag">수정 완료</button>
</template>

<script setup>

</script>

<style scoped></style>