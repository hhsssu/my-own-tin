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
    </div>
    <!-- 운동 부위 -->
    <div class="routine-create-part">
      <label for="part">운동 부위</label>
    </div>
    <!-- 운동 시간 -->
    <div class="routine-create-workoutTime">
      <label for="workoutTime">운동 시간</label>
      <input type="number" v-model="routine.workoutTime" />
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
import { useRoutineStore } from "@/stores/routine";
import { useUserStore } from "@/stores/user";

const store = useRoutineStore();
const userStore = useUserStore();

const routine = {
  userId: "",
  title: "",
  writer: "",
  routineAt: "",
  part1: "",
  part2: "",
  workoutTime: "",
  content: "",
};

const confirmCreate = function () {
  const userItem = sessionStorage.getItem("user");
  const userObj = JSON.parse(userItem);
  routine.userId = userObj.id;
  routine.writer = userObj.nickname;
  console.log(routine.writer);
  
  store.createRoutine(routine);
};

const cancelCreate = function () {

};

</script>

<style scoped>
.routine-create {
  padding: 30px;
  margin: 20px;
  height: 570px;
  font-size: 1em;
  color: #777;
  box-shadow: 5px 5px 10px lightgray;
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
.routine-create-content label {
  margin-right: 30px;
  /* width: 200px; */
}

.routine-create-title input,
.routine-create-workoutTime input {
  border: none;
  border-bottom: 1px solid #999;
  width: 290px;
  font-size: 1em;
  margin-left: 10px;
  padding: 5px 0px;
}

.routine-create-title input:focus,
.routine-create-workoutTime input:focus {
  outline: none;
}

.routine-create-content {
  width: 400px;
}

.routine-create-content label {
  vertical-align: top;
}

.routine-create-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  width: 290px;
  height: 300px;
  font-size: 1em;
  padding: 5px;
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
