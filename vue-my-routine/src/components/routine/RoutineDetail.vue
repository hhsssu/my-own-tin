<template>
  <!-- 루틴 상세 정보를 표시하는 부분 -->
  <div v-if="routine" class="routine-detail-container">
    <div class="flex-box flex-space-between">
      <div class="routine-title">{{ routine.title }}</div>
        <img src="@/assets/img/icon/free-icon-save-button-4443176.png" 
        @click.stop="createMarkedRoutine" class="scrap-btn">
      </div>
      <div class="flex-box">
        <div class="routine-user-tag">{{ routine.userAge }}</div>
        <div class="routine-user-tag">{{ routine.userGender }}</div>
        <div class="routine-tag">{{ routine.part1 }}</div>
        <div class="routine-tag" v-if="routine.part2 != null">
          {{ routine.part2 }}
        </div>
        <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
        <!-- 공개 / 비공개 여부 -->
      </div>
    <p class="routine-detail-content">{{ routine.content }}</p>
    <!-- 여기에 루틴의 상세 정보를 표시하는 나머지 코드 추가 -->
    <!-- 버튼 -->
    <div class="routine-detail-buttons">
      <button @click="clickForLike">좋아요 {{ routine.likeCnt }}</button>
    </div>
    <!-- 루틴 작성자와 사용자의 id값 같은지 비교 후 표시 -->
    <div class="routine-detail-buttons">
      <button @click="updateRoutine">수정</button>
      <button @click="deleteRoutine">삭제</button>
    </div>
  </div>
  <div v-else>
    <!-- <p>루틴을 선택하세요.</p> -->
  </div>
</template>

<script setup>
import { ref, watch, onMounted } from "vue";
import { useRoutineStore } from "@/stores/routine";
import { useRouter } from "vue-router";
import axios from "axios";

const props = defineProps({
  routineId: {
    type: Number,
    required: true,
  },
});

const store = useRoutineStore();
const router = useRouter()
const routine = ref(null);

const fetchRoutineDetail = async (id) => {
  const routineData = await store.getRoutine(id);
  routine.value = routineData;
};

watch(
  () => props.routineId,
  async (newId) => {
    if (newId) {
      await fetchRoutineDetail(newId);
    }
  },
  { immediate: true }
);

const emits = defineEmits(['isUpdate']);

const updateRoutine = function () {
  emits("isUpdate", true);
}

const deleteRoutine = function () {
  axios.put(`http://localhost:8080/myroutine/routine/delete?id=${store.routine.id}`)
  .then(() => {
    router.go({ name: 'routineList' });
  })
};

const createMarkedRoutine = () => {
  store.getRoutine(props.routineId);
  const newRoutine = { ...store.routine, isMarked: 1 };
  store.createRoutine(newRoutine); // 새로운 루틴 추가하는 메서드에 따라 변경
  alert('북마크에 추가되었습니다!');
};

const clickForLike = function () {
  axios.put(`http://localhost:8080/myroutine/routine/like?id=${props.routineId}`)
  .then(() => {
    alert('좋아요를 눌렀습니다!');
    router.pgo({ name: 'routineDetail', path:props.routineId })
    console.log(props.routineId);
  })
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
.routine-detail-container {
  padding: 0 50px;
}

.routine-detail-buttons {
  text-align: center;
}

.routine-detail-buttons button {
  background-color: white;
  border: 1px solid black;
  border-radius: 5px;
  font-size: 1.3em;
  width: 100px;
  padding: 10px 20px;
  margin: 10px 20px;
  cursor: pointer;
}

.scrap-btn {
  width: 25px;
  margin-left: 100px;
  cursor: pointer;
}
</style>
