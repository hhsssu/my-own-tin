<template>
  <div class="search-item content-box" @click="handleClick">
    <!-- 루틴 말머리 (정보) -->
    <div class="search-item-head">
      <div class="routine-title">{{ routine.title }}</div>
      <div class="routine-tag">{{ routine.userAge }}</div>
      <div class="routine-tag">{{ routine.userGender }}</div>
      <div class="routine-tag">{{ routine.part1 }}</div>
      <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
      <div class="routine-tag">{{ routine.workoutTime }}분</div>
      <div class="search-item-more" @click="showMore">...</div>
    </div>
    <!-- 루틴 본문 -->
    <div class="search-item-content">{{ routine.content }}</div>
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
</script>

<style scoped>
.search-item {
  background-color: #fff9e8;
}

.search-item-head {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.search-item-title {
  font-size: 1.2em;
  font-weight: 600;
  margin-right: 10px;
}



.search-item-more {
  /* 더보기 */
  margin-left: auto;
  margin-bottom: 20px;
}

.search-item-content {
  font-size: 1em;
  padding: 5px;
}
</style>
