<template>
  <div>
    <div class="qna-detail-comment-input">
      <textarea
        name=""
        id=""
        placeholder="댓글을 작성하세요..."
        v-model="answer.content"
        style="min-width: 90%;
        max-width: 90%;
        min-height: 100px;
        max-height: 80px;"
      ></textarea>
      <button class="qna-detail-comment-button" @click="createAnswer">등록</button>
    </div>
  </div>
</template>

<script setup>
import { useQnAStore } from "@/stores/qna";
import { defineProps } from "vue";

const store = useQnAStore();

const props = defineProps({
  questionId: {
    type: Number,
    required: true,
  },
});

const answer = {
  writer: "",
  content: "",
  questionId: "",
  userId: "",
};

const createAnswer = function () {
  const userItem = sessionStorage.getItem("user");
  const userObj = JSON.parse(userItem);
  answer.userId = userObj.id;
  answer.questionId = props.questionId;
  store.createAnswer(answer);
};
</script>

<style scoped>
.qna-detail-comment-input {
  display: flex;
  flex-direction: column;
}

.qna-detail-comment-input textarea {
  padding: 15px;
  margin: 20px 0px 10px 0px;
  border: 1px solid #777;
  border-radius: 5px;
  font-size: 1em;
  width: 90%;
}

.qna-detail-comment-button {
  background-color: #ffa101;
  color: white;
  font-size: 15px;
  padding: 5px 15px;
  border: none;
  border-radius: 5px;
  margin-left: auto;
  margin-right: 10px;
  /* margin-left: 10px; */
}
</style>
