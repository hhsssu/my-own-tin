<template>
  <div class="qna-list-container">
    <!-- 질문 목록 본문 -->
    <!-- 질문 카드 -->
    <div class="qna-list-card" v-for="qna in store.qnaList" :key="qna.id">
      <!-- 상단 작성자 프로필 -->
      <div class="qna-list-card-profile">
        <!-- 작성자 프로필 사진 -->
        <img src="/src/assets/img/profile.png" class="qna-list-card-pic" />
        <div class="qna-list-card-name">{{ qna.writer }}</div>
        <div class="qna-list-card-tag">{{ qna.userAge }}</div>
        <div class="qna-list-card-tag">여성</div>
      </div>
      <!-- 질문 제목 -->
      <div class="qna-list-card-title">{{ qna.title }}</div>
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import { onMounted, ref } from "vue";
import { useQnAStore } from "@/stores/qna";

const store = useQnAStore();
const router = useRouter();

const showQnADetail = ref(false);
const showQnACreate = ref(false);

const searchCondition = ref({
  key: '',
  word: '',
  orderBy: '',
  orderByDir: ''
});

const createQnA = function () {
  showQnADetail.value = false;
  showQnACreate.value = true;
  router.push({ name: "qnaCreate" });
};

// const fetchDetails = async () => {
//     await store.getQnAList(searchCondition.value);
// };

onMounted(() => {
  if (sessionStorage.getItem("selectedQnA")) {
    showQnACreate.value = false;
    showQnADetail.value = true;
  } else {
    showQnACreate.value = false;
    showQnADetail.value = false;
  }

  store.getQnAList(searchCondition.value);
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
  padding: 15px;
  margin-bottom: 30px;
  border-radius: 5px;
  width: 94%;
  height: 100px;
  box-shadow: 5px 5px 10px lightgray;
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
  padding: 3px 10px;
  margin: 0px 5px;
  border-radius: 3px;
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
