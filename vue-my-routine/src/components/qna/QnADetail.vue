<template>
  <div class="qna-detail-container" v-if="question">
    <!-- 작성자 프로필 -->
    <div class="qna-detail-profile">
      <img src="/src/assets/img/profile.png" class="qna-detail-profile-pic" />
      <div class="qna-detail-profile-name">{{ question.writer }}</div>
      <div class="routine-tag">Lv.{{ question.userLevel }}</div>
      <div class="routine-user-tag">{{ question.userAge }}</div>
      <div class="routine-user-tag">{{ question.userGender }}</div>
    </div>
    <!-- 작성글 -->
    <div class="qna-detail-card">
      <div class="qna-detail-title">{{ question.title }}</div>
      <p class="qna-detail-content">
        {{ question.content }}
      </p>
      <!-- 작성자 루틴 -->
      <div class="qna-detail-routine content-box" v-if="question.routine != null">
        <div class="routine-title">{{ question.routine.title }}</div>
        <div class="flex-box">
          <div class="routine-user-tag qna-detail-routine-info">{{ question.userAge }}</div>
          <div class="routine-user-tag qna-detail-routine-info">{{ question.userGender }}</div>
          <div class="routine-tag qna-detail-routine-info2">{{ question.routine.part1 }}</div>
          <div v-if="question.routine.part2 != null" class="routine-tag qna-detail-routine-info2">{{ question.routine.part2 }}</div>
          <div class="routine-tag qna-detail-routine-info2">{{ workoutTimeFormat(question.routine.workoutTime) }}</div>
        </div>
        <p class="qna-detail-routine-content">{{ question.routine.content }}</p>
      </div>
      <!-- 로그인된 유저가 작성자일 경우, 수정 / 삭제 버튼 활성화 -->
      <div v-if="checkWriter(question.userId)">
        <button @click="updateQuestion" class="mp-create-btn">수정</button>
        <button @click="deleteQuestion" class="mp-create-btn">삭제</button>
      </div>
    </div>

    <!-- 댓글 -->
    <!-- AnswerList 로 이동 -->
    <AnswerList :questionId="questionId"/>

    <!-- 댓글 작성 폼 -->
    <!-- AnswerCreate 로 이동 -->
    <AnswerCreate :questionId="questionId"/>
  </div>
</template>

<script setup>
import AnswerList from "./AnswerList.vue";
import AnswerCreate from "./AnswerCreate.vue";
import axios from "axios";
import { ref, watch, defineProps } from "vue";
import { useQnAStore } from "@/stores/qna";
import { useRouter } from "vue-router";

const props = defineProps({
  questionId: {
    type: Number,
    required: true,
  },
  selectedQuestionId: {
    type: Number,

  }
});

const store = useQnAStore();
const router = useRouter();
const question = ref(null);

const fetchQuestionDetail = async (id) => {
  try {
    const questionData = await store.getQuestion(id);
    question.value = questionData;
  } catch (error) {
    console.error(error);
  }
};

watch(
  () => props.questionId,
  async (newId) => {
    if (newId) {
      await fetchQuestionDetail(newId);
    }
  },
  { immediate: true }
);

const checkWriter = function (userId) {
  const loginUser = JSON.parse(sessionStorage.getItem('user'));
  if (loginUser && loginUser.id === userId) {
    return true;
  }
  return false;
};

const emits = defineEmits(['isUpdate']);

const updateQuestion = function () {
  emits("isUpdate", true);
  console.log(store.question.userId);
  console.log(store.question.title);
}

const deleteQuestion = function () {
  axios.put(`http://localhost:8080/myroutine/que/delete?id=${store.question.id}`)
  .then(() => {
    router.push({ name: 'qna' });
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
.qna-detail-container {
  width: 400px;
  margin-left: 15px;
  padding: 20px 30px;
}

.qna-detail-profile {
  display: flex;
  align-items: center; /* 수직 가운데 정렬 */
  margin-bottom: 30px;
}

.qna-detail-profile-pic {
  width: 50px;
  height: 50px;
}

.qna-detail-profile-name {
  padding: 3px 10px;
  margin: 0px 5px;
  font-size: 1em;
  width: 120px;
}

.qna-detail-profile-level {
  background-color: #fae6b1;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  font-size: 1em;
}

.qna-detail-profile-tag {
  background: #aaa;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  color: white;
  font-size: 1em;
}

.qna-detail-title {
  font-size: 1.3rem;
  font-weight: 500;
  padding-bottom: 10px;
}

.qna-detail-content {
  font-size: 1em;

}

.routine-tag {
  background-color: #fae6b1;
  color: #555;
}

.qna-detail-routine {
  background-color: #fff9e8;
}

.qna-detail-routine-info {
  background-color: #aaa;
}

.qna-detail-routine-info2 {
  background-color: #31525b;
  color: #fff;
}

.qna-detail-routine-content {
  padding: 0px 0px 10px 0;
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
