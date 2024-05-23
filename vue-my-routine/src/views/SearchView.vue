<template>
  <div class="search-container">
    <div class="search-list">
      <div class="search-title">둘러보기</div>
      <!-- 검색창 -->
      <div>
        <div class="search-list-input">
          <div>#</div>
          <input type="text" placeholder="내용으로 검색하세요." v-model="condition.word"/>
          <button class="search-button" @click="clickSearch">
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
        <div class="search-list-option">
          <select
            name="search-option"
            id="search-option"
            class="search-list-option"
            @change="handleSearchOptionChange"
          >
            <option value="OFF">검색 필터 OFF</option>
            <option value="ON">검색 필터 ON</option>
          </select>

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
      <!-- 루틴 검색 결과 리스트 -->
      <SearchList @selectRoutineId="handleSelectRoutine" />
    </div>
    <!-- 세션에 selectedSearch 있는 경우에 SearchDetail 보임 -->
    <div class="search-detail-container">
      <div v-if="selectedRoutineId" >
        <SearchDetail :routineId="selectedRoutineId" />
      </div>
    </div>
  </div>
</template>

<script setup>
import SearchList from "@/components/search/SearchList.vue";
import SearchDetail from "@/components/search/SearchDetail.vue";
import { onMounted, ref } from "vue";
import { useRoutineStore } from "@/stores/routine";

const store = useRoutineStore();

const filterCheckboxes = ref(false);
const showSearchDetail = ref(false);

const selectedRoutineId = ref(null);

const handleSelectRoutine = (routineId) => {
  selectedRoutineId.value = routineId;
};

const handleSearchOptionChange = (event) => {
  filterCheckboxes.value = event.target.value === "ON";
};

const condition = {
  // 작성자 아이디를 기반으로 user 정보 조회
  word: "", // 제목 검색어 word
  word1: "", // level 
  word2: "", // gender
  word3: "", // ageRange
  orderBy: "", // 좋아요순 or 최신순(routineAt 기준)
}

// 검색 버튼 입력
const clickSearch = function () {
  // console.log(condition); // 객체 형태로 잘 넘어옴
  const searchCondition = {
    key: null,
    word: condition.word,
    orderByDir: "asc",
    level: condition.word1,
    gender: condition.word2,
    ageRange: condition.word3,
    orderBy: condition.orderBy
  }

  console.log(searchCondition);

  store.searchRoutineList(searchCondition);
};

onMounted(() => {
  if (sessionStorage.getItem("selectedSearch")) {
    showSearchDetail.value = true;
  } else {
    // 기능 구현 후 수정 필요
    // showSearchDetail.value = false;
    showSearchDetail.value = true;
  }
});
</script>

<style scoped>
.search-container {
  width: 1000px;
  margin: 0 auto;
  display: flex;
  /* flex-direction: column; */
}

.search-list {
  /* width: 450px; */
  width: 50%;
  border-right: 1px solid #ccc;
}

.search-title {
  font-size: 1.5em;
  font-weight: 600;
  color: #31525b;
  margin-bottom: 20px;
  text-align: center;
}

.search-button {
  background-color: white;
  border: none;
  border-radius: 5px;
}

.search-button:hover {
  background-color: #ffa101;
}

.search-list-input {
  display: flex;
  border-bottom: 2px solid lightgray;
  font-size: 25px;
  color: #ffa101;
  /* width: 400px; */
  width: 100%;
  margin-right: 25px;
  margin-bottom: 10px;
}

.search-list-input input {
  border: transparent;
  font-size: 15px;
  color: gray;
  margin: 5px 10px;
  /* width: 350px; */
  width: 90%;
}

.search-list-input svg {
  color: gray;
}

.search-list-option {
  /* margin-top: 20px; */
  border: 1px solid #aaa;
  border-radius: 3px;
}

.search-list-option select {
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

.search-detail-container {
  width: 50%;
}
</style>
