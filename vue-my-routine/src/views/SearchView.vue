<template>
  <div class="search-container">
    <div class="search-filter">
      <div class="search-title">둘러보기</div>
      <!-- 검색창 -->
      <div>
        <div class="search-list-input">
          <div>#</div>
          <input type="text" placeholder="검색어를 입력하세요" />
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
    </div>
    <!-- 루틴 검색 결과 리스트 -->
    <SearchList />
  </div>
</template>

<script setup>
import SearchList from "@/components/search/SearchList.vue";
import { ref } from "vue";

const filterCheckboxes = ref(false);

const handleSearchOptionChange = (event) => {
  filterCheckboxes.value = event.target.value === "ON";
};
</script>

<style scoped>
.search-container {
  width: 1000px;
  margin: 0 auto;
  display: flex;
  flex-direction: column;
}

.search-filter {
  width: 450px;
  padding-right: 25px;
  border-right: 1px solid #AAA;
}

.search-title {
  font-size: 1.5em;
  font-weight: 600;
  color: #31525b;
  margin-bottom: 20px;
}

.search-list-input {
  display: flex;
  border-bottom: 2px solid lightgray;
  font-size: 25px;
  color: #ffa101;
  width: 400px;
  margin-bottom: 10px;
}

.search-list-input input {
  border: transparent;
  font-size: 15px;
  color: gray;
  margin: 5px 10px;
  width: 350px;
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
</style>
