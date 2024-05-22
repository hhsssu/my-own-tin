<template>
  <!-- 나의 루틴 태그 영역 -->
  <section class="profile-click-box content-box" @click="handleClick">
    <div class="flex-box">
      <div class="routine-title">{{ routine.title }}</div>
      <div class="routine-tag">{{ routine.userAge }}</div>
      <div class="routine-tag">{{ routine.userGender }}</div>
      <div class="routine-tag">{{ routine.part1 }}</div>
      <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
      <div class="routine-tag">{{ routine.workoutTime }}분</div>
      <!-- 더보기 버튼 -->
      <button class="routine-more" @click="toggleDropdown">...</button>
      <!-- 드롭다운 -->
      <div class="dropdown" v-if="showDropdown">
        <button @click="createMarkedRoutine">북마크</button>
      </div>
    </div>
    <p>
      {{ routine.content }}
    </p>
    </section>
</template>

<script setup>
import { useRoutineStore } from "@/stores/routine";
import { ref, defineProps, defineEmits } from "vue";
import { useRoute } from "vue-router";

const store = useRoutineStore();
const route = useRoute();
const showDropdown = ref(false);

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

const toggleDropdown = () => {
  showDropdown.value = !showDropdown.value;
};

const createMarkedRoutine = () => {
  const newRoutine = { ...props.routine, isMarked: 1 };
  store.createRoutine(newRoutine); // 새로운 루틴 추가하는 메서드에 따라 변경
  toggleDropdown(); // 드롭다운 닫기
};



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

/* 더보기 버튼 */
/* css 시급 */
.routine-more {
  margin-left: auto;
  background-color: transparent;
  border: none;
  border-radius: 5px;
  width: 30px;
  height: 30px;
}

.routine-more:hover {
  background-color: #777;
  color: white;
  cursor: pointer;;
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
