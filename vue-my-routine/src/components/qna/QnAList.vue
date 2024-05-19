<template>
  <div class="qna-list">
    <div class="qna-list-container">
      <div><h1>질문 상담</h1></div>
      <button class="qna-list-create-button" @click="createQnA">질문 등록</button>
      <!-- 질문 목록 페이지 내 검색 기능 -->
      <div class="qna-list-search">
        <div class="qna-list-search-input">
          <div>#</div>
          <input type="text" placeholder="검색어를 입력하세요" />
          <!-- 돋보기 아이콘 -->
          <div class="qna-list-search-button">
            <svg
              xmlns="http://www.w3.org/2000/svg"
              width="16"
              height="16"
              fill="currentColor"
              class="bi bi-search"
              viewBox="0 0 16 16"
            >
              <path
                d="M11.742 10.344a6.5 6.5 0 1 0-1.397 1.398h-.001q.044.06.098.115l3.85 3.85a1 1 0 0 0 1.415-1.414l-3.85-3.85a1 1 0 0 0-.115-.1zM12 6.5a5.5 5.5 0 1 1-11 0 5.5 5.5 0 0 1 11 0"
              />
            </svg>
          </div>
        </div>
        <!-- 질문 목록 내 검색 기능 필터 -->
        <!-- 폼을 아예 루틴 쪽과 통일시켜 만들지 고민 -->
        <div>
          <select name="qna-search" id="qna-search" class="qna-list-search-filter">
            <option value="">검색 필터 OFF</option>
            <option value="">최신순</option>
            <option value="">좋아요 순</option>
          </select>
        </div>
      </div>
      <!-- 질문 목록 본문 -->
      <div>
        <!-- 질문 카드 -->
        <div class="qna-list-card">
          <!-- 상단 작성자 프로필 -->
          <div class="qna-list-card-profile">
            <!-- 작성자 프로필 사진 -->
            <img src="/src/assets/img/profile.png" class="qna-list-card-pic" />
            <div class="qna-list-card-name">익명</div>
            <div class="qna-list-card-tag">20대</div>
            <div class="qna-list-card-tag">여성</div>
          </div>
          <!-- 질문 제목 -->
          <div class="qna-list-card-title">
            헬스장 초보자 루틴 추천 좀 해주세요!!!!🚨🚨🚨
          </div>
        </div>

        <div class="qna-list-card">
          <!-- 상단 작성자 프로필 -->
          <div class="qna-list-card-profile">
            <!-- 작성자 프로필 사진 -->
            <img src="/src/assets/img/profile_male.png" class="qna-list-card-pic" />
            <div class="qna-list-card-name">힘세진</div>
            <div class="qna-list-card-level">Lv. 2</div>
            <div class="qna-list-card-tag">20대</div>
            <div class="qna-list-card-tag">남성</div>
          </div>
          <!-- 질문 제목 -->
          <div class="qna-list-card-title">추가로 할 운동 추천해주세요</div>
        </div>
      </div>
    </div>
    <div v-if="showQnADetail">
      <QnADetail />
    </div>
    <div v-else-if="showQnACreate">
      <QnACreate />
    </div>
  </div>
</template>

<script setup>
import { useRouter } from "vue-router";
import QnACreate from "./QnACreate.vue";
import QnADetail from "./QnADetail.vue";
import { onMounted, ref } from "vue";

const router = useRouter();
const showQnADetail = ref(false);
const showQnACreate = ref(false);

const createQnA = function () {
  showQnADetail.value = false;
  showQnACreate.value = true;
  router.push({ name: "qnaCreate" });
};

onMounted(() => {
  if(sessionStorage.getItem('selectedQnA')) {
    showQnACreate.value = false;
    showQnADetail.value = true;
  } else {
    showQnACreate.value = false;
    showQnADetail.value = false;
  }
})
</script>

<style scoped>
.qna-list {
  display: flex;
  /* width: 100%; */
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
  width: 650px;
  border-right: 1px solid #777;
  padding-right: 15px;
}

.qna-list-search {
  margin-bottom: 40px;
}

.qna-list-search-input {
  display: flex;
  border-bottom: 2px solid lightgray;
  font-size: 30px;
  color: #ffa101;
  width: 500px;
}

.qna-list-search-input input {
  border: transparent;
  font-size: 20px;
  color: gray;
  margin: 5px 10px;
  width: 450px;
}

.qna-list-search-button svg {
  color: gray;
}

.qna-list-search-filter {
  padding: 5px;
  margin: 10px 5px;
  border-radius: 3px;
  border: 1px solid lightgray;
  color: gray;
}

.qna-list-card {
  background-color: #eafafd;
  padding: 15px;
  margin-bottom: 30px;
  border-radius: 5px;
  width: 550px;
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
  font-size: 18px;
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
  font-size: 25px;
  padding: 15px 10px 0px 10px;
}
</style>
