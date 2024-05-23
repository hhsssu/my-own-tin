<template>
    <div class="header-container">
        <div class="header-nav-container">
            <RouterLink :to="{name: 'search'}"><img class="img-header-nav" src="@/assets/img/search.png"></RouterLink>
            <RouterLink :to="{name: 'qna'}"><img class="img-header-nav" src="@/assets/img/qna.png"></RouterLink>
        </div>
        <RouterLink :to="{name: 'routine'}">
            <img src="@/assets/img/logo1.png" alt="MOT로고" class="img-header-logo">
        </RouterLink>
        <div class="header-nav-container">
            <RouterLink :to="{name: 'mypage'}"><img class="img-header-nav" src="@/assets/img/mypage.png"></RouterLink>
            <div class="logout-btn" @click="logout"><img class="img-header-nav" src="@/assets/img/logout.png"></div>
        </div>
    </div>
</template>

<script setup>
import { ref } from "vue";
import { useUserStore } from "@/stores/user";
import { useRouter } from "vue-router";

const router = useRouter();
const store = useUserStore();

const logout = function() {
    store.logoutUser();
    console.log("로그아웃");
};

</script>

<style scoped>
.header-container {
    width: 1000px;
    margin: 30px auto;
    padding: 7px 0;
    text-align: center;
    /* border-bottom: 1.5px solid #ddd; */
    /* box-shadow: 0px 13px 10px -12px #ddd; */
    font-family: 'Pretendard';
    font-weight: 600;
    display: flex;
    align-items: center;
    justify-content: space-between;
}

.header-nav-container {
    display: flex;
    flex-wrap: wrap;
    justify-content: space-around;
    width: 24%;
}

.img-header-logo {
    width: 150px;
}

.img-header-nav {
    /* width: 100px; */
    height: 40px;
}

a, .logout-btn {
    text-decoration: none;
    color: #777;
    font-size: 1rem;
}

/* 로그아웃 커서 모양 변경 */
.logout-btn {
    cursor: pointer;
}
</style>