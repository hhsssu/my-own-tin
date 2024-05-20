<template>
    <!-- 나의 루틴 태그 영역 -->
    <section class="profile-click-box content-box">
        <div
        class="flex-box"
        v-for="routine in store.getRoutineList"
        :key="routine.id">
            <div class="routine-title">{{ routine.title }}</div>
            <div class="routine-tag" v-if="routine.age < 20">10대</div>
            <div class="routine-tag" v-else-if="routine.age > 19">20대</div>
            <div class="routine-tag" v-else-if="routine.age > 29">30대</div>
            <div class="routine-tag" v-else-if="routine.age > 39">40대</div>
            <div class="routine-tag" v-else-if="routine.age > 49">50대</div>
            <div class="routine-tag" v-else-if="routine.age > 59">60대 이상</div>
            <div class="routine-tag">{{ routine.gender }}</div>
            <div class="routine-tag">{{ routine.part1 }}</div>
            <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
            <div class="routine-tag">{{ routine.workout_time }}</div>
        </div>
        <div>
            <p>
                {{ routine.content }}
                실내 사이클 30분, 땅끄부부 칼소폭 10분, 스쿼트20회 3세트, 스트레칭 10분
            </p>
        </div>
    </section>
</template>

<script setup>
import { useRoutineStore } from "@/stores/routine";
import { onMounted } from "vue";
import { useRoute } from "vue-router";

const store = useRoutineStore();
const route = useRoute();

onMounted(() => {
    store.getRoutineList(route.params.userId);
});

</script>

<style scoped>
.routine-tag {
    background-color: #fae6b1;
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