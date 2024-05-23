<template>
  <!-- 루틴 상세 정보를 표시하는 부분 -->
  <div v-if="routine" class="routine-detail-container">
    <div class="flex-box flex-space-between">
      <div class="routine-title">{{ routine.title }}</div>
      <img
        src="@/assets/img/icon/free-icon-save-button-4443176.png"
        @click.stop="createMarkedRoutine"
        class="scrap-btn"
      />
    </div>
    <div class="flex-box">
      <div style="display: flex">
        <div class="routine-user-tag">{{ routine.userAge }}</div>
        <div class="routine-user-tag">{{ routine.userGender }}</div>
        <div class="routine-tag">{{ routine.part1 }}</div>
        <div class="routine-tag" v-if="routine.part2 != null">
          {{ routine.part2 }}
        </div>
        <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
      </div>
      <!-- 공개 / 비공개 여부 -->
      <div>
        <div style="text-align: end; font-size: 0.8rem; color: #999">
          {{ formatDate(routine.routineAt) }}
        </div>
      </div>
    </div>
    <p class="routine-detail-content">{{ routine.content }}</p>
    <!-- 여기에 루틴의 상세 정보를 표시하는 나머지 코드 추가 -->
    <!-- 버튼 -->
    <div class="routine-detail-buttons">
      <div class="like-cnt">
        좋아요
        <span style="color: red;">
          {{ routine.likeCnt }}
        </span>
      </div>
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
const router = useRouter();
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

const emits = defineEmits(["isUpdate"]);

const updateRoutine = function () {
  emits("isUpdate", true);
};

const deleteRoutine = function () {
  axios
    .put(`http://localhost:8080/myroutine/routine/delete?id=${store.routine.id}`)
    .then(() => {
      alert("삭제되었습니다!");
      router.go(0);
    });
};

const createMarkedRoutine = () => {
  store.getRoutine(props.routineId);
  const newRoutine = { ...store.routine, isMarked: 1 };
  store.createRoutine(newRoutine); // 새로운 루틴 추가하는 메서드에 따라 변경
  alert("보관함에 저장되었습니다!");
};

// 운동 시간 태그 (30 -> 30분, 60 -> 1시간, 90 -> 1시간30분)
const workoutTimeFormat = (workoutTime) => {
  const hours = Math.floor(workoutTime / 60); // 시간
  const minuties = workoutTime % 60; // 분

  let workout = "";
  if (hours > 0) {
    workout += `${hours}시간`;
  }
  if (minuties > 0) {
    workout += `${minuties}분`;
  }

  return workout.trim();
};

function formatDate(dateString) {
  const date = new Date(dateString);
  const year = date.getFullYear();
  const month = String(date.getMonth() + 1).padStart(2, "0"); // 월은 0부터 시작하므로 1을 더해줍니다.
  const day = String(date.getDate()).padStart(2, "0");
  return `${year}/${month}/${day}`;
}
</script>

<style scoped>
.routine-detail-container {
  padding: 0 50px;
}

.routine-detail-container .flex-box {
  justify-content: space-between;
}

.routine-detail-buttons {
  text-align: center;
}

.routine-detail-buttons button {
  background-color: white;
  border: 1px solid black;
  border-radius: 5px;
  font-size: 1em;
  width: 100px;
  padding: 10px 20px;
  margin: 10px 20px;
}

.like-cnt {
  margin: 50px auto 20px;
  font-weight: 600;
}

.scrap-btn {
  width: 25px;
  margin-left: 100px;
  cursor: pointer;
}
</style>
