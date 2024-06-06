<template>
  <div class="routine-create">
    <!-- 제목 -->
    <div class="routine-create-title">
      <label for="title">제목</label>
      <input type="text" v-model="routine.title" />
    </div>
    <!-- 루틴 날짜 (생성날짜 X) -->
    <div class="routine-create-date">
      <label for="routineAt">날짜</label>
      <input type="date" v-model="routine.routineAt" />
    </div>
    <!-- 운동 부위 -->
    <div class="routine-create-part">
      <label for="part">운동 부위</label>
      <div class="flex-box">
        <div style="margin-right: 20px">
          <!-- 운동 부위 1 -->
          <select name="part1" id="part1" size="3" v-model="selectedOption1">
            <option value="" disabled selected>운동부위1</option>
            <option value="유산소">유산소</option>
            <option value="상체">상체</option>
            <option value="어깨">어깨</option>
            <option value="등">등</option>
            <option value="팔">팔</option>
            <option value="코어">코어</option>
            <option value="복부">복부</option>
            <option value="하체">하체</option>
            <option value="다리">다리</option>
            <option value="허벅지">허벅지</option>
            <option value="엉덩이">엉덩이</option>
          </select>
        </div>
        <div>
          <!-- 운동 부위 2 -->
          <!-- 운동 부위 1을 선택해야 선택할 수 있도록 함(value != null) -->
          <select
            name="part2"
            id="part2"
            size="3"
            :disabled="isPart2Disabled"
            v-model="selectedOption2"
          >
            <option value="" disabled selected>운동부위2</option>
            <option value="null">선택안함</option>
            <option value="유산소">유산소</option>
            <option value="상체">상체</option>
            <option value="어깨">어깨</option>
            <option value="등">등</option>
            <option value="팔">팔</option>
            <option value="코어">코어</option>
            <option value="복부">복부</option>
            <option value="하체">하체</option>
            <option value="다리">다리</option>
            <option value="허벅지">허벅지</option>
            <option value="엉덩이">엉덩이</option>
          </select>
        </div>
      </div>
    </div>
    <!-- 운동 시간 -->
    <div class="routine-create-workoutTime">
      <label for="workoutTime">운동 시간</label>
      <select name="time" id="time" size="3" v-model="timeOption">
        <option value="10">10분</option>
        <option value="20">20분</option>
        <option value="30">30분</option>
        <option value="40">40분</option>
        <option value="50">50분</option>
        <option value="60">1시간</option>
        <option value="90">1시간30분</option>
        <option value="120">2시간</option>
        <option value="150">2시간30분</option>
        <option value="180">3시간</option>
        <option value="240">4시간</option>
        <option value="300">5시간 이상</option>
      </select>
    </div>
    <!-- 내용 -->
    <div class="routine-create-content">
      <label for="content">내용</label>
      <textarea
        name="content"
        id="content"
        cols="30"
        rows="10"
        v-model="routine.content"
      ></textarea>
    </div>
    <!-- 등록 / 취소 버튼 -->
    <div class="routine-create-buttons">
      <button @click="confirmCreate">등록</button>
      <button @click="cancelCreate">취소</button>
    </div>
  </div>
</template>

<script setup>
import { usePointmileStore } from "@/stores/pointmile";
import { useRoutineStore } from "@/stores/routine";
import { useUserStore } from "@/stores/user";
import { ref, watch, computed } from "vue";

const store = useRoutineStore();
const userStore = useUserStore();
const pointMileStore = usePointmileStore();

const user = JSON.parse(sessionStorage.getItem("user"));

const selectedOption1 = ref(user.part1);
const selectedOption2 = ref(user.part2);
const timeOption = ref(user.workoutTime);

const routine = {
  userId: "",
  title: "",
  writer: "",
  routineAt: "",
  part1: selectedOption1.value,
  part2: selectedOption2.value,
  workoutTime: timeOption.value,
  content: "",
};

const isPart2Disabled = computed(() => selectedOption1.value === "null");

watch(selectedOption1, (newValue) => {
  if (newValue === "null") {
    selectedOption2.value = null;
  }
});

const confirmCreate = function () {
  const user = JSON.parse(sessionStorage.getItem("user"));
  routine.userId = user.id;
  routine.writer = user.nickname;
  routine.part1 = selectedOption1.value;
  routine.part2 = selectedOption2.value;


  if (timeOption.value === "0") {
    routine.workoutTime = null;
  } else {
    routine.workoutTime = parseInt(timeOption.value, 10);
  }

  // 날짜를 선택하지 않으면, 해당 날짜의 00시 00일로 설정됨
  // 현재 날짜를 YYYY-MM-DD 형식으로 설정
  if (routine.routineAt === "") {
    const now = new Date();
    const year = now.getUTCFullYear();
    const month = String(now.getUTCMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 +1 필요
    const day = String(now.getUTCDate()).padStart(2, '0');
    routine.routineAt = `${year}-${month}-${day}`;
  }

  store.createRoutine(routine);

  // 포인트 생성
  const point = {
    userId: routine.userId,
    amount: 100,
    record: "루틴 완료",
  };

  pointMileStore.createPoint(point);
};

const cancelCreate = function () {};
</script>

<style scoped>
.routine-create {
  padding: 30px;
  margin: 20px;
  font-size: 1em;
  color: #555;
  box-shadow: 3px 4px 20px -5px #ccc;
  border-radius: 5px;
}

.routine-create-title,
.routine-create-part,
.routine-create-date,
.routine-create-content {
  margin: 20px 0px;
}

.routine-create-title label,
.routine-create-part label,
.routine-create-date label,
.routine-create-workoutTime label,
.routine-create-content label {
  margin-right: 30px;
  /* width: 200px; */
}

.routine-create-title input,
.routine-create-workoutTime input,
.routine-create-date input {
  border: none;
  border-bottom: 1px solid #999;
  width: 290px;
  font-size: 1em;
  margin-left: 10px;
  padding: 5px 0px;
}

.routine-create-title input:focus,
.routine-create-workoutTime input:focus,
.routine-create-date input:focus {
  outline: none;
}

.routine-create-part {
  display: flex;
  align-content: center;
}

.routine-create-part label {
  vertical-align: center;
}

.routine-create-content {
  width: 400px;
}

.routine-create-content label,
.routine-create-workoutTime label  {
  vertical-align: top;
}

.routine-create-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  font-size: 1em;
  padding: 5px;
  min-width: 300px; 
  max-width: 300px; 
  min-height: 150px; 
  max-height: 150px;
}

.routine-create-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: auto;
  margin-right: 30px;
}

.routine-create-buttons button {
  padding: 5px 20px;
  margin-left: 10px;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 3px;
}

.routine-create-buttons button:nth-child(1) {
  background-color: #ffa101;
}

.routine-create-buttons button:nth-child(2) {
  background-color: #aaa;
}
</style>
