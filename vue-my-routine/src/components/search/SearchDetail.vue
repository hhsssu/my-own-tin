<template>
  <div class="search-detail">
    <!-- 루틴 말머리 (정보) -->
    <div class="search-detail-head">
      <div class="search-detail-title">어깨 루틴</div>
      <div class="search-detail-tag">20대</div>
      <div class="search-detail-tag">남성</div>
      <div class="search-detail-tag">어깨</div>
      <div class="search-detail-tag">30분</div>
    </div>
    <!-- 루틴 본문 -->
    <div class="search-detail-content">내용</div>
    <!-- 버튼 -->
    <div class="search-detail-buttons">
      <button>담기</button>
      <button>좋아요</button>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.search-detail {
  margin-left: 4%;
  padding: 15px;
  border-radius: 5px;
  box-shadow: 5px 5px 10px lightgray;
  width: 90%;
  height: 300px;
  display: flex;
  flex-direction: column;
}

.search-detail-head {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.search-detail-title {
  font-size: 1.2em;
  font-weight: 600;
  margin-right: 10px;
}

.search-detail-tag {
  font-size: 0.8em;
  color: white;
  background-color: #aaa;
  border-radius: 3px;
  padding: 3px 7px;
  margin: 0px 5px;
}

.search-detail-content {
  font-size: 1em;
  padding: 5pdetail;
}

.search-detail-buttons {
    margin-top: auto;
    margin-left: auto;
    margin-right: auto;
}

.search-detail-buttons button {
    margin: 10px;
    padding: 10px 20px;
    font-size: 1.3em;
    width: 100px;
    border-radius: 5px;
    border: 1px solid black;

}
</style>
