<template>
    <!-- 나의 루틴 태그 영역 -->
    <section class="profile-click-box content-box">
        <div class="flex-box">
            <div class="routine-title">평일 주 3회</div>
            <div class="routine-tag">20대</div>
            <div class="routine-tag">여성</div>
            <div class="routine-tag">유산소</div>
            <div class="routine-tag">하체</div>
            <div class="routine-tag">1시간</div>
        </div>
        <div>
            <p>실내 사이클 30분, 땅끄부부 칼소폭 10분, 스쿼트20회 3세트, 스트레칭 10분</p>
        </div>
    </section>
</template>

<script setup>

</script>

<style scoped>
.routine-tag {
    background-color: #FAE6B1;
    font-size: 0.8em;
    color: #333;
    font-weight: 500;
    padding: 4px 5px;
    margin-bottom: 20px;
    border-radius: 3px;
    margin-right: 7px;
}

/* 나의 루틴 태그 스타일 */
.routine-tag {
    background-color: #aaa;
    color: white;
    font-weight: 300;
}

/* 프로필 클릭 박스의 타이틀 스타일 */
.routine-title {
    font-weight: 700;
    font-size: 1.2em;
    margin-bottom: 20px;
    margin-right: 15px;
}

/* 각 프로필 박스 클릭 커서 변경 */
.profile-click-box {
    cursor: pointer;
}
</style>