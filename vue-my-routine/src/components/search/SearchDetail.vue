<template>
  <div v-if="routine" class="search-detail">
    <!-- 루틴 말머리 (정보) -->
    <div class="routine-title">{{ routine.title }}</div>
    <div class="search-detail-head">
      <div class="routine-user-tag">{{ routine.userAge }}</div>
      <div class="routine-user-tag">{{ routine.userGender }}</div>
      <div class="routine-tag">{{ routine.part1 }}</div>
      <div class="routine-tag" v-if="routine.part2 != null">
        {{ routine.part2 }}
      </div>
      <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
    </div>
    <!-- 루틴 본문 -->
    <div class="search-detail-content">{{ routine.content }}</div>
    <!-- 버튼 -->
    <div class="search-detail-buttons">
      <button @click="createBookmark">담기</button>
      <button @click="clickForLike">좋아요</button>
    </div>
  </div>
</template>

<script setup>
import { ref, watch, defineProps, isMemoSame } from "vue";
import { useRoutineStore } from "@/stores/routine";
import axios from "axios";
import { useRouter } from "vue-router";

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

const createBookmark = function () {
  store.getRoutine(props.routineId);
  const loginUser = JSON.parse(sessionStorage.getItem("user"));
  // console.log(loginUserId);
  const newRoutine = {
    ...store.routine,
    isMarked: 1,
    userId: loginUser.id,
    writer: loginUser.nickname,
  };
  store.createRoutine(newRoutine);
  alert("북마크에 추가되었습니다!");
};

const clickForLike = function () {
  axios
    .put(`http://localhost:8080/myroutine/routine/like?id=${props.routineId}`)
    .then(() => {
      alert("좋아요를 눌렀습니다!");
      router.go({ name: "search" });
    });
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
.search-detail {
  margin-left: 4%;
  padding: 15px;
  border-radius: 5px;
  box-shadow: 5px 5px 10px lightgray;
  width: 90%;
  height: 300px;
  display: flex;
  flex-direction: column;
}

.search-detail-head {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.search-detail-content {
  font-size: 1em;
  padding: 5pdetail;
}

.search-detail-buttons {
  margin-top: auto;
  margin-left: auto;
  margin-right: auto;
}

.search-detail-buttons button {
  margin: 10px;
  padding: 10px 20px;
  font-size: 1.3em;
  width: 100px;
  border-radius: 5px;
  border: 1px solid black;
}
</style>
