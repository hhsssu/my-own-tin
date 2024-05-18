<template>
    <div class="header-container">
        <RouterLink :to="{name: 'search'}">검색</RouterLink>
        <RouterLink :to="{name: 'qna'}">질문상담</RouterLink>
        <RouterLink :to="{name: 'routine'}">
            <img src="@/assets/img/logo2.png" alt="MOT로고" class="img-header-logo">
        </RouterLink>
        <RouterLink :to="{name: 'mypage'}">마이페이지</RouterLink>
        <button type="button" class="logout-btn">로그아웃</button>
    </div>
</template>

<script setup>

</script>

<style scoped>
.header-container {
    width: 1000px;
    margin: 30px auto;
    padding: 7px 0;
    text-align: center;
    border-bottom: 1px solid #777;
}

.img-header-logo {
    width: 150px;
    margin: 0 150px;
}

a, .logout-btn {
    text-decoration: none;
    color: #777;
    font-size: 1em;
    margin: 0 20px 0 0;
}

.logout-btn {
    border: none;
    background-color: #fff;
    padding: 0;
}
</style>