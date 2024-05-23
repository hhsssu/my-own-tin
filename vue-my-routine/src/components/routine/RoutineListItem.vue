<template>
  <!-- 나의 루틴 태그 영역 -->
  <div class="profile-click-box content-box" @click="handleClick">
    <div class="routine-title">{{ routine.title }}</div>
    <div class="flex-box">
      <div class="routine-user-tag">{{ routine.userAge }}</div>
      <div class="routine-user-tag">{{ routine.userGender }}</div>
      <div class="routine-tag">{{ routine.part1 }}</div>
      <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
      <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
    </div>
    <p>
      {{ routine.content }}
    </p>
  </div>
</template>

<script setup>
import { useRoutineStore } from "@/stores/routine";
import { ref, defineProps, defineEmits } from "vue";
import { useRoute } from "vue-router";

const store = useRoutineStore();
const route = useRoute();

const props = defineProps({
  routine: {
    type: Object,
    required: true,
  },
});

const emits = defineEmits(['selectRoutine']);

const handleClick = () => {
  emits('selectRoutine', props.routine.id);
};


const isMarked = ref(false); // 북마크 여부

const createMarkedRoutine = () => {
  const newRoutine = { ...props.routine, isMarked: 1 };
  store.createRoutine(newRoutine); // 새로운 루틴 추가하는 메서드에 따라 변경
  isMarked.value = true; // 이미지 변경
  alert('북마크에 추가되었습니다!');
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
</script>

<style scoped>


/* 나의 루틴 태그 스타일 */
.routine-tag {
  /* background-color: #aaa; */
  color: white;
  font-weight: 300;
}


/* 프로필 클릭 박스의 타이틀 스타일 */
.routine-title {
  font-weight: 700;
  font-size: 1.2em;
  margin-right: 15px;
}

/* 각 프로필 박스 클릭 커서 변경 */
.profile-click-box {
  cursor: pointer;
}
</style>
