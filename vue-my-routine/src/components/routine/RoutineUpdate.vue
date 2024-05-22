<template>
  <div class="routine-update">
    <!-- 제목 -->
    <div class="routine-update-title">
      <label for="title">제목</label>
      <input type="text" v-model="store.routine.title" />
    </div>
    <!-- 루틴 날짜 (생성날짜 X) -->
    <div class="routine-update-date">
      <label for="routineAt">날짜</label>
      <input type="date" v-model="store.routine.routineAt" />
    </div>
    <!-- 운동 부위 -->
    <div class="routine-update-part">
      <label for="part">운동 부위</label>
      <div class="flex-box">
        <div>
          <!-- 운동 부위 1 -->
          <select name="part1" id="part1" v-model="store.routine.part1">
            <option value="" disabled selected>운동부위1</option>
            <option value="유산소">유산소</option>
            <option value="하체">하체</option>
            <option value="상체">상체</option>
            <option value="어깨">어깨</option>
            <option value="팔">팔</option>
            <option value="허벅지">허벅지</option>
          </select>
        </div>
        <div>
          <!-- 운동 부위 2 -->
          <!-- 운동 부위 1을 선택해야 선택할 수 있도록 함(value != null) -->
          <select name="part2" id="part2" v-model="store.routine.part2">
            <option value="" disabled selected>운동부위2</option>
            <option value="null">선택안함</option>
            <option value="유산소">유산소</option>
            <option value="하체">하체</option>
            <option value="상체">상체</option>
            <option value="어깨">어깨</option>
            <option value="팔">팔</option>
            <option value="허벅지">허벅지</option>
          </select>
        </div>
      </div>
    </div>
    <!-- 운동 시간 -->
    <div class="routine-update-workoutTime">
      <label for="workoutTime">운동 시간</label>
      <input type="number" v-model="store.routine.workoutTime" />
    </div>
    <!-- 내용 -->
    <div class="routine-update-content">
      <label for="content">내용</label>
      <textarea
        name="content"
        id="content"
        cols="30"
        rows="10"
        v-model="store.routine.content"
      ></textarea>
    </div>
    <!-- 수정 / 취소 버튼 -->
    <div class="routine-update-buttons">
      <button @click="confirmUpdate">수정</button>
      <button @click="cancelUpdate">취소</button>
    </div>
  </div>
</template>

<script setup>
import { useRoutineStore } from "@/stores/routine";

const store = useRoutineStore();

const confirmUpdate = function () {
    store.updateRoutine(store.routine.id);
};

const cancelUpdate = function () {

};

</script>

<style scoped>
.routine-update {
  padding: 30px;
  margin: 20px;
  font-size: 1em;
  color: #777;
  box-shadow: 5px 5px 10px lightgray;
}

.routine-update-title,
.routine-update-part,
.routine-update-date,
.routine-update-content {
  margin: 20px 0px;
}

.routine-update-title label,
.routine-update-part label,
.routine-update-date label,
.routine-update-content label {
  margin-right: 30px;
  /* width: 200px; */
}

.routine-update-title input,
.routine-update-workoutTime input,
.routine-update-date input {
  border: none;
  border-bottom: 1px solid #999;
  width: 290px;
  font-size: 1em;
  margin-left: 10px;
  padding: 5px 0px;
}

.routine-update-title input:focus,
.routine-update-workoutTime input:focus,
.routine-update-date input:focus {
  outline: none;
}

.routine-update-part {
  display: flex;
  align-content: center;
}

.routine-update-part label {
  vertical-align: center;
}

.routine-update-content {
  width: 400px;
}

.routine-update-content label {
  vertical-align: top;
}

.routine-update-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  width: 290px;
  height: 300px;
  font-size: 1em;
  padding: 5px;
}

.routine-update-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: auto;
  margin-right: 30px;
}

.routine-update-buttons button {
  padding: 5px 20px;
  margin-left: 10px;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 3px;
}

.routine-update-buttons button:nth-child(1) {
  background-color: #ffa101;
}

.routine-update-buttons button:nth-child(2) {
  background-color: #aaa;
}
</style>
