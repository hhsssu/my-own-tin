<template>
  <div class="qna-detail-container" v-if="showQnADetail && question">
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
      <div class="qna-detail-routine">
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
    </div>

    <!-- 댓글 -->
    <!-- AnswerList 로 이동 -->
    <AnswerList :questionId="question.id"/>

    <!-- 댓글 작성 폼 -->
    <!-- AnswerCreate 로 이동 -->
    <AnswerCreate :questionId="question.id"/>
  </div>
</template>

<script setup>
import AnswerList from "./AnswerList.vue";
import AnswerCreate from "./AnswerCreate.vue";
import { ref, onMounted } from "vue";
import { useQnAStore } from "@/stores/qna";

const store = useQnAStore();

const showQnADetail = ref(false);
const showQnACreate = ref(false);
const question = ref(null);

const checkSelectedQnA = () => {
  const selectedQnA = JSON.parse(sessionStorage.getItem("selectedQnA"));
  if (selectedQnA) {
    showQnACreate.value = false;
    showQnADetail.value = true;
    question.value = selectedQnA;
  } else {
    showQnACreate.value = false;
    showQnADetail.value = false;
  }
};

onMounted(() => {
  checkSelectedQnA();
});
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
