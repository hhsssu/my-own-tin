<template>
  <div>
    <div class="routine-create-title">
      <label for="title">제목</label>
      <input type="text" v-model="question.title" />
    </div>
    <!-- 내용 -->
    <div class="routine-create-content">
      <label for="content">내용</label>
      <textarea
        name="content"
        id="content"
        cols="30"
        rows="10"
        v-model="question.content"
      ></textarea>
    </div>
  </div>
</template>

<script setup></script>

<style scoped></style>
