<template>
  <div class="qna-create">
    <!-- 프로필 공개 여부 -->
    <!-- 사용자의 isPublic 여부에 따라 표시 -->
    <!-- <div class="qna-create-profile">
      <label for="profile">프로필 공개 여부</label>
      <input type="checkbox" id="public" value="true" v-model="isPublic" />
      <label for="public">예</label>
      <input type="checkbox" id="private" value="false" v-model="isPublic" />
      <label for="private">아니오</label>
    </div> -->
    <!-- 제목 -->
    <div class="qna-create-title">
      <label for="title">제목</label>
      <input type="text" v-model="question.title" />
    </div>
    <!-- 내용 -->
    <div class="qna-create-content">
      <label for="content">내용</label>
      <textarea
        name="content"
        id="content"
        cols="30"
        rows="10"
        v-model="question.content"
      ></textarea>
    </div>
    <!-- 루틴 인용 -->
    <div class="qna-create-routine">
      <label for="routine">루틴</label>
      <!-- 날짜 선택기 -->
      <input type="date" v-model="selectedDate" @change="fetchRoutines" />
      <!-- 루틴 목록 -->
      <select v-if="routines.length" v-model="selectedRoutine">
        <option v-for="routine in routines" :key="routine.id" :value="routine.id">
          {{ routine.title }}
        </option>
      </select>
    </div>
    <!-- 등록 및 취소 버튼 -->
    <div class="qna-create-buttons">
      <button @click="confirmCreate">등록</button>
      <button @click="deleteCreate">취소</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from "vue";
import { useQnAStore } from "@/stores/qna";
import { useRouter } from "vue-router";
import axios from "axios";

const store = useQnAStore();
const router = useRouter();

const question = {
  userId: "",
  routineId: "",
  writer: "",
  title: "",
  content: "",
};

const selectedDate = ref('');
const routines = ref([]);
const selectedRoutine = ref(null);

const fetchRoutines = async () => {
  if (selectedDate.value) {
    try {
      const userId = JSON.parse(sessionStorage.getItem("user")).id;
      console.log(sessionStorage.getItem('user'));
      // const userId = 13;
      const response = await axios.get(`http://localhost:8080/myroutine/routine/byDate`, { params: { date: selectedDate.value, userId: userId } });
      routines.value = response.data;
      console.log(routines.value);
    } catch (error) {
      console.error(error);
    }
  }
};

const confirmCreate = function () {
  const userItem = sessionStorage.getItem("user");
  const userObj = JSON.parse(userItem);
  question.userId = userObj.id;
  // console.log(userObj.id);
  // console.log(selectedRoutine.value);
  store.createQuestion(question);

  router.push({ name: 'qnaUpdate', params: { selectedRoutine: selectedRoutine.value } });
};
</script>

<style scoped>
.qna-create {
  /* border: 1px solid gray; */
  padding: 30px;
  margin: 20px;
  /* width: 500px; */
  height: 570px;
  font-size: 1em;
  color: #777;
  box-shadow: 5px 5px 10px lightgray;
}

.qna-create-profile,
.qna-create-title,
.qna-create-content,
.qna-create-routine {
  margin: 20px 0px;
}

.qna-create-profile input {
  margin-right: 10px;
}

.qna-create-profile label,
.qna-create-title label,
.qna-create-content label,
.qna-create-routine label {
  margin-right: 30px;
}

.qna-create-title input {
  border: none;
  border-bottom: 1px solid #999;
  width: 290px;
  font-size: 1em;
  padding: 5px;
}

.qna-create-title input:focus {
  outline: none;
}

.qna-create-content {
  width: 400px;
}

.qna-create-content label {
  vertical-align: top;
}

.qna-create-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  width: 290px;
  height: 300px;
  font-size: 1em;
  padding: 5px;
}

.qna-create-routine {
  display: flex;
  align-items: center;
}

.qna-create-routine div {
  border-bottom: 1px solid #999;
}

.qna-create-routine button {
  border: none;
  border-radius: 5px;
  background-color: #aaa;
  color: white;
  font-size: 1em;
  padding: 5px 20px;
  margin-left: auto;
  margin-right: 30px;
}

.qna-create-routine button:hover {
  background-color: #ffa101;
}

.qna-create-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: auto;
  margin-right: 30px;
}

.qna-create-buttons button {
  padding: 5px 20px;
  margin-left: 10px;
  color: white;
  font-size: 1em;
  border: none;
  border-radius: 3px;
}

.qna-create-buttons button:nth-child(1) {
  background-color: #ffa101;
}

.qna-create-buttons button:nth-child(2) {
  background-color: #aaa;
}
</style>
