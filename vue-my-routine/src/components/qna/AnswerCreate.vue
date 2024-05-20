<template>
  <div>
    <div class="qna-detail-comment-input">
      <textarea
        name=""
        id=""
        cols="50"
        rows="4"
        placeholder="댓글을 작성하세요..."
      ></textarea>
      <button class="qna-detail-comment-button">등록</button>
    </div>
  </div>
</template>

<script setup></script>

<style scoped>
.qna-detail-comment-input {
  display: flex;
  flex-direction: column;
}

.qna-detail-comment-input textarea {
  padding: 20px;
  margin: 20px 0px 10px 0px;
  border: 1px solid #777;
  border-radius: 5px;
  font-size: 1em;
  width: 350px;
}

.qna-detail-comment-button {
  background-color: #FFA101;
  color: white;
  font-size: 15px;
  padding: 5px 12px;
  border: none;
  border-radius: 5px;
  margin-left: auto;
  margin-right: 10px;
  /* margin-left: 10px; */
}</style>
