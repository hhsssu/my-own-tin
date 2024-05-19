<template>
    <div>
        <h2 class="view-title">루틴 보관함</h2>
        <!-- 나의 루틴 태그 영역 -->
        <section class="profile-click-box content-box">
            <div class="flex-box">
                <div class="routine-title">평일 주 3회</div>
                <div class="routine-tag">20대</div>
                <div class="routine-tag">여성</div>
                <div class="routine-tag">유산소</div>
                <div class="routine-tag">하체</div>
                <div class="routine-tag">1시간</div>
                <!-- 추가 버튼 -->
                <div class="routine-title">+</div>
            </div>
            <div>
                <p>실내 사이클 30분, 땅끄부부 칼소폭 10분, 스쿼트20회 3세트, 스트레칭 10분</p>
            </div>
        </section>
        <section class="profile-click-box content-box">
            <div class="flex-box">
                <div class="routine-title">주말 루틴</div>
                <div class="routine-tag">20대</div>
                <div class="routine-tag">여성</div>
                <div class="routine-tag">유산소</div>
                <div class="routine-tag">하체</div>
                <div class="routine-tag">1시간</div>
                <!-- 추가 버튼 -->
                <div class="routine-title">+</div>
            </div>
            <div>
                <p>실내 사이클 30분, 스쿼트20회 3세트, 런지 20회 3세트, 스트레칭 10분</p>
            </div>
        </section>
    </div>
</template>

<script setup>
// 나의 루틴, 담아온 루틴 보여주기
// 다른사람의 루틴인지 확인할 수 있도록 함
// 분류하여 보기..?
</script>

<style scoped>
/* 나의 루틴 태그 스타일 */
.routine-tag {
    font-size: 0.8em;
    padding: 4px 5px;
    margin-bottom: 20px;
    border-radius: 3px;
    margin-right: 7px;
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
</style>