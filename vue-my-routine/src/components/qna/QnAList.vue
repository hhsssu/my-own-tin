<template>
  <div class="qna-list-container">
    <!-- 질문 목록 본문 -->
    <!-- 질문 카드 -->
    <div v-for="qna in store.qnaList" :key="qna.id" @click="handleClick(qna)">
      <div class="qna-list-card content-box">
        <!-- 상단 작성자 프로필 -->
        <div class="flex-box flex-space-between">
          <!-- 작성자 프로필 사진 -->
          <div style="display: flex; align-items: center">
            <div>
              <img src="/src/assets/img/profile.png" class="qna-list-card-pic" />
            </div>
            <div class="qna-list-card-name">{{ qna.writer }}</div>
            <div class="routine-tag qna-list-card-level">Lv.{{ qna.userLevel }}</div>
            <div class="routine-tag">{{ qna.userAge }}</div>
            <div class="routine-tag">{{ qna.userGender }}</div>
          </div>
          <div>
            <div style="text-align: end; font-size: 0.8rem; color: #999;">{{ formatDate(qna.createAt) }}</div>
          </div>
        </div>
        <!-- 질문 제목 -->
        <div class="qna-list-card-title">{{ qna.title }}</div>
      </div>
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
      word: "",
    };
    store.searchQuestionList(searchCondition);
  }

  // store.getQnAList(searchCondition.value);
});

function formatDate(dateString) {
    const date = new Date(dateString);
    const year = date.getFullYear();
    const month = String(date.getMonth() + 1).padStart(2, '0'); // 월은 0부터 시작하므로 1을 더해줍니다.
    const day = String(date.getDate()).padStart(2, '0');
    return `${year}/${month}/${day}`;
}

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

.qna-list-card {
  background-color: #eafafd;
}

.qna-list-card-pic {
  width: 50px;
  margin-right: 7px;
  margin-bottom: 20px;
}

.qna-list-card-name {
  font-weight: 700;
  font-size: 1rem;
  margin-bottom: 20px;
  margin-right: 15px;
}

.routine-tag {
  background: #aaa;
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
  font-size: 1rem;
  font-weight: 600;
  padding-bottom: 10px;
}
</style>
