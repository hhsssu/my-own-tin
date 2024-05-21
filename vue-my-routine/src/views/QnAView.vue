<template>
  <div class="qna-container">
    <div class="qna-list">
      <div class="qna-title">질문 상담</div>
      <!-- 질문 등록 버튼 -->
      <button class="qna-create-button" @click="createQnA">질문 등록</button>
      <!-- 검색창 -->
      <div>
        <div class="qna-search">
          <div>#</div>
          <input type="text" placeholder="검색어를 입력하세요" />
          <div>
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
        <!-- 검색 기능 필터 -->
        <div class="qna-search-option">
          <select
            name="search-option"
            id="search-option"
            class="qna-search-option"
            @change="handleSearchOptionChange"
          >
            <option value="OFF">검색 필터 OFF</option>
            <option value="ON">검색 필터 ON</option>
          </select>
          <!-- </div> -->
          <div
            :class="['filter-checkboxes', { show: filterCheckboxes }]"
            id="filter-checkboxes"
          >
            <div>
              <div>
                <input type="radio" name="filter1" id="filter1-1" value="전체" checked />
                <label for="filter1-1">전체</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-2" value="Lv 1" />
                <label for="filter1-2">Lv 1</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-3" value="Lv 2" />
                <label for="filter1-3">Lv 2</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-4" value="Lv 3" />
                <label for="filter1-4">Lv 3</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-5" value="Lv 4" />
                <label for="filter1-5">Lv 4</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-6" value="Lv 5" />
                <label for="filter1-6">Lv 5</label>
              </div>
              <div>
                <input type="radio" name="filter1" id="filter1-7" value="Lv 6" />
                <label for="filter1-7">Lv 6</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="filter2" id="filter2-1" value="전체" checked />
                <label for="filter2-1">전체</label>
              </div>
              <div>
                <input type="radio" name="filter2" id="filter2-2" value="좋아요순" />
                <label for="filter2-2">좋아요순</label>
              </div>
              <div>
                <input type="radio" name="filter2" id="filter2-3" value="최신순" />
                <label for="filter2-3">최신순</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="filter3" id="filter3-1" value="전체" checked />
                <label for="filter3-1">전체</label>
              </div>
              <div>
                <input type="radio" name="filter3" id="filter3-2" value="남성" />
                <label for="filter3-2">남성</label>
              </div>
              <div>
                <input type="radio" name="filter3" id="filter3-3" value="여성" />
                <label for="filter3-3">여성</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="filter4" id="filter4-1" value="전체" checked />
                <label for="filter4-1">전체</label>
              </div>
              <div>
                <input type="radio" name="filter4" id="filter4-2" value="10대" />
                <label for="filter4-2">10대</label>
              </div>
              <div>
                <input type="radio" name="filter4" id="filter4-3" value="20대" />
                <label for="filter4-3">20대</label>
              </div>
              <div>
                <input type="radio" name="filter4" id="filter4-4" value="30대" />
                <label for="filter4-4">30대</label>
              </div>
              <div>
                <input type="radio" name="filter4" id="filter4-5" value="40대" />
                <label for="filter4-5">40대</label>
              </div>
              <div>
                <input type="radio" name="filter4" id="filter4-6" value="50대 이상" />
                <label for="filter4-6">50대 이상</label>
              </div>
            </div>
          </div>
        </div>
      </div>
      <!-- 질의응답 리스트 -->
      <QnAList />
    </div>
    <!-- 세션에 selectedQnA 있는 경우 QnADetail 보임 -->
    <div v-if="showQnADetail">
      <!-- <div> -->
      <QnADetail />
    </div>
    <!-- 질의응답 작성 페이지 -->
    <div v-else-if="showQnACreate">
      <QnACreate />
    </div>
  </div>
</template>

<script setup>
import QnAList from "@/components/qna/QnAList.vue";
import QnADetail from "@/components/qna/QnADetail.vue";
import QnACreate from "@/components/qna/QnACreate.vue";
import { onMounted, onBeforeMount, ref } from "vue";

const filterCheckboxes = ref(false);
const showQnADetail = ref(false);
const showQnACreate = ref(false);

const createQnA = function () {
  showQnADetail.value = false;
  showQnACreate.value = true;
  router.push({ name: "qnaCreate" });
};

const handleSearchOptionChange = (event) => {
  filterCheckboxes.value = event.target.value === "ON";
};

onBeforeMount(() => {
  checkSelectedQnA();
});

const checkSelectedQnA = () => {
  if (sessionStorage.getItem("selectedQnA")) {
    showQnACreate.value = false;
    showQnADetail.value = true;
  } else {
    showQnACreate.value = false;
    showQnADetail.value = false;
  }
};

// 페이지 로드시마다 상태 업데이트
window.addEventListener("DOMContentLoaded", checkSelectedQnA);
</script>

<style scoped>
.qna-container {
  width: 1000px;
  margin: 0 auto;
  display: flex;
}

.qna-list {
  width: 47%;
  padding-right: 3%;
  border-right: 1px solid #aaa;
}

.qna-title {
  font-size: 1.5em;
  font-weight: 600;
  color: #31525b;
  margin-bottom: 20px;
  text-align: center;
}

.qna-create-button {
  font-size: 1em;
  color: white;
  background-color: #ffa101;
  border: none;
  border-radius: 5px;
  padding: 5px 15px;
}

/* 버튼에 마우스 커서 올렸을 시 */
.qna-create-button:hover {
  background-color: gray;
}

.qna-search {
  display: flex;
  border-bottom: 2px solid lightgray;
  font-size: 25px;
  color: #ffa101;
  /* width: 400px; */
  width: 100%;
  margin-right: 25px;
  margin-bottom: 10px;
}

.qna-search input {
  border: transparent;
  font-size: 15px;
  color: gray;
  margin: 5px 10px;
  /* width: 350px; */
  width: 90%;
}

.qna-search svg {
  color: gray;
}

.qna-search-option {
  /* margin-top: 20px; */
  border: 1px solid #aaa;
  border-radius: 3px;
}

.qna-search-option select {
  border: none;
  width: 100%;
}

.filter-checkboxes {
  display: none;
  margin-top: 20px;
  flex-wrap: wrap;
}

.filter-checkboxes.show {
  display: flex;
  justify-content: center;
  flex-wrap: wrap;
}

.filter-checkboxes > div {
  display: flex;
  flex-direction: column;
  margin-right: 20px;
}

.filter-checkboxes div div {
  margin-bottom: 10px;
}

.filter-checkboxes input {
  margin-right: 10px;
}
</style>
