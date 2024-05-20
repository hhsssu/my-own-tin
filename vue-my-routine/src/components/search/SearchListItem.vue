<template>
  <div class="search-item" @click="showDetail">
    <!-- 루틴 말머리 (정보) -->
    <div class="search-item-head">
      <div class="search-item-title">어깨 루틴</div>
      <div class="search-item-tag">20대</div>
      <div class="search-item-tag">남성</div>
      <div class="search-item-tag">어깨</div>
      <div class="search-item-tag">30분</div>
      <div class="search-item-more" @click="showMore">...</div>
    </div>
    <!-- 루틴 본문 -->
    <div class="search-item-content">내용</div>
  </div>
</template>

<script setup>

const showDetail = function () {
    console.log('디테일');
}

const showMore = function () {
    // 현재 showDetail과 중첩되는 상태
    console.log('더보기');
}
</script>

<style scoped>
.search-item {
  padding: 15px;
  margin: 10px 0px;
  background-color: #fff9e8;
  border-radius: 5px;
  box-shadow: 5px 5px 10px lightgray;
  width: 94%;
  height: 100px;
}

.search-item-head {
  display: flex;
  align-items: center;
  margin-bottom: 15px;
}

.search-item-title {
  font-size: 1.2em;
  font-weight: 600;
  margin-right: 10px;
}

.search-item-tag {
  font-size: 0.8em;
  color: white;
  background-color: #aaa;
  border-radius: 3px;
  padding: 3px 7px;
  margin: 0px 5px;
}

.search-item-more {
    /* 더보기 */
    margin-left: auto;
}

.search-item-content {
    font-size: 1em;
    padding: 5px;
}
</style>
