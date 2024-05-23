<template>
  <div class="qna-list-container">
    <!-- 질문 목록 본문 -->
    <!-- 질문 카드 -->
    <div
      class="qna-list-card content-box"
      v-for="qna in store.qnaList"
      :key="qna.id"
      @click="handleClick(qna)"
    >
      <!-- 상단 작성자 프로필 -->
      <div class="qna-list-card-profile">
        <!-- 작성자 프로필 사진 -->
        <img src="/src/assets/img/profile.png" class="qna-list-card-pic" />
        <div class="qna-list-card-name">{{ qna.writer }}</div>
        <div class="routine-tag qna-list-card-level">Lv {{ qna.userLevel }}</div>
        <div class="routine-tag">{{ qna.userAge }}</div>
        <div class="routine-tag">{{ qna.userGender }}</div>
      </div>
      <!-- 질문 제목 -->
      <div class="qna-list-card-title">{{ qna.title }}</div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { onMounted, ref, defineEmits } from "vue";
import { useQnAStore } from "@/stores/qna";

const store = useQnAStore();
const router = useRouter();

const showQnADetail = ref(false);
const showQnACreate = ref(false);

const searchCondition = ref({
  key: "",
  word: "",
  orderBy: "",
  orderByDir: "",
});

const emits = defineEmits(["selectQuestion"]);

const handleClick = function (qna) {
  emits("selectQuestion", qna.id);
};

onMounted(() => {
  if (store.qnaList.length == 0) {
    const searchCondition = {
      level: "",
      gender: "",
      ageRange: "",
      orderBy: "",
      word: ""
    };
    store.searchQuestionList(searchCondition);
  }

  // store.getQnAList(searchCondition.value);
});
</script>

<style scoped>
.qna-list {
  display: flex;
  width: 500px;
}

/* 질문 등록 버튼 */
.qna-list-create-button {
  background-color: #ffa101;
  color: white;
  padding: 5px 10px;
  border-radius: 5px;
  text-decoration: none;
}

.qna-list-container {
  display: flex;
  flex-direction: column;
  align-items: center;
  width: 100%;
  /* border-right: 1px solid #777; */
  padding-top: 15px;
  padding-right: 15px;
}

.qna-list-card {
  background-color: #eafafd;
}

.qna-list-card-profile {
  display: flex;
  align-items: center;
}

.qna-list-card-pic {
  width: 50px;
  height: 50px;
}

.qna-list-card-name {
  padding: 3px 10px;
  margin: 0px 5px;
  font-size: 1em;
}

.qna-list-card-level {
  background-color: #fae6b1;
  color: #555;
}

.qna-list-card-tag {
  background: #aaa;
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
  color: white;
}

.qna-list-card-title {
  font-size: 1em;
  padding: 15px 10px 0px 10px;
}
</style>
