<template>
  <div class="qna-detail-container" v-if="question">
    <!-- 작성자 프로필 -->
    <div class="qna-detail-profile">
      <img src="/src/assets/img/profile_male.png" class="qna-detail-profile-pic" />
      <div class="qna-detail-profile-name">{{ question.writer }}</div>
      <div class="qna-detail-profile-level">Lv.{{ question.userLevel }}</div>
      <div class="qna-detail-profile-tag">{{ question.userAge }}</div>
      <div class="qna-detail-profile-tag">{{ question.userGender }}</div>
    </div>
    <!-- 작성글 -->
    <div class="qna-detail-card">
      <div class="qna-detail-title">{{ question.title }}</div>
      <div class="qna-detail-content">
        {{ question.content }}
      </div>
      <!-- 작성자 루틴 -->
      <div class="qna-detail-routine" v-if="question.routine != null">
        <div class="qna-detail-routine-tag">
          <div class="qna-detail-routine-title">{{ question.routine.title }}</div>
          <div class="qna-detail-routine-info">{{ question.userAge }}</div>
          <div class="qna-detail-routine-info">{{ question.userGender }}</div>
          <div class="qna-detail-routine-info2">{{ question.routine.part1 }}</div>
          <div v-if="question.routine.part2 != null" class="qna-detail-routine-info2">{{ question.routine.part2 }}</div>
          <div class="qna-detail-routine-info2">{{ question.routine.workoutTime }}분</div>
        </div>
        <div class="qna-detail-routine-content">{{ question.routine.content }}</div>
      </div>
      <!-- 로그인된 유저가 작성자일 경우, 수정 / 삭제 버튼 활성화 -->
      <div v-if="checkWriter(question.userId)">
        <button @click="updateQuestion">수정</button>
        <button @click="deleteQuestion">삭제</button>
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

// const updateQuestion = function () {
//   router.push({ name: 'qnaUpdate' });
// };

const deleteQuestion = function () {

};

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
  font-size: 1.3em;
  margin-bottom: 10px;
}

.qna-detail-content {
  font-size: 1em;

}

.qna-detail-routine {
  background-color: #fff9e8;
  padding: 10px 25px;
  margin-top: 10px;
  /* padding-bottom: 50px; */
  height: 80px;
  box-shadow: 5px 5px 10px lightgray;
  border-radius: 5px;
}

.qna-detail-routine-tag {
  display: flex;
  align-items: center;
}

.qna-detail-routine-title {
  font-size: 1em;
  font-weight: 600;
  margin-right: 10px;
}

.qna-detail-routine-info {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 0.8em;
  background-color: #aaa;
  border-radius: 3px;
}

.qna-detail-routine-info2 {
  padding: 3px 8px;
  margin: 0px 5px;
  color: white;
  font-size: 0.8em;
  background-color: #31525b;
  border-radius: 3px;
}

.qna-detail-routine-content {
  padding: 20px 5px;
}
</style>
