<template>
  <div class="qna-create">
    <!-- 프로필 공개 여부 -->
    <div class="qna-create-profile">
      <label for="profile">프로필 공개 여부</label>
      <input type="checkbox" id="public" value="public" v-model="isPublic" />
      <label for="public">예</label>
      <input type="checkbox" id="private" value="private" v-model="isPublic" />
      <label for="private">아니오</label>
    </div>
    <!-- 제목 -->
    <div class="qna-create-title">
      <label for="title">제목</label>
      <input type="text" />
    </div>
    <!-- 내용 -->
    <div class="qna-create-content">
      <label for="content">내용</label>
      <textarea name="content" id="content" cols="30" rows="10"></textarea>
    </div>
    <!-- 루틴 인용 -->
    <div class="qna-create-routine">
      <label for="routine">루틴</label>
      <!-- 선택된 날짜가 불러와져야 함 -->
      <div>2024-05-23</div>
      <button>날짜 선택</button>
    </div>
    <!-- 등록 및 취소 버튼 -->
    <div class="qna-create-buttons">
      <button>등록</button>
      <button>취소</button>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.qna-create {
  /* border: 1px solid gray; */
  padding: 30px;
  margin: 20px;
  width: 500px;
  height: 570px;
  font-size: 20px;
  color: #777;
  box-shadow: 5px 5px 10px lightgray;
}

.qna-create-profile,
.qna-create-title,
.qna-create-content,
.qna-create-routine {
  margin: 20px 0px;
}

.qna-create-profile input {
  margin-right: 10px;
}

.qna-create-profile label,
.qna-create-title label,
.qna-create-content label,
.qna-create-routine label {
  margin-right: 30px;
}

.qna-create-title input {
  border: none;
  border-bottom: 1px solid #999;
  width: 390px;
  font-size: 17px;
  padding: 5px;
}

.qna-create-title input:focus {
  outline: none;
}

.qna-create-content label {
  vertical-align: top;
}

.qna-create-content textarea {
  border: 1px solid #999;
  border-radius: 5px;
  width: 390px;
  height: 300px;
  font-size: 17px;
  padding: 5px;
}

.qna-create-routine {
  display: flex;
  align-items: center;
}

.qna-create-routine div {
  border-bottom: 1px solid #999;
}

.qna-create-routine button {
  border: none;
  border-radius: 5px;
  background-color: #aaa;
  color: white;
  font-size: 17px;
  padding: 5px 20px;
  margin-left: auto;
  margin-right: 30px;
}

.qna-create-routine button:hover {
  background-color: #ffa101;
}

.qna-create-buttons {
  display: flex;
  justify-content: flex-end;
  margin-top: auto;
  margin-right: 30px;
}

.qna-create-buttons button {
  padding: 5px 20px;
  margin-left: 10px;
  color: white;
  font-size: 19px;
  border: none;
  border-radius: 3px;
}

.qna-create-buttons button:nth-child(1) {
  background-color: #ffa101;
}

.qna-create-buttons button:nth-child(2) {
  background-color: #aaa;
}
</style>
