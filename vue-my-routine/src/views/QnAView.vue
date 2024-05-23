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
          <button @click="clickSearch">
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
          </button>
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
          <!-- 검색 상세 필터 div 시작 -->
          <div
            :class="['filter-checkboxes', { show: filterCheckboxes }]"
            id="filter-checkboxes"
          >
            <div>
              <div>
                <input type="radio" name="level" id="total-level" value="" checked v-model="condition.word1" />
                <label for="total-level">전체</label>
              </div>
              <div>
                <input type="radio" name="level" id="level1" value="1" v-model="condition.word1" />
                <label for="level1">Lv 1</label>
              </div>
              <div>
                <input type="radio" name="level" id="level2" value="2" v-model="condition.word1" />
                <label for="level2">Lv 2</label>
              </div>
              <div>
                <input type="radio" name="level" id="level3" value="3" v-model="condition.word1" />
                <label for="level3">Lv 3</label>
              </div>
              <div>
                <input type="radio" name="level" id="level4" value="4" v-model="condition.word1" />
                <label for="level4">Lv 4</label>
              </div>
              <div>
                <input type="radio" name="level" id="level5" value="5" v-model="condition.word1" />
                <label for="level5">Lv 5</label>
              </div>
              <div>
                <input type="radio" name="level" id="level6" value="6" v-model="condition.word1" />
                <label for="level6">Lv 6</label>
              </div>
            </div>
            <div>
              <div>
                <input
                  type="radio"
                  name="orderBy"
                  id="total-order"
                  value=""
                  checked
                  v-model="condition.orderBy"
                />
                <label for="total-order">전체</label>
              </div>
              <div>
                <input type="radio" name="orderBy" id="like-cnt" value="like_cnt" v-model="condition.orderBy" />
                <label for="like">좋아요순</label>
              </div>
              <div>
                <input type="radio" name="orderBy" id="routine-at" value="routine_at" v-model="condition.orderBy" />
                <label for="recent">최신순</label>
              </div>
            </div>
            <div>
              <div>
                <input
                  type="radio"
                  name="gender"
                  id="total-gender"
                  value=""
                  checked
                  v-model="condition.word2"
                />
                <label for="total-gender">전체</label>
              </div>
              <div>
                <input type="radio" name="gender" id="male" value="남성" v-model="condition.word2" />
                <label for="male">남성</label>
              </div>
              <div>
                <input type="radio" name="gender" id="female" value="여성" v-model="condition.word2" />
                <label for="female">여성</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="age" id="total-age" value="" checked v-model="condition.word3" />
                <label for="total-age">전체</label>
              </div>
              <div>
                <input type="radio" name="age" id="ten" value="10" v-model="condition.word3" />
                <label for="ten">10대</label>
              </div>
              <div>
                <input type="radio" name="age" id="twenty" value="20" v-model="condition.word3" />
                <label for="twenty">20대</label>
              </div>
              <div>
                <input type="radio" name="age" id="thirty" value="30" v-model="condition.word3" />
                <label for="thirty">30대</label>
              </div>
              <div>
                <input type="radio" name="age" id="fourty" value="40" v-model="condition.word3" />
                <label for="fourty">40대</label>
              </div>
              <div>
                <input type="radio" name="age" id="fifty" value="50" v-model="condition.word3" />
                <label for="fifty">50대 이상</label>
              </div>
            </div>
          </div>
        </div>
        <!-- 검색 상세 필터 div 끝 -->
      </div>
      <!-- 질의응답 리스트 -->
      <QnAList @selectQuestion="handleSelectQuesion"/>
    </div>
    <!-- 세션에 selectedQnA 있는 경우 QnADetail 보임 -->
    <div v-if="selectedQuestionId">
      <!-- <div> -->
      <QnADetail :questionId="selectedQuestionId" @isUpdate="handleQuestionUpdate" />
    </div>
    <!-- 질의응답 작성 페이지 -->
    <div v-else-if="selectedQuestionId == null && !updatedQuestion">
      <QnACreate />
    </div>
    <!-- 수정 버튼 눌렸을 시 QnaUpdate 보임 -->
    <div v-else-if="updatedQuestion">
      <QnAUpdate />
    </div>
  </div>
</template>

<script setup>
import QnAList from "@/components/qna/QnAList.vue";
import QnADetail from "@/components/qna/QnADetail.vue";
import QnACreate from "@/components/qna/QnACreate.vue";
import QnAUpdate from "@/components/qna/QnAUpdate.vue";
import { ref } from "vue";
import { useRouter } from "vue-router";
import { useQnAStore } from "@/stores/qna";

const store = useQnAStore();
const router = useRouter();

const filterCheckboxes = ref(false);

const selectedQuestionId = ref(null);
const updatedQuestion = ref(false);

const handleSelectQuesion = (questionId) => {
  selectedQuestionId.value = questionId;
  router.push({ name: 'qnaList' });
}

const handleQuestionUpdate = (isUpdate) => {
  if (isUpdate == true) {
    selectedQuestionId.value = null;
    updatedQuestion.value = true;
    router.push({ name: 'qnaUpdate'});
  }
}

const createQnA = function () {
  selectedQuestionId.value = null;
  updatedQuestion.value = false;
  router.push({ name: "qnaCreate" });
};

const handleSearchOptionChange = (event) => {
  filterCheckboxes.value = event.target.value === "ON";
};

const condition = {
  word1: "",
  word2: "",
  word3: "",
  orderBy: "",
}

const clickSearch = function () {
  const searchCondition = {
    key: null,
    word: null,
    orderByDir: "asc",
    level: condition.word1,
    gender: condition.word2,
    ageRange: condition.word3,
    orderBy: condition.orderBy
  }

  console.log(searchCondition);

  store.searchQuestionList(searchCondition);
};

</script>

<style scoped>
.qna-container {
  width: 1000px;
  margin: 0 auto;
  display: flex;
  font-family: 'Pretendard';
}

.qna-list {
  width: 50%;
  padding-right: 3%;
  border-right: 1px solid #ccc;
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

.qna-search button {
  background-color: white;
  border: none;
  border-radius: 5px;
}

.qna-search button:hover {
  background-color: #ffa101;
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
