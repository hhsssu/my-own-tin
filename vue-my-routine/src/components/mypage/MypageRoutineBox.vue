<template>
  <div>
    <h2 class="view-title">루틴 보관함</h2>
    <!-- 나의 루틴 태그 영역 -->
    <!-- <div v-for="routine in store.routineList" :key="routine.id"> -->
    <section
      class="profile-click-box content-box"
      v-for="routine in store.routineList"
      :key="routine.id"
    >
      <div class="flex-box flex-space-between">
        <div class="routine-title">{{ routine.title }}</div>
        <!-- 등록 버튼 -->
        <div>
            <button @click="createRoutine(routine.id)" class="mp-create-btn">등록</button>
        <!-- 삭제 버튼 -->
          <!-- <img src="@/assets/img/icon/free-icon-cross-11122367.png" alt="보관한 루틴 삭제버튼"
                        class="img-delete-btn"
                        @click="deleteRoutine(routine.id)"> -->
          <button @click="deleteRoutine(routine.id)" class="mp-create-btn">삭제</button>
        </div>
      </div>
      <div class="flex-box">
        <div class="routine-user-tag">{{ routine.userAge }}</div>
        <div class="routine-user-tag">{{ routine.userGender }}</div>
        <div class="routine-tag">{{ routine.part1 }}</div>
        <div class="routine-tag" v-if="routine.part2 != null">{{ routine.part2 }}</div>
        <div class="routine-tag">{{ workoutTimeFormat(routine.workoutTime) }}</div>
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
import axios from "axios";
import { useRouter } from "vue-router";

const store = useRoutineStore();
const router = useRouter();
const user = JSON.parse(sessionStorage.getItem("user"));

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

const createRoutine = async function (routineId) {
    await store.getRoutine(routineId);
    const newRoutine = { ...store.routine, isMarked: 0 };
    newRoutine.routineAt = newRoutine.createAt;
    store.createRoutine(newRoutine);
    alert("루틴을 생성했습니다!");
}

const deleteRoutine = function (routineId) {
  axios.put(`http://localhost:8080/myroutine/routine/delete?id=${routineId}`).then(() => {
    alert("삭제되었습니다!");
    router.go(0);
  });
};

// 보관함에 담은 것만 뽑아내기 (user marked)
onMounted(() => {
  store.getMarkedList(user.id);
});
</script>

<style scoped>
/* 프로필 클릭 박스의 타이틀 스타일 */
.routine-title {
  font-weight: 700;
  font-size: 1.2rem;
  margin-bottom: 20px;
  margin-right: 15px;
}

.content-box {
  cursor: default;
}

.mp-create-btn {
  margin-right: 10px;
  margin-bottom: 15px;
  border: none;
  background-color: #999;
  color: #fff;
  padding: 2px 10px;
  border-radius: 3px;
}
</style>
