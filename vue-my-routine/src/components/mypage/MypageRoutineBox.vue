<template>
    <div>
        <h2 class="view-title">루틴 보관함</h2>
        <!-- 나의 루틴 태그 영역 -->
        <!-- <div v-for="routine in store.routineList" :key="routine.id"> -->
            <section class="profile-click-box content-box" v-for="routine in store.routineList" :key="routine.id">
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
        <!-- </div> -->
    </div>
</template>

<script setup>
// 담아둔 루틴 보여주기
import { useRoutineStore } from "@/stores/routine";
import { onMounted } from "vue";
import { useRoute } from "vue-router";

const store = useRoutineStore();
const route = useRoute();
const user = JSON.parse(sessionStorage.getItem('user'));

// 보관함에 담은 것만 뽑아내기 (user marked)
onMounted(() => {
    store.getMarkedList(user.id);
})


</script>

<style scoped>
/* 프로필 클릭 박스의 타이틀 스타일 */
.routine-title {
    font-weight: 700;
    font-size: 1.2rem;
    margin-bottom: 20px;
    margin-right: 15px;
}
</style>