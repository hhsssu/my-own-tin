<template>
  <div class="qna-update">
    <!-- 제목 -->
    <!-- <h2>업데이트 페이지</h2> -->
    <div class="qna-update-title">
      <label for="title">제목</label>
      <input type="text" v-model="store.question.title" />
    </div>
    <!-- 내용 -->
    <div class="qna-update-content">
      <label for="content">내용</label>
      <textarea
        name="content"
        id="content"
        cols="30"
        rows="10"
        v-model="store.question.content"
      ></textarea>
    </div>
    <!-- 루틴 인용 -->
    <div class="qna-update-routine">
      <label for="routine">루틴</label>
      <!-- 날짜 선택기 -->
      <input type="date" v-model="selectedDate" @change="fetchRoutines" />
      <!-- 루틴 목록 -->
      <select v-if="routines.length" v-model="store.question.routineId">
        <option v-for="routine in routines" :key="routine.id" :value="routine.id">
          {{ routine.title }}
        </option>
      </select>
    </div>
    <!-- 등록 및 취소 버튼 -->
    <div class="qna-update-buttons">
      <button @click="confirmUpdate">등록</button>
      <button @click="cancelUpdate">취소</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useQnAStore } from "@/stores/qna";
import { useRoute, useRouter } from "vue-router";
import axios from "axios";

const store = useQnAStore();
const route = useRoute();
const router = useRouter();

const selectedDate = ref("");
const routines = ref([]);
const selectedRoutine = ref(route.params.selectedRoutine);

const fetchRoutines = async () => {
  if (selectedDate.value) {
    try {
      const userId = JSON.parse(sessionStorage.getItem("user")).id;
      console.log(sessionStorage.getItem("user"));
      const response = await axios.get(`http://localhost:8080/myroutine/routine/byDate`, {
        params: { date: selectedDate.value, userId: userId },
      });
      routines.value = response.data;
      console.log(routines.value);
    } catch (error) {
      console.error(error);
    }
  }
};

const confirmUpdate = function () {
    // console.log(selectedRoutine);
    // console.log(selectedRoutine.value);
    // store.question.routineId = selectedRoutine.value;
    // console.log(selectedRoutine.value);
    store.updateQuestion(store.question.id);
};

const cancelUpdate = function () {
    console.log(store.question.id);
    router.push({ name: 'qnaDetail', params: {questionId: store.question.id}});
};
</script>

<style scoped>
.qna-update {
  /* border: 1px solid gray; */
  padding: 30px;
  margin: 20px;
  /* width: 500px; */
  height: 570px;
  font-size: 1em;
  color: #777;
  box-shadow: 5px 5px 10px lightgray;
}

.qna-update-profile,
.qna-update-title,
.qna-update-content,
.qna-update-routine {
  margin: 20px 0px;
}

.qna-update-profile input {
  margin-right: 10px;
}

.qna-update-profile label,
.qna-update-title label,
.qna-update-content label,
.qna-update-routine label {
  margin-right: 30px;
}

.qna-update-title input {
  border: none;
  border-bottom: 1px solid #999;
  width: 290px;
  font-size: 1em;
  padding: 5px;
}

.qna-update-title input:focus {
  outline: none;
}

.qna-update-content {
  width: 400px;
}

.qna-update-content label {
  vertical-align: top;
}

.qna-update-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  width: 290px;
  height: 300px;
  font-size: 1em;
  padding: 5px;
}

.qna-update-routine {
  display: flex;
  align-items: center;
}

.qna-update-routine div {
  border-bottom: 1px solid #999;
}

.qna-update-routine button {
  border: none;
  border-radius: 5px;
  background-color: #aaa;
  color: white;
  font-size: 1em;
  padding: 5px 20px;
  margin-left: auto;
  margin-right: 30px;
}

.qna-update-routine button:hover {
  background-color: #ffa101;
}

.qna-update-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: auto;
  margin-right: 30px;
}

.qna-update-buttons button {
  padding: 5px 20px;
  margin-left: 10px;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 3px;
}

.qna-update-buttons button:nth-child(1) {
  background-color: #ffa101;
}

.qna-update-buttons button:nth-child(2) {
  background-color: #aaa;
}
</style>
