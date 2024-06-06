<template>
  <div class="search-item content-box" @click="handleClick">
    <!-- 루틴 말머리 (정보) -->
    <div class="routine-title">{{ routine.title }}</div>
    <div class="flex-box">
      <div class="routine-tag" style="background-color: #FFA101;">Lv.{{ routine.userLevel }}</div>
      <div class="routine-user-tag">{{ routine.userAge }}</div>
      <div class="routine-user-tag">{{ routine.userGender }}</div>
      <div class="routine-tag">{{ routine.part1 }}</div>
      <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
      <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
      <!-- <div class="search-item-more" @click="showMore">...</div> -->
    </div>
    <!-- 루틴 본문 -->
    <p class="search-item-content">{{ routine.content }}</p>
    <div style="text-align: end; font-size: 0.8rem; color: #999;">{{ formatDate(routine.routineAt) }}</div>
  </div>
</template>

<script setup>
import { defineProps } from "vue";

const props = defineProps({
  routine: {
    type: Object,
    required: true,
  },
});

const emits = defineEmits(["selectRoutine"]);

const handleClick = () => {
  emits("selectRoutine", props.routine.id);
};

const showMore = function () {
  // 현재 검색 상세 페이지 조회와 겹치는 상황
  console.log("더보기");
};

// 운동 시간 태그 (30 -> 30분, 60 -> 1시간, 90 -> 1시간30분)
const workoutTimeFormat = (workoutTime) => {
    const hours = Math.floor(workoutTime / 60); // 시간
    const minuties = workoutTime % 60; // 분
    
    let workout = '';
    if(hours > 0) {
        workout += `${hours}시간`;
    }
    if(minuties > 0) {
        workout += `${minuties}분`;
    }

    return workout.trim();
}

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줍니다.
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}/${month}/${day}`;
}

</script>

<style scoped>
.search-item {
  background-color: #fff9e8;
}

.search-item-more {
  /* 더보기 */
  margin-left: auto;
  margin-bottom: 20px;
}
</style>
