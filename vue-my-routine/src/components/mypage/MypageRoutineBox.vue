<template>
    <div>
        <h2 class="view-title">루틴 보관함</h2>
        <!-- 나의 루틴 태그 영역 -->
        <section class="profile-click-box content-box" v-for="item in routineList" :key="item.id">
            <div class="flex-box">
                <div class="routine-title">{{ routine.title }}</div>
                <div class="routine-tag">{{ routine.userAge }}</div>
                <div class="routine-tag">{{ routine.userGender }}</div>
                <div class="routine-tag">{{ routine.part1 }}</div>
                <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
                <div class="routine-tag">{{ routine.workoutTime }}분</div>
                <!-- 더보기 버튼 -->
                <div></div>
            </div>
            <p>
                {{ routine.content }}
            </p>
        </section>
    </div>
</template>

<script setup>
// 담아둔 루틴 보여주기
import { useRoutineStore } from "@/stores/routine";
import { defineProps } from "vue";
import { useRoute } from "vue-router";

const store = useRoutineStore();
const route = useRoute();

const props = defineProps({
    routine: {
        type: Object,
        required: true,
    },
});

// 보관함에 담은 것만 뽑아내기 (user marked)



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