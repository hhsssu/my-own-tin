<template>
  <div>
    <!-- 댓글 -->
    <div class="qna-detail-comment">
      <div class="count">댓글 2개</div>
      <div class="qna-detail-comment-card" v-for="ans in store.ansList" :key="ans.id">
        <!-- 댓글 작성자 프로필 -->
        <div class="qna-detail-comment-profile">
          <img
            src="/src/assets/img/profile_gentleman.png"
            class="qna-detail-comment-profile-pic"
          />
          <div class="qna-detail-comment-profile-name">{{ ans.writer }}</div>
          <div class="qna-detail-comment-profile-level">Lv.{{ ans.userLevel }}</div>
          <div class="qna-detail-comment-profile-tag">{{ ans.userAge }}</div>
          <div class="qna-detail-comment-profile-tag">남성</div>
        </div>
        <!-- 댓글 내용 -->
        <div>
          <div class="qna-detail-comment-content">
            {{ ans.content }}
          </div>
        </div>
      </div>


    </div>
  </div>
</template>

<script setup>
import { useQnAStore } from '@/stores/qna';
import { defineProps, onMounted } from 'vue';

const store = useQnAStore();

const props = defineProps({
  questionId: {
    type: Number,
    required: true
  }
});

onMounted(() => {
  store.getAnsList(props.questionId);
})

</script>

<style scoped>
.qna-detail-comment {
  /* border-top: 2px solid #777; */
  margin-top: 20px;
  margin-bottom: 10px;
}
.qna-detail-comment .count {
  border-bottom: 1.5px solid #777;
  padding-bottom: 10px;
  padding-left: 10px;
  font-size: 1em;
  color: #31525b;
}

.qna-detail-comment-card {
  padding: 10px 15px;
  border-top: 1px solid #999;
}

.qna-detail-comment-profile {
  display: flex;
  align-items: center;
  /* padding: 10px 15px; */
}

.qna-detail-comment-profile-pic {
  width: 50px;
  height: 50px;
}

.qna-detail-comment-profile-name {
  font-size: 1em;
  /* margin: 0px 5px; */
  padding: 0px 5px;
}

.qna-detail-comment-profile-level {
  font-size: 13px;
  padding: 3px 10px;
  margin: 0px 5px;
  background-color: #fae6b1;
  border-radius: 3px;
}

.qna-detail-comment-profile-tag {
  color: white;
  font-size: 13px;
  padding: 3px 10px;
  margin: 0px 5px;
  background-color: #aaa;
  border-radius: 3px;
}

.qna-detail-comment-content {
  font-size: 17px;
  margin-top: 15px;
  margin-bottom: 15px;
}
</style>
