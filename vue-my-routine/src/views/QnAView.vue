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
                <input type="radio" name="total" id="total-level" value="" checked />
                <label for="total-level">전체</label>
              </div>
              <div>
                <input type="radio" name="level1" id="level1" value="1" />
                <label for="level1">Lv 1</label>
              </div>
              <div>
                <input type="radio" name="level2" id="level2" value="2" />
                <label for="level2">Lv 2</label>
              </div>
              <div>
                <input type="radio" name="level3" id="level3" value="3" />
                <label for="level3">Lv 3</label>
              </div>
              <div>
                <input type="radio" name="level4" id="level4" value="4" />
                <label for="level4">Lv 4</label>
              </div>
              <div>
                <input type="radio" name="level5" id="level5" value="Lv 5" />
                <label for="level5">Lv 5</label>
              </div>
              <div>
                <input type="radio" name="level6" id="level6" value="Lv 6" />
                <label for="level-6">Lv 6</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="total-order" id="total-order" value="" checked />
                <label for="total-order">전체</label>
              </div>
              <div>
                <input type="radio" name="like-cnt" id="like-cnt" value="like_cnt" />
                <label for="like">좋아요순</label>
              </div>
              <div>
                <input type="radio" name="create-at" id="create-at" value="create_at" />
                <label for="recent">최신순</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="total-gender" id="total-gender" value="" checked />
                <label for="total-gender">전체</label>
              </div>
              <div>
                <input type="radio" name="male" id="male" value="male" />
                <label for="male">남성</label>
              </div>
              <div>
                <input type="radio" name="female" id="female" value="female" />
                <label for="female">여성</label>
              </div>
            </div>
            <div>
              <div>
                <input type="radio" name="total-age" id="total-age" value="" checked />
                <label for="total-age">전체</label>
              </div>
              <div>
                <input type="radio" name="ten" id="ten" value="10" />
                <label for="ten">10대</label>
              </div>
              <div>
                <input type="radio" name="twenty" id="twenty" value="20" />
                <label for="twenty">20대</label>
              </div>
              <div>
                <input type="radio" name="thirty" id="thirty" value="30" />
                <label for="thirty">30대</label>
              </div>
              <div>
                <input type="radio" name="fourty" id="fourty" value="40" />
                <label for="fourty">40대</label>
              </div>
              <div>
                <input type="radio" name="fifty" id="fifty" value="50" />
                <label for="fifty">50대 이상</label>
              </div>
            </div>
          </div>
        </div>
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
